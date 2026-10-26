//@+leo-ver=5-thin
//@+node:swot.20261026105949.1: * @file code/23-design-patterns/01-strategy.js
//@@language javascript
/**
 * 策略接口：接收两个数字，返回结果
 * @typedef {(a: number, b: number) => number} Strategy
 */

// 具体策略

/** @type {Strategy} 加法 */
const add = (a, b) => a + b;

/** @type {Strategy} 减法 */
const subtract = (a, b) => a - b;

/** @type {Strategy} 乘法 */
const multiply = (a, b) => a * b;

/**
 * 核心就这一行：上下文（Context）把算法委托给传入的策略
 * @param {Strategy} strategy 具体策略
 * @param {number} a
 * @param {number} b
 */
const execute = (strategy, a, b) => strategy(a, b);

console.log(execute(add, 1, 2));      // 3
console.log(execute(subtract, 5, 2)); // 3
console.log(execute(multiply, 3, 4)); // 12

//@+others
//@+node:swot.20261026110815.1: ** 可选扩展：策略表
// ===== 可选扩展：策略表，用于替代 if/else 分支分发 =====

/**
 * 策略表：用 key 选择策略
 * @type {Record<string, Strategy>}
 */
const strategies = { add, subtract, multiply };

/**
 * 按操作名分发
 * @param {keyof typeof strategies} op
 * @param {number} a
 * @param {number} b
 */
const calcBy = (op, a, b) => {
  const strategy = strategies[op];
  if (!strategy) throw new Error(`未知策略: ${op}`);
  return strategy(a, b);
};

console.log(calcBy('multiply', 3, 4)); // 12
//@+node:swot.20261026110806.1: ** 可选扩展：上下文类
// ===== 可选扩展：只在需要「保存策略、多次复用」时才用类 =====

/**
 * 上下文类：持有策略，避免每次调用都要传一遍
 */
class Calculator {
  /** @param {Strategy} strategy 初始策略 */
  constructor(strategy) {
    this.strategy = strategy;
  }

  /** @param {Strategy} strategy 运行时切换策略 */
  setStrategy(strategy) {
    this.strategy = strategy;
  }

  /**
   * 执行计算
   * @param {number} a
   * @param {number} b
   */
  calc(a, b) {
    return this.strategy(a, b);
  }
}

const calc = new Calculator(add);
console.log(calc.calc(1, 2)); // 3

calc.setStrategy(multiply);
console.log(calc.calc(3, 4)); // 12
//@-others
//@-leo
