//@+leo-ver=5-thin
//@+node:swot.20260515161701.1: * @file app/pages/test-format.vue
//@@language javascript
//@+<< pages test-format >>
//@+node:swot.20260515161731.1: ** << pages test-format >>
<template>
  <div style="padding: 20px;">
    <h1>Utils 自动导入示例</h1>
    
    <div style="margin: 20px 0;">
      <h3>日期格式化：</h3>
      <p>{{ formatDate('2024-12-25') }}</p>
      <p>{{ formatDate(new Date()) }}</p>
    </div>

    <div style="margin: 20px 0;">
      <h3>首字母大写：</h3>
      <p>{{ capitalize('hello world') }}</p>
      <p>{{ capitalize('VUE') }}</p>
      <p>{{ capitalize('nUXt') }}</p>
    </div>

    <div style="margin: 20px 0;">
      <h3>文本截断：</h3>
      <p>{{ truncate('这是一段很长的文本内容，超过20个字符就会被截断', 20) }}</p>
      <p>{{ truncate('短文本', 20) }}</p>
    </div>
  </div>
</template>

<script setup>
// 注意：这里没有任何 import 语句！
// formatDate、capitalize、truncate 可以直接使用

// 也可以在逻辑中直接调用
const message = capitalize('auto-import works!')
console.log(message) // "Auto-import works!"  // 不够 20 个字符全部输出
</script>
//@-<< pages test-format >>
//@-leo
