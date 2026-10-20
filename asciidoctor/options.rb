require 'asciidoctor'

# 官方属性，模板读，走 DEFAULT_ATTRIBUTES 即可
Asciidoctor::DEFAULT_ATTRIBUTES['stem'] = 'latexmath'

# 下面的 postprocessor 可以解决公式渲染错位问题
# 非官方属性 mathjax-url 无效，改用 postprocessor 替换字符串
Asciidoctor::Extensions.register do
  postprocessor do
    process do |doc, output|
      output.gsub('config=TeX-MML-AM_HTMLorMML', 'config=TeX-MML-AM_CHTML')
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
