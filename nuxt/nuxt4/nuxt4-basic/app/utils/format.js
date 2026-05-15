//@+leo-ver=5-thin
//@+node:swot.20260515161247.1: * @file app/utils/format.js
//@@language javascript
//@+<< utils format >>
//@+node:swot.20260515161303.1: ** << utils format >>
//@@language javascript
// 格式化日期
export const formatDate = (date) => {
  const d = new Date(date)
  return `${d.getFullYear()}-${d.getMonth() + 1}-${d.getDate()}`
}

// 首字母大写
export const capitalize = (str) => {
  if (!str) return ''
  return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase()
}

// 截断文本
export const truncate = (str, length = 20) => {
  if (!str) return ''
  return str.length > length ? str.slice(0, length) + '...' : str
}
//@-<< utils format >>
//@-leo
