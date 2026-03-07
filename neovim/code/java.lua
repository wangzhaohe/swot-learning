--@+leo-ver=5-thin
--@+node:swot.20260306153430.1: * @file code/java.lua
--@@language lua
--@+others
--@+node:swot.20260306092624.1: ** 4.1 强制当前缓冲区的缩进为 4 空格
vim.opt_local.shiftwidth = 4
vim.opt_local.tabstop = 4
vim.opt_local.softtabstop = 4
vim.opt_local.expandtab = true

--@+node:swot.20260306092634.1: ** 4.2 配置 jdtls 的启动参数
--@@language lua
local java_bin = "/Users/swot/Library/Java/JavaVirtualMachines/temurin-17.0.18/Contents/Home/bin/java"

local jdtls_path = vim.fn.expand("~/jdtls_1.33")

local launcher_jar = vim.fn.glob(jdtls_path .. "/plugins/org.eclipse.equinox.launcher_*.jar")

local workspace_dir = vim.fn.stdpath("cache") .. "/jdtls/" .. vim.fn.fnamemodify(vim.fn.getcwd(), ":p:h:t")

--@+node:swot.20260306092646.1: ** 4.3 获取 blink.cmp 的底层能力
--@@language lua
local client_capabilities = vim.lsp.protocol.make_client_capabilities()
local has_blink, blink = pcall(require, "blink.cmp")
if has_blink then
  client_capabilities = blink.get_lsp_capabilities(client_capabilities)
end

local config = {
  cmd = {
    java_bin,
    "-Declipse.application=org.eclipse.jdt.ls.core.id1",
    "-Dosgi.bundles.defaultStartLevel=4",
    "-Declipse.product=org.eclipse.jdt.ls.core.product",
    "-Dlog.protocol=true",
    "-Dlog.level=ALL",
    "-Xmx1g",
    "--add-modules=ALL-SYSTEM",
    "--add-opens",
    "java.base/java.util=ALL-UNNAMED",
    "--add-opens",
    "java.base/java.lang=ALL-UNNAMED",
    "-jar",
    launcher_jar,
    "-configuration",
    jdtls_path .. "/config_mac",
    "-data",
    workspace_dir,
  },
  root_dir = require("jdtls.setup").find_root({ ".git", "pom.xml", ".idea", "testBasicSyntax.iml" }) or vim.fn.getcwd(),
  capabilities = client_capabilities,
  settings = {
    java = {
      signatureHelp = { enabled = true },
      contentProvider = { preferred = "fernflower" },
      configuration = { updateBuildConfiguration = "interactive" },
      references = { includeDecompiledSources = true },
      inlayHints = { parameterNames = { enabled = "all" } },
    },
  },
}
--@+node:swot.20260306092706.1: ** 4.4 强制 LSP 格式化时使用 4 空格
--@@language lua
vim.lsp.handlers["textDocument/formatting"] = vim.lsp.with(vim.lsp.handlers.formatting, {
  tabSize = 4,
  insertSpaces = true,
})

--@+node:swot.20260306004048.1: ** 4.5 配置快捷运行
--@@language lua
-- 终极 Java 一键运行 (全自动扫包 + 跨目录编译)
vim.keymap.set("n", "<leader>rj", function()
  vim.cmd("write") -- 保存当前文件

  local file_path = vim.fn.expand("%:p")
  -- 修复：强制从当前文件的目录开始向上寻找 src
  local current_dir = vim.fs.dirname(file_path)
  local src_marker = vim.fs.find({ "src" }, { upward = true, path = current_dir })[1]

  if not src_marker then
    -- 单文件模式：如果没有包结构，直接用 Java 11+ 原生方式运行
    vim.cmd("split | term java " .. vim.fn.shellescape(file_path))
    return
  end

  local project_root = vim.fs.dirname(src_marker)
  local src_dir = project_root .. "/src"

  -- 1. 获取相对路径用于推导包名全称
  local relative_file = file_path:sub(#src_dir + 2)
  local class_with_path = relative_file:gsub("%.java$", "")
  local full_class_name = class_with_path:gsub("/", ".")

  -- 2. 核心指令：扫描整个 src 目录下的所有 .java 文件并统一编译
  -- 技巧：将所有找到的文件路径写入 sources.txt，通过 javac @sources.txt 编译
  -- 这样既能解决所有跨包依赖，又能避免项目变大后命令行参数超长的报错
  local run_cmd = string.format(
    "cd %s && mkdir -p ../out && find . -name '*.java' > sources.txt " ..
    "&& javac -d ../out @sources.txt " ..
    "&& rm sources.txt " ..
    "&& java -cp ../out %s",
    vim.fn.shellescape(src_dir),
    full_class_name
  )

  -- 使用 Snacks.terminal 开启下侧分栏
  Snacks.terminal(run_cmd, {
    cwd = src_dir,
    interactive = false,
    -- 核心：通过 win 参数指定布局为 split 并在下方
    win = {
      position = "bottom",
      style = "terminal", -- 确保使用终端样式
      height = 0.4,
    },
  })
end, { buffer = true, desc = "Java 编译并运行(支持全项目跨包依赖)" })
--@+node:swot.20260306092724.1: ** 4.6 核心：每次打开 java 文件时，触发挂载

require("jdtls").start_or_attach(config)
--@-others
--@-leo
