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
<v t="swot.20250508123546.1"><vh>LEO-Editor</vh></v>
<v t="swot.20250508162744.1"><vh>Software-Designer</vh></v>
</v>
</v>
<v t="swot.20250508124711.1"><vh>@path asciidoctor</vh>
<v t="swot.20250508105244.1"><vh>@adoc asciidoctor.adoc</vh>
<v t="swot.20250508114609.1"><vh>Asciidoctor</vh>
<v t="swot.20250508105234.1"><vh>asciidoctor-default.css -&gt; custom your export html</vh></v>
<v t="swot.20250508220045.1"><vh>options.rb -&gt; asciidoctor config file</vh>
<v t="swot.20250508220045.2"><vh>data-uri</vh></v>
</v>
<v t="swot.20250508224645.1"><vh>asciidoctor-diagram local drawing</vh>
<v t="swot.20250508224817.1"><vh>plantuml</vh>
<v t="swot.20250508230637.1"><vh>gantt</vh></v>
<v t="swot.20250508233908.1"><vh>json</vh>
<v t="swot.20250508233908.2"><vh>@ignore-node #edit data/diagram-json1.puml</vh></v>
</v>
<v t="swot.20250508232203.1"><vh>mindmap</vh></v>
<v t="swot.20250508225006.1"><vh>Salt -&gt; Draw the prototype diagram</vh></v>
<v t="swot.20250508232842.1"><vh>yaml</vh></v>
</v>
</v>
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
<v t="swot.20250508162519.1"><vh>@path software-designer</vh>
<v t="swot.20250508162628.1"><vh>@adoc software-designer.adoc</vh>
<v t="swot.20250508162652.1"><vh>软件设计师例题精讲</vh>
<v t="swot.20250508163105.1"><vh>操作系统</vh>
<v t="swot.20250508163140.1"><vh>进程同步与互斥（PV 操作）</vh>
<v t="swot.20250508163626.1"><vh>前趋图</vh></v>
<v t="swot.20250508164139.1"><vh>PV 操作</vh></v>
<v t="swot.20250508192826.1"><vh>Example</vh></v>
</v>
</v>
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
<t tx="swot.20250508162519.1"></t>
<t tx="swot.20250508162628.1">@language asciidoc
:source-highlighter: pygments
:icons: font
:scripts: cjk
:toc:
:toc: right
:toc-title: Table of Contents
:toclevels: 3</t>
<t tx="swot.20250508162652.1">
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
<t tx="swot.20250508162744.1">Now only for Chinese.

xref:software-designer/software-designer.adoc[ Read software-designer learning Notes ]

https://bm.ruankao.org.cn/sign/welcome

https://ruankaodaren.com/exam/#/

</t>
<t tx="swot.20250508163105.1"></t>
<t tx="swot.20250508163140.1"></t>
<t tx="swot.20250508163626.1">前趋图（Precedence Graph）是一种用于表示任务或进程之间执行顺序关系的图形表示方法。它通常用于项目管理、操作系统的进程调度、编译原理中的指令调度等领域，以帮助确定任务或进程的执行顺序和依赖关系。

前趋图的特点：

1. **节点（Vertices）**：图中的每个节点代表一个任务或进程。
2. **有向边（Directed Edges）**：图中的边表示任务或进程之间的依赖关系。如果存在一条从节点 A 指向节点 B 的有向边，那么它表示任务 A 必须在任务 B 之前完成。

前趋图的作用：

- **确定执行顺序**：通过分析前趋图，可以确定哪些任务可以并行执行，哪些任务必须按顺序执行。
- **避免死锁**：在操作系统中，前趋图可以帮助设计避免死锁的资源分配策略。
- **优化调度**：在编译器设计中，前趋图可以帮助优化指令的执行顺序，提高程序的执行效率。

前趋图的示例：

假设有四个任务 A、B、C、D，其中：
- 任务 A 必须在任务 B 和 C 之前完成。
- 任务 B 和 C 必须在任务 D 之前完成。

前趋图可以表示为：

```
  A
  │
  ▼
  B---C
  │    │
  ▼    ▼
    D
```

在这个图中，A 是 B 和 C 的前趋，B 和 C 是 D 的前趋。

在操作系统的进程调度中，前趋图可以帮助操作系统确定进程的执行顺序，确保系统资源的有效利用和进程的正确执行。
</t>
<t tx="swot.20250508164139.1">PV操作是操作系统中用于进程同步与互斥的一种机制，由两部分组成：P操作（Proberen，荷兰语中的“测试”）和V操作（Verhogen，荷兰语中的“增加”）。这种机制通常与信号量（Semaphore）一起使用，信号量是一个整数变量，用于控制对共享资源的访问。

* P操作（等待操作）

    ** P操作用于申请资源。当一个进程需要访问共享资源时，它会执行P操作。如果信号量的值大于0，表示资源可用，信号量减1，进程继续执行。如果信号量的值为0，表示资源不可用，进程将被阻塞，直到信号量的值变为正数。

* V操作（释放操作）

    ** V操作用于释放资源。当一个进程完成对共享资源的访问后，它会执行V操作。这会使信号量的值增加1。如果此时有其他进程因为资源不可用而被阻塞，那么V操作会唤醒其中一个阻塞的进程，使其可以继续执行。

* 信号量的类型

    1. **整型信号量**：信号量的值可以是任意整数，用于表示资源的数量。当信号量的值为0时，表示没有资源可用。
    2. **二进制信号量**：信号量的值只能是0或1，用于实现互斥锁。0表示锁被占用，1表示锁可用。

PV操作广泛应用于操作系统中的进程同步、互斥、死锁避免等领域。例如：

- **进程同步**：确保多个进程按照特定的顺序执行。
- **互斥访问**：确保在任何时刻只有一个进程可以访问共享资源。
- **避免死锁**：通过控制资源的分配和释放，避免系统进入死锁状态。

通过合理使用PV操作，可以有效地管理进程间的协作和资源共享，提高系统的稳定性和效率。
</t>
<t tx="swot.20250508192826.1">进程 P1、P2、P3、P4、P5 的 link:img/graphviz-pv-2025-05-08-193314.png[前趋图] 如下：

[graphviz, target="img/graphviz-pv-2025-05-08-193314", format=png]
....
digraph finite_state_machine {
    rankdir=LR;
    node [shape = circle];

    P1 -&gt; P3;
    P2 -&gt; P3;
    P3 -&gt; P4;
    P3 -&gt; P5;
}
....

若用 PV 操作控制进程并发执行的过程，则需要设置 4 个信号量 S1、S2、S3、S4，且信号量初值都等于零。下面 link:img/graphviz-2025-05-08-194149.png[PV图] 中 a 和 b 应分别填写（），c 和 d 应分别填写（），e 和 f 应分别填写（）。

[graphviz, target="img/graphviz-2025-05-08-194149", format=png]
....
digraph PetriNet {
    rankdir=TB;
    node [shape=circle]; P1 P2;
    node [shape=box]; P3 P4 P5 a b c d e f;
    end1 [label="", shape=circle];
    end2 [label="", shape=circle];

    P1 -&gt; a [label="P1执行"];
    P2 -&gt; b [label="P2执行"];
    c  -&gt; P3
    P3 -&gt; d [label="P3执行"];
    e -&gt; P4
    P4 -&gt; end1
    f -&gt; P5
    P5 -&gt; end2
}
....

*答案解析：*

- **a 和 b 处**：当 P1 和 P2 执行完毕后，它们需要通过执行 V 操作来增加信号量 S1 和 S2 的值，从而通知 P3 可以开始执行。因此，a 处应填写 V(S1)，b 处应填写 V(S2)。

- **c 和 d 处**：进程 P3 在开始执行前，需要通过执行 P 操作来检查 S1 和 S2 的值是否大于 0。如果 S1 和 S2 的值都大于 0，表示 P1 和 P2 已经执行完毕，P3 可以开始执行。因此，c 处应填写 P(S1)，d 处应填写 P(S2)。

- **e 和 f 处**：进程 P4 和 P5 在开始执行前，需要通过执行 P 操作来检查 S3 和 S4 的值是否大于 0。如果 S3 和 S4 的值都大于 0，表示 P3 已经执行完毕，P4 和 P5 可以开始执行。因此，e 处应填写 P(S3)，f 处应填写 P(S4)。
</t>
<t tx="swot.20250508220045.1">Purpose: It is unnecessary to change the Leo Editor source code to customize the Asciidoctor command used for generating HTML.

Asciidoctor runs in a Ruby environment, and we occasionally need to pass extra parameters. Since Leo Editor uses fixed parameters in the vr3 plugin to generate HTML, and modifying its source code repeatedly isn’t ideal, we developed the following solution.

.Find Ruby LOAD_PATH
[source,console]
----
ruby -e 'puts $LOAD_PATH'

/Users/swot/.rvm/rubies/ruby-2.7.2/lib/ruby/site_ruby/2.7.0
/Users/swot/.rvm/rubies/ruby-2.7.2/lib/ruby/site_ruby/2.7.0/x86_64-darwin21
/Users/swot/.rvm/rubies/ruby-2.7.2/lib/ruby/site_ruby
/Users/swot/.rvm/rubies/ruby-2.7.2/lib/ruby/vendor_ruby/2.7.0
/Users/swot/.rvm/rubies/ruby-2.7.2/lib/ruby/vendor_ruby/2.7.0/x86_64-darwin21
/Users/swot/.rvm/rubies/ruby-2.7.2/lib/ruby/vendor_ruby
/Users/swot/.rvm/rubies/ruby-2.7.2/lib/ruby/2.7.0
/Users/swot/.rvm/rubies/ruby-2.7.2/lib/ruby/2.7.0/x86_64-darwin21
----

.nvim options.rb
[source,ruby]
----
begin
  require 'asciidoctor-diagram'
rescue LoadError
  warn 'asciidoctor-diagram is not installed'
end

# Not use now
#begin
#  require 'asciidoctor-kroki'
#rescue LoadError
#  warn 'asciidoctor-kroki is not installed'
#end

# use data-uri（same as -a data-uri）for embed images base64 string in html page
# Asciidoctor::DEFAULT_ATTRIBUTES['data-uri'] = ''
----

.copy options.rb to site_ruby/2.7.0
[source,console]
----
cp options.rb /Users/swot/.rvm/rubies/ruby-2.7.2/lib/ruby/site_ruby/2.7.0
----

.Then leo-editor source code only use `-r options` will be ok
[source,python]
----
command = f"{prog} {i_path} -o {o_path} -b html5 -r options"
----

[NOTE]
====
leo-editor source code `-r options` position: +
unl://LeoPyRef.leo#Code--&gt;Core classes--&gt;@file leoMarkup.py--&gt;class MarkupCommands--&gt;markup.command_helper &amp; helpers--&gt;markup.run_asciidoctor
====
</t>
<t tx="swot.20250508220045.2">This line of code:

```ruby
Asciidoctor::DEFAULT_ATTRIBUTES['data-uri'] = ''
```

enables the `data-uri` option in Asciidoctor's default attributes, which means **images will be converted to Base64 and embedded directly into the HTML** instead of being referenced by an external URL.

[discrete]
==== **Explanation**
By default, Asciidoctor generates HTML with `&lt;img&gt;` tags that load images using file paths or URLs. For example:

```html
&lt;img src="images/example.png"&gt;
```

If the `data-uri` option is enabled, Asciidoctor will convert the image files into Base64 and embed them directly in the HTML, like this:

```html
&lt;img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAA..."&gt;
```

This approach reduces external requests and is suitable for:

* **Single-file HTML (e.g., offline documents)**
* **Better portability** (no concerns about image path issues)

[discrete]
==== **Equivalent Command-Line Option**
This line of code is equivalent to running the following in the command line:

```console
asciidoctor -a data-uri myfile.adoc
```

Or setting it in the AsciiDoc document:

```adoc
:imagesdir: images
:data-uri:
```

[discrete]
==== **Why Use `Asciidoctor::DEFAULT_ATTRIBUTES`?**
`Asciidoctor::DEFAULT_ATTRIBUTES` is the global default attribute configuration. Setting `data-uri` to an empty string `''` effectively **enables** this option. When Asciidoctor processes attributes:

* If the attribute value is empty (`''`), it is treated as **enabled**
* If the attribute does not exist, it is treated as **disabled**
* If explicitly set as `data-uri=0` or `!data-uri`, it is **disabled**

[discrete]
==== **Summary**

* **Enabling `data-uri`** converts images to Base64 and embeds them in the HTML
* **Improves document portability** by avoiding missing external images
* **Suitable for offline HTML documents**
* **Equivalent to the `-a data-uri` option**

If you do not want to embed images as Base64, you can remove this line of code or explicitly disable `data-uri`:

```ruby
Asciidoctor::DEFAULT_ATTRIBUTES.delete('data-uri')
```
</t>
<t tx="swot.20250508224645.1"></t>
<t tx="swot.20250508224817.1">1. https://docs.asciidoctor.org/diagram-extension/latest/diagram_types/plantuml/
2. https://rubygems.org/gems/asciidoctor-diagram-plantuml
3. https://plantuml.com/zh/
4. https://plantuml.com/

安装 plantuml:

    gem install asciidoctor-diagram-plantuml

</t>
<t tx="swot.20250508225006.1">.link:img/diagram-salt-2025-05-08-225243.png[common prototype]
[plantuml, target=img/diagram-salt-2025-05-08-225243, format=png]
....
@startsalt
scale 1.5
{
  Just plain text
  [This is my button]
  ()  Unchecked radio
  (X) Checked radio
  []  Unchecked box
  [X] Checked box
  "Enter text here   "
  ^This is a droplist^
}
@endsalt
....

.link:img/diagram-salt-2025-05-08-225302.png[prototype in flow]
[plantuml, target=img/diagram-salt-2025-05-08-225302, format=png]
....
@startuml
start
repeat :read data;
  :generate diagrams;
repeat while (\n{{\nsalt\n{^"Next step"\n  Do you want to continue? \n[Yes]|[No]\n}\n}}\n)
stop
@enduml
....
</t>
<t tx="swot.20250508230637.1">.link:img/diagram-gantt-2025-05-08-230846.png[gantt]
[plantuml, target=img/diagram-gantt-2025-05-08-230846, format=png]
....
@startgantt
scale 1.5
'skinparam dpi 300
saturday are closed
sunday are closed

Project starts the 1st of january 2021
[Prototype design end] as [TASK1] requires 19 days
[TASK1] is colored in Lavender/LightBlue
[Testing] requires 14 days
[TASK1]-&gt;[Testing]

2021-01-18 to 2021-01-22 are named [End's committee]
2021-01-18 to 2021-01-22 are colored in salmon 
@endgantt
....
</t>
<t tx="swot.20250508232203.1">.link:img/diagram-mindmap-2025-05-08-232219.png[mindmap]
[plantuml, target=img/diagram-mindmap-2025-05-08-232219, format=png]
....
@startmindmap
* Creole on Mindmap
left side
**:==Creole
  This is **bold**
  This is //italics//
  This is ""monospaced""
  This is --stricken-out--
  This is __underlined__
  This is ~~wave-underlined~~
--test Unicode and icons--
  This is &lt;U+221E&gt; long
  This is a &lt;&amp;code&gt; icon
  Use image : &lt;img:https://plantuml.com/logo3.png&gt;
;
**: &lt;b&gt;HTML Creole 
  This is &lt;b&gt;bold&lt;/b&gt;
  This is &lt;i&gt;italics&lt;/i&gt;
  This is &lt;font:monospaced&gt;monospaced&lt;/font&gt;
  This is &lt;s&gt;stroked&lt;/s&gt;
  This is &lt;u&gt;underlined&lt;/u&gt;
  This is &lt;w&gt;waved&lt;/w&gt;
  This is &lt;s:green&gt;stroked&lt;/s&gt;
  This is &lt;u:red&gt;underlined&lt;/u&gt;
  This is &lt;w:#0000FF&gt;waved&lt;/w&gt;
-- other examples --
  This is &lt;color:blue&gt;Blue&lt;/color&gt;
  This is &lt;back:orange&gt;Orange background&lt;/back&gt;
  This is &lt;size:20&gt;big&lt;/size&gt;
;
right side
**:==Creole line
You can have horizontal line
----
Or double line
====
Or strong line
____
Or dotted line
..My title..
Or dotted title
//and title... //
==Title==
Or double-line title
--Another title--
Or single-line title
Enjoy!;
**:==Creole list item
**test list 1**
* Bullet list
* Second item
** Sub item
*** Sub sub item
* Third item
----
**test list 2**
# Numbered list
# Second item
## Sub item
## Another sub item
# Third item
;
@endmindmap
....
</t>
<t tx="swot.20250508232842.1">plantuml show yaml structure， for example Spring Boot's application.yml.
https://plantuml.com/zh/yaml

.link:img/diagram-yaml-2025-05-08-232919.png[yaml]
[plantuml, target=img/diagram-yaml-2025-05-08-232919, format=png]
....
@startyaml
spring:
  application:
    name: mybatis_quickstart-crud
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/general_manager
    username: root
    password: root
  servlet:
    multipart:                  # 上传文件
      max-file-size: 10MB     # 指定单个文件上传的大小
      max-request-size: 100MB  # 指定单次请求上传文件的总大小

mybatis:
  configuration:
    # 指定mybatis输出日志的位置, 输出控制台
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    #开启驼峰命名自动映射，即从经典数据库列名 a_column 映射到经典 Java 属性 名 aColumn
    map-underscore-to-camel-case: true

# --- 阿里云 OSS ---
# 与之对应引用为 @Vaule("${aliyun.oss.endpoint}")
aliyun:
  oss:
    endpoint: "https://oss-cn-beijing.aliyuncs.com"
    accessKeyId: "LQAI5tJ3drn2qkNAGZrxwpnC"
    accessKeySecret: "cMJ6XHSntg361NsN6swGVlyB0Sks84"
    bucketName: "swot-learn"
@endyaml
....
</t>
<t tx="swot.20250508233908.1">@language asciidoc
Example: Using different styles for highlight -&gt; link:data/diagram-json1.puml[show json source]
Ref: https://plantuml.com/zh/json

    [plantuml, img/diagram-json-2025-05-08-233937, format=png]
    ----
    include::data/diagram-json1.puml[]
    ----

.link:img/diagram-json-2025-05-08-233937.png[draw json image]
[plantuml, img/diagram-json-2025-05-08-233937, format=png]
----
include::data/diagram-json1.puml[]
----
</t>
<t tx="swot.20250508233908.2">@startjson
&lt;style&gt;
  .h1 {
    BackGroundColor green
    FontColor white
    FontStyle italic
  }
  .h2 {
    BackGroundColor red
    FontColor white
    FontStyle bold
  }
&lt;/style&gt;
#highlight "lastName"
#highlight "address" / "city" &lt;&lt;h1&gt;&gt;
#highlight "phoneNumbers" / "0" / "number" &lt;&lt;h2&gt;&gt;
{
  "firstName": "John",
  "lastName": "Smith",
  "isAlive": true,
  "age": 28,
  "address": {
    "streetAddress": "21 2nd Street",
    "city": "New York",
    "state": "NY",
    "postalCode": "10021-3100"
  },
  "phoneNumbers": [
    {
      "type": "home",
      "number": "212 555-1234"
    },
    {
      "type": "office",
      "number": "646 555-4567"
    }
  ],
  "children": [],
  "spouse": null
}
@endjson</t>
</tnodes>
</leo_file>
