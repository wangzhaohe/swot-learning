<?xml version="1.0" encoding="utf-8"?>
<!-- Created by Leo: https://leo-editor.github.io/leo-editor/leo_toc.html -->
<leo_file xmlns:leo="https://leo-editor.github.io/leo-editor/namespaces/leo-python-editor/1.1" >
<leo_header file_format="2"/>
<globals/>
<preferences/>
<find_panel_settings/>
<vnodes>
<v t="swot.20250508105043.2"><vh>@path leo-editor</vh>
<v t="swot.20250508105244.1"><vh>@adoc asciidoctor.adoc</vh>
<v t="swot.20250508114609.1"><vh>Asciidoctor</vh>
<v t="swot.20250508105234.1"><vh>asciidoctor-default.css -&gt; custom your export html</vh></v>
</v>
</v>
</v>
</vnodes>
<tnodes>
<t tx="swot.20250508105043.2"></t>
<t tx="swot.20250508105234.1">Find it position:

    gem contents asciidoctor | grep asciidoctor-default.css
    ~/.rvm/gems/ruby-2.7.2/gems/asciidoctor-2.0.23/data/stylesheets

Create a hard link:

    ln ~/.rvm/gems/ruby-2.7.2/gems/asciidoctor-2.0.23/data/stylesheets/asciidoctor-default.css leo-editor/
    Now I can edit asciidoctor-default.css in leo-editor.

</t>
<t tx="swot.20250508105244.1">@language asciidoc
:source-highlighter: pygments
:icons: font
:scripts: cjk
:toc:
:toc: right
:toc-title: Table of Contents
:toclevels: 3</t>
<t tx="swot.20250508114609.1">
++++
&lt;button id="toggleButton"&gt;Table of Contents&lt;/button&gt;
&lt;script&gt;
    // 获取按钮和 div 元素
    const toggleButton = document.getElementById('toggleButton');
    const contentDiv = document.getElementById('toc');
    contentDiv.style.display = 'none';

    // 添加点击事件监听器
    toggleButton.addEventListener('click', () =&gt; {
        // 切换 div 的显示状态
        // if (contentDiv.style.display === 'none' || contentDiv.style.display === '') {
        if (contentDiv.style.display === 'none') {
            contentDiv.style.display = 'block';
        } else {
            contentDiv.style.display = 'none';
        }
    });
&lt;/script&gt;
++++
</t>
</tnodes>
</leo_file>
