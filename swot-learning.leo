<?xml version="1.0" encoding="utf-8"?>
<!-- Created by Leo: https://leo-editor.github.io/leo-editor/leo_toc.html -->
<leo_file xmlns:leo="https://leo-editor.github.io/leo-editor/namespaces/leo-python-editor/1.1" >
<leo_header file_format="2"/>
<globals/>
<preferences/>
<find_panel_settings/>
<vnodes>
<v t="swot.20250508123413.1"><vh>@adoc README.adoc</vh>
<v t="swot.20250508123515.1"><vh>Record learning contents</vh>
<v t="swot.20250508123848.1"><vh>Asciidoctor</vh></v>
<v t="swot.20250508123546.1"><vh>leo-editor</vh></v>
</v>
</v>
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
<t tx="swot.20250508123413.1">@language asciidoc
:source-highlighter: pygments
:icons: font
:scripts: cjk
:toc:
:toc: right
:toc-title: Table of Contents
:toclevels: 3</t>
<t tx="swot.20250508123515.1">
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
<t tx="swot.20250508123546.1">Leo is a PIM, IDE and outliner that accelerates the work flow of programmers, authors and web designers. 
Outline nodes may appear in more than one place, allowing multiple organizations of data within a single outline.

https://leo-editor.github.io/leo-editor/[ Leo’s Home Page ]

https://github.com/leo-editor/leo-editor[ Leo's GitHub repository ]

https://groups.google.com/g/leo-editor[ Leo's forum ]

</t>
<t tx="swot.20250508123848.1">Asciidoctor is a fast, open source, text processor for parsing AsciiDoc into a document model, then converting it to output formats such as HTML 5, DocBook 5, man(ual) pages, PDF, and EPUB 3. Asciidoctor is written in the Ruby programming language.

https://docs.asciidoctor.org/asciidoctor/latest/[ Asciidoctor Documentation ]

</t>
</tnodes>
</leo_file>
