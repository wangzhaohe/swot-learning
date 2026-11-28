require 'asciidoctor'

# 官方属性，模板读，走 DEFAULT_ATTRIBUTES 即可
# 等价于每篇 .adoc 头部的 :xxx: 定义，无需在 leo-editor 里输入 toc;; 指令
# 单篇文档若写了同名属性，文档内的值会覆盖这里的默认值
Asciidoctor::DEFAULT_ATTRIBUTES['stem'] = 'latexmath'
{
  'author' => 'https://github.com/wangzhaohe/swot-learning',
  'source-highlighter' => 'pygments',
  'icons' => 'font',
  'font-size' => '16px',
  'scripts' => 'cjk',
  'experimental' => '',
  'toc' => 'right',
  'toc-title' => '目录',
  'toclevels' => '3',
  'tip-caption' => '⚡',
  'note-caption' => '❕',
  'important-caption' => '❗',
  'caution-caption' => '⚠️',
  'warning-caption' => '‼️'
}.each { |key, value| Asciidoctor::DEFAULT_ATTRIBUTES[key] = value }

# postprocessor：
# 1. 解决公式渲染错位问题（非官方属性 mathjax-url 无效，改用字符串替换）
# 2. 全局注入 TOC 工具条（Home + Open TOC），替代每个 .adoc 里的 dir 指令
Asciidoctor::Extensions.register do
  postprocessor do
    process do |doc, output|
      output = output.gsub('config=TeX-MML-AM_HTMLorMML', 'config=TeX-MML-AM_CHTML')

      # 只给 html5 后端注入；pdf / github 后端自动跳过（替代原来的 ifndef 双守卫）
      # 已含 dir 指令注入的旧文档跳过，避免重复按钮/脚本
      if doc.backend == 'html5' && !doc.attributes.key?('env-github') &&
         !output.include?('id="toc-toolbar"')
        toolbar = <<~HTML
          <div id="toc-toolbar">
            <a id="homeButton" href="/" title="Back to home">Home</a>
            <button id="toggleButton" type="button">Open TOC</button>
          </div>
          <script>
          (function () {
            // Home：域名根部署 -> "/"；GitHub Pages 项目站点 -> 自动回到项目根
            var home = document.getElementById('homeButton');
            var segs = location.pathname.split('/').filter(Boolean);
            home.href = (location.hostname.endsWith('github.io') && segs.length)
              ? '/' + segs[0] + '/' : '/';

            var toc = document.getElementById('toc');
            var btn = document.getElementById('toggleButton');
            // 没有原生目录时只保留 Home，不显示无意义的开关
            if (!toc) { btn.style.display = 'none'; return; }
            btn.addEventListener('click', function () {
              var open = toc.classList.toggle('active');
              btn.textContent = open ? 'Close TOC' : 'Open TOC';
            });
          })();
          </script>
        HTML
        output = output.sub('</body>', "#{toolbar}</body>")
      end

      output
    end
  end
end

## 检查 asciidoctor-diagram 是否已安装
begin
  require 'asciidoctor-diagram'
rescue LoadError
  warn 'asciidoctor-diagram is not installed'
end

## 检查 asciidoctor-kroki 是否已安装
#begin
#  require 'asciidoctor-kroki'
#rescue LoadError
#  warn 'asciidoctor-kroki is not installed'
#end

# 启用 data-uri（等同于 -a data-uri），图片作为 base64 嵌入网页中
# Asciidoctor::DEFAULT_ATTRIBUTES['data-uri'] = ''
