<?xml version="1.0" encoding="utf-8"?>
<!-- Created by Leo: https://leo-editor.github.io/leo-editor/leo_toc.html -->
<leo_file xmlns:leo="https://leo-editor.github.io/leo-editor/namespaces/leo-python-editor/1.1" >
<leo_header file_format="2"/>
<globals/>
<preferences/>
<find_panel_settings/>
<vnodes>
<v t="swot.20250508105043.2"><vh>@path leo-editor</vh>
<v t="swot.20250508105244.1"><vh>use asciidoctor</vh>
<v t="swot.20250508105234.1"><vh>asciidoctor-default.css -&gt; custom your export html</vh></v>
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
<t tx="swot.20250508105244.1"></t>
</tnodes>
</leo_file>
