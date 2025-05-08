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
<v t="swot.20250508125533.1"><vh>Read document</vh></v>
<v t="swot.20250508123848.1"><vh>Asciidoctor</vh></v>
<v t="swot.20250508161823.1"><vh>Git</vh></v>
<v t="swot.20250508123546.1"><vh>leo-editor</vh></v>
</v>
</v>
<v t="swot.20250508124711.1"><vh>@path asciidoctor</vh>
<v t="swot.20250508105244.1"><vh>@adoc asciidoctor.adoc</vh>
<v t="swot.20250508114609.1"><vh>Asciidoctor</vh>
<v t="swot.20250508105234.1"><vh>asciidoctor-default.css -&gt; custom your export html</vh></v>
</v>
</v>
</v>
<v t="swot.20250508105043.2"><vh>@path leo-editor</vh>
<v t="swot.20250508134307.1"><vh>@adoc leo-editor.adoc</vh>
<v t="swot.20250508134351.1"><vh>Learning Leo</vh>
<v t="swot.20250508134253.1"><vh>Install leo-editor from source</vh></v>
<v t="swot.20250508154257.1"><vh>Teaching by leo-editor</vh>
<v t="swot.20250508154257.2"><vh>write document</vh></v>
<v t="swot.20250508154257.3"><vh>write code</vh></v>
<v t="swot.20250508154257.4"><vh>show content</vh></v>
</v>
</v>
</v>
</v>
<v t="swot.20250508160844.1"><vh>@path git</vh>
<v t="swot.20250508161005.1"><vh>@adoc git.adoc</vh>
<v t="swot.20250508161024.1"><vh>Git</vh>
<v t="swot.20250508161032.1"><vh>git-filter-repo -&gt; delete file</vh></v>
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
:toclevels: 3

</t>
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

xref:leo-editor/leo-editor.adoc[ Read leo-editor learning Notes ]

https://leo-editor.github.io/leo-editor/[ Leo’s Home Page ]

https://github.com/leo-editor/leo-editor[ Leo's GitHub repository ]

https://groups.google.com/g/leo-editor[ Leo's forum ]

</t>
<t tx="swot.20250508123848.1">Asciidoctor is a fast, open source, text processor for parsing AsciiDoc into a document model, then converting it to output formats such as HTML 5, DocBook 5, man(ual) pages, PDF, and EPUB 3. Asciidoctor is written in the Ruby programming language.

xref:asciidoctor/asciidoctor.adoc[ Read Asciidoctor learning Notes ]

https://docs.asciidoctor.org/asciidoctor/latest/[ Asciidoctor Documentation ]
</t>
<t tx="swot.20250508124711.1"></t>
<t tx="swot.20250508125533.1">The AsciiDoc file(.adoc) is intended for online viewing, while the HTML file(.html) is for offline access.
</t>
<t tx="swot.20250508134253.1">1. Clone leo-editor source code

    git clone https://github.com/leo-editor/leo-editor.git

2. Install python3

    https://www.python.org/downloads/

3. Create a Python virtual environment

    python3 -m venv your_environment_name
    source your_environment_name/bin/activate

4. Install some packages
+
....
pip install pyqt6
pip install PyQtWebEngine
pip install docutils markdown nbformat numpy matplotlib nbconvert pygments
pip install jupyter-client==6.1.12 qtconsole // &lt;1&gt;
....
+
&lt;1&gt; for python debug in shell
NOTE: jupyter-client-7.3.4 has bug, and can't run `qtconsole`
+
.Change pip source
[NOTE]
====
pip config set global.index-url https://pypi.tuna.tsinghua.edu.cn/simple
====

5. launch leo-editor

    python launchLeo.py
</t>
<t tx="swot.20250508134307.1">@language asciidoc
:source-highlighter: pygments
:icons: font
:scripts: cjk
:toc:
:toc: right
:toc-title: Table of Contents
:toclevels: 3</t>
<t tx="swot.20250508134351.1">
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
<t tx="swot.20250508154257.1"></t>
<t tx="swot.20250508154257.2">@language asciidoc
Teaching documents are typically presented in the form of PowerPoint (PPT) files. However, PPTs are generally used for summarizing and presenting information during live sessions and should be complemented by detailed Word or Markdown files to serve as practical operational aids. 

In programming-related teaching materials, the primary source code of demonstration programs is usually included. Typically, the relevant portions of the source code are copied into the documentation. This practice introduces the challenge of ensuring that the documentation is promptly updated whenever changes are made to the source code.

Therefore, if a method could be devised to write the source code concurrently with the documentation, a significant amount of repetitive labor and time could be saved.


教学文档通常为 ppt 文件，但是 ppt 一般做为临场提纲挈领的演示，需要再配合详尽的 word 或者 markdown 文件来作为实操辅助。

在编程的教学文档里，会包含演示程序的主要源代码。通常会把需要的部分源代码复制到文档里。这样就存在源代码更改后需要再及时更改文档的问题。

所以如果能够在编写文档的同时编写源代码，将会节省大量重复劳动的时间。
</t>
<t tx="swot.20250508154257.3">@language asciidoc
When writing code, it is often necessary to add a lot of explanatory text. The commonly adopted approach is to directly write single-line comments or a large number of block comments in the code.

If these comments in the code could be formatted like an article, the effect would be much better.


在编写代码时，往往需要加入很多说明文字，经常采用的方式是直接在代码里写单行注释或者大量块注释。

如果代码里的这些注释能够有像文章一样的排版体现，效果就会好很多。
</t>
<t tx="swot.20250508154257.4">@language asciidoc
When presenting, making more use of ppt can achieve paginated display of each knowledge node.

If the key documents and main code can be combined and written only once, and presented in the form of nodes, the purpose of integrating the documents, code and presentation can be achieved.


在演示时多采用 ppt，可以实现每个知识节点的分页显示。

如果能够将重点文档和主要代码结合在一起且只写一次，以节点的方式呈现出来，则能达到文档、代码、演示合一的目的。
</t>
<t tx="swot.20250508160844.1"></t>
<t tx="swot.20250508161005.1">@language asciidoc
:source-highlighter: pygments
:icons: font
:scripts: cjk
:toc:
:toc: right
:toc-title: Table of Contents
:toclevels: 3</t>
<t tx="swot.20250508161024.1">
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
<t tx="swot.20250508161032.1">https://github.com/newren/git-filter-repo

To **delete a specific file from all commits in a Git repository**, you can use `git-filter-repo` which is a modern and faster replacement for `git filter-branch`.

Here’s the command to delete a file from all commits:

```bash
git filter-repo --path &lt;path/to/file&gt; --invert-paths
```

Example:

To delete `secret.txt` from all history:

```bash
git filter-repo --path secret.txt --invert-paths
```

Notes:

* This permanently **removes the file from the entire Git history**, including all branches and tags.
* `--invert-paths` means "remove this path instead of keeping it".
* After running this command, you'll likely need to force-push to remote:

```bash
git push --force --all
git push --force --tags
```

&gt; ⚠️ Warning: This rewrites history. All collaborators will need to re-clone or rebase their work.

</t>
<t tx="swot.20250508161823.1">Git is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency.

xref:git/git.adoc[ Read git learning Notes ]

https://git-scm.com/

https://git-scm.com/downloads

</t>
</tnodes>
</leo_file>
