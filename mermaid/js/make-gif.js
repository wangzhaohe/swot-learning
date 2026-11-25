/**
 * 把带 SMIL 动画的 Mermaid SVG 转成循环 GIF。
 *
 * 运行（puppeteer 是 pnpm 全局装的，需要把全局 node_modules 挂进 NODE_PATH）：
 *   NODE_PATH="$(pnpm root -g)" node make-gif.js
 *
 * 输入：./src/*.svg   输出：./img/*.gif
 * 中间帧放系统临时目录，不污染博客目录。
 */
const fs = require('fs');
const os = require('os');
const path = require('path');
const crypto = require('crypto');
const { execFileSync } = require('child_process');
const puppeteer = require('puppeteer');

const ROOT = __dirname;
const SRC_DIR = path.join(ROOT, 'src');
const OUT_DIR = path.join(ROOT, 'img');
const WORK = path.join(os.tmpdir(), 'mmd-gif-frames');

const FILES = [
  'mermaid-test-2026-11-22-113447.svg',
  'mermaid-test-2026-11-22-125752.svg',
  'mermaid-test-2026-11-22-130426.svg',
];

const FPS = 12;
const CYCLE = 3;                 // 3 秒一轮：0-1s step1，1-2s step2，2-3s step3
const NFRAMES = FPS * CYCLE;     // 36 帧
const DSF = 2;                    // 2x 清晰度

const md5 = b => crypto.createHash('md5').update(b).digest('hex').slice(0, 10);

/**
 * puppeteer.executablePath() 默认指向完整版 Chrome；若只装了
 * chrome-headless-shell（puppeteer browsers install chrome-headless-shell），
 * 会报 "Browser was not found"。这里直接探测缓存目录里的 headless-shell。
 */
function resolveChrome() {
  if (process.env.PUPPETEER_EXECUTABLE_PATH) return process.env.PUPPETEER_EXECUTABLE_PATH;
  const base = process.env.PUPPETEER_CACHE_DIR || path.join(os.homedir(), '.cache', 'puppeteer');
  const hits = [];
  const walk = (dir, depth) => {
    if (depth > 3 || !fs.existsSync(dir)) return;
    for (const e of fs.readdirSync(dir, { withFileTypes: true })) {
      const p = path.join(dir, e.name);
      if (e.isFile() && /^chrome-headless-shell(\.exe)?$/.test(e.name)) hits.push(p);
      else if (e.isDirectory() && depth < 3) walk(p, depth + 1);
    }
  };
  walk(path.join(base, 'chrome-headless-shell'), 0);
  if (!hits.length) throw new Error('找不到 chrome-headless-shell，请先执行 puppeteer browsers install chrome-headless-shell');
  return hits.sort().pop();
}

function viewBox(svgText) {
  const m = svgText.match(/viewBox='([^']+)'/);
  if (!m) throw new Error('找不到 viewBox');
  const n = m[1].trim().split(/[\s,]+/).map(Number);
  return { x: n[0], y: n[1], w: n[2], h: n[3] };
}

function buildGif(frameDir, outPath) {
  execFileSync('ffmpeg', [
    '-y', '-loglevel', 'error',
    '-framerate', String(FPS),
    '-i', path.join(frameDir, 'f_%03d.png'),
    '-filter_complex', '[0:v]split[a][b];[a]palettegen=stats_mode=full[p];[b][p]paletteuse=dither=sierra2_4a',
    '-loop', '0',
    '-gifflags', '+transdiff',
    outPath,
  ], { stdio: ['ignore', 'pipe', 'pipe'] });
}

(async () => {
  fs.mkdirSync(WORK, { recursive: true });
  const browser = await puppeteer.launch({
    executablePath: resolveChrome(),
    headless: 'shell',
    args: ['--no-sandbox', '--disable-setuid-sandbox', '--disable-dev-shm-usage', '--disable-gpu'],
  });

  try {
    for (const name of FILES) {
      const src = path.join(SRC_DIR, name);
      const vb = viewBox(fs.readFileSync(src, 'utf8'));
      const out = path.join(OUT_DIR, name.replace(/\.svg$/, '.gif'));
      const frameDir = path.join(WORK, name.replace(/\.svg$/, ''));
      fs.mkdirSync(frameDir, { recursive: true });
      fs.readdirSync(frameDir).filter(f => f.endsWith('.png')).forEach(f => fs.unlinkSync(path.join(frameDir, f)));

      const page = await browser.newPage();
      await page.setViewport({ width: Math.ceil(vb.w) + 20, height: Math.ceil(vb.h) + 20, deviceScaleFactor: DSF });
      // 关键：否则 SVG 内嵌的 @media (prefers-reduced-motion: reduce) 会把圆点和高亮隐藏
      await page.emulateMediaFeatures([{ name: 'prefers-reduced-motion', value: 'no-preference' }]);
      await page.goto('file://' + src, { waitUntil: 'load' });

      // 独立 SVG 文档里 documentElement 就是根 <svg>；把尺寸钉到 viewBox，去掉留白
      await page.evaluate(({ w, h }) => {
        const svg = document.documentElement;
        svg.style.setProperty('width', w + 'px', 'important');
        svg.style.setProperty('height', h + 'px', 'important');
        svg.style.setProperty('max-width', 'none', 'important');
        svg.style.setProperty('background-color', 'white', 'important');
        svg.pauseAnimations();
        svg.setCurrentTime(0);
      }, { w: vb.w, h: vb.h });

      const el = await page.$('svg');
      const hashes = new Set();
      for (let i = 0; i < NFRAMES; i++) {
        await page.evaluate(t => document.querySelector('svg').setCurrentTime(t), i / FPS);
        const buf = await el.screenshot({ path: path.join(frameDir, `f_${String(i).padStart(3, '0')}.png`) });
        hashes.add(md5(buf));
      }
      if (hashes.size < 5) throw new Error(`${name}：只有 ${hashes.size} 种帧，动画可能没跑起来`);

      buildGif(frameDir, out);
      console.log(`[OK] ${name} -> ${path.basename(out)} | ${hashes.size}/${NFRAMES} 帧不同 | ${(fs.statSync(out).size / 1024).toFixed(0)} KB`);
      await page.close();
    }
  } finally {
    await browser.close();
  }
})().catch(e => { console.error('ERROR:', e.stack || e.message); process.exit(1); });
