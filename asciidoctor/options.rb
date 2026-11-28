require 'asciidoctor'

# 官方属性，模板读，走 DEFAULT_ATTRIBUTES 即可
Asciidoctor::DEFAULT_ATTRIBUTES['stem'] = 'latexmath'

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
            <button id="toggleButton" type="button">Open Toc</button>
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
