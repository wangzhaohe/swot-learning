//@+leo-ver=5-thin
//@+node:swot.20260901105510.1: * @file src/lib/state-class/placement-rules.spec.js
//@@language javascript
/**
 * 这个用例跑在 server（node）项目里：只做编译期校验，不需要 $effect，也就不需要浏览器。
 * 编译期合法性与报错校验（4 个）
 */

import { compileModule } from 'svelte/compiler';
import { describe, expect, it } from 'vitest';

/**
 * 按 client 模式编译一段源码，返回报错信息；没有报错则返回 null。
 * @param {string} source
 */
function compileError(source) {
    try {
        compileModule(source, {
            generate: 'client',
            filename: 'case.svelte.js'
        });
        return null;
    } catch (error) {
        return /** @type {Error} */ (error).message;
    }
}

describe('$state 在 class 里的合法 / 非法位置', () => {
    //@+others
    //@+node:swot.20260904140228.1: ** 1. 文档里的两种写法都能正常编译
    /**
     * 两种写法：作为属性和构造方法的首次赋值都正确
     * 注意 $state 是作为字符串存在的
     */
    it('文档里的两种写法都能正常编译', () => {
        const message = compileError(`
            export class Todo {
                done = $state(false);
                constructor(text) {
                    this.text = $state(text);
                }
            }
        `);
        expect(message).toBeNull();
    });
    //@+node:swot.20260904140211.1: ** 2. private 私有字段也能正常编译
    // 注意 $state 是作为字符串存在的
    it('private 私有字段也能正常编译', () => {
        const message = compileError(`
            export class Todo {
                #done = $state(false);
            }
        `);
        expect(message).toBeNull();
    });
    //@+node:swot.20260904140148.1: ** 3. 构造函数里「非首次」赋值会编译报错
    // 注意 $state 是作为字符串存在的
    it('构造函数里「非首次」赋值会编译报错', () => {
        const message = compileError(`
            export class Todo {
                constructor(text) {
                    this.text = text;
                    this.text = $state(text);  // 这不是首次赋值
                }
            }
        `);
        expect(message).toContain(
            'Cannot assign to a state field before its declaration'
        );
    });
    //@+node:swot.20260904140130.1: ** 4. 在普通方法里用 $state 赋值会编译报错
    // 注意 $state 是作为字符串存在的
    it('在普通方法里用 $state 赋值会编译报错', () => {
        const message = compileError(`
            export class Todo {
                addNote() {
                    this.note = $state('x'); // addNote 是一个普通的方法，不能用 $state
                }
            }
        `);
        // 只能在类的构造器中使用 $state
        expect(message).toContain(
            'the first assignment to a class field at the top level of the constructor'
        );
    });

    /**
     * 只有三种情况可以使用 $state:
     * `$state(...)` can only be used as a variable declaration initializer, a class field declaration, or the first assignment to a class field at the top level of the constructor.
     */
    //@-others
});
//@-leo
