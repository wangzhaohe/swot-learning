<!--@+leo-ver=5-thin-->
<!--@+node:swot.20261011155549.2: * @file src/routes/template-syntax/await/+page.svelte-->
<!--@@language html-->
<!--@+<< template >>-->
<!--@+node:swot.20261011155549.4: ** << template >>-->
<!-- 触发预加载的 UI，给了两个一样功能的按钮，为了对比 1 和 2 数字的变化而已 -->
{#each [1, 2] as userId (userId)}
    <button
        onmouseenter={() => preloadProfile(userId)}
        onmouseleave={cancelPreload}
        onclick={() => commitProfile(userId)}
        tabindex="0"
        style="color:grey; border:2px solid gray; font-size: 18px"
    >
        查看用户 {userId} 的资料
    </button>
{/each}

<!-- 数据展示区域 -->
{#if $effect.pending() && !isPreloading}
    <p>加载中…</p>

{:else if result?.error}
    <p style="color: red;">{result.error}</p>

{:else if result?.profile}
    <p>姓名: {result.profile.name}</p>
    <p>简介: {result.profile.bio}</p>

{:else}
    <p>将鼠标悬停在按钮上开始预加载。</p>
{/if}
<!--@-<< template >>-->

<!--@delims // -->
<script lang="ts">
//@+<< script >>
//@+node:swot.20261011155642.1: ** << script >>
//@@language javascript
import { fork } from 'svelte';

/**
 * @typedef {{ id: number, name: string, bio: string }} Profile
 * @typedef {{ profile?: Profile, error?: string }} Result
 */

/** 用于持有当前的 fork 实例
 * @type {import('svelte').Fork | null}
 */
let pendingFork = null;

// 是否正在「预载」（fork 投机阶段，尚未 commit）。
// $effect.pending() 会连 fork 的预取请求一起计数，所以需要这个标志把
// 「投机预载」和「真正提交的加载」区分开，避免 hover 时也闪"加载中…"。
/** @type {boolean} */
let isPreloading = $state(false);

// 唯一的真实状态：当前选中的用户。fork 隔离的就是「对它的一次同步写入」
/** @type {number | null} */
let selectedId = $state(null);

/**
 * 异步派生：请求由 Svelte 的 async 机制承载。
 * 它的挂起/兑现会自动登记到「创建它时」的那个 batch —— 也就是 fork。
 * 这样才能被 discard() 作废、被 commit() 复用。
 * @type {Result | null}
 */
const result = $derived(
    selectedId === null
    ? null
    : await loadProfile(selectedId)
);

// 加载态：模板里用 `$effect.pending() && !isPreloading` 判断。
// 原因：在 experimental.async 模式下，selectedId 变化触发的 $derived(await ...) 会把
// 整个更新「挂起」——中间态（selectedId 已变、result 还没回来）不会渲染到 DOM，
// 所以不能用 selectedId/result 派生出 loading（派生了也看不到）。$effect.pending()
// 是 Svelte 专门为此提供的、能跨挂起窗口工作的计数。
// 但 $effect.pending() 也会计入 fork 的预取请求，所以用 !isPreloading 排除投机预载。

//@+others
//@+node:swot.20261011161917.1: *3* async function fetchProfile(userId) 模拟一个异步网络请求
/**
 * 模拟一个异步网络请求
 * @param {number} userId
 * @returns {Promise<Profile>}
 */
async function fetchProfile(userId) {
    // 实际项目中这里会是 fetch('/api/user/' + userId)
    // 模拟网络延迟
    await new Promise(
        (r) => setTimeout(r, 6000)
    );
    return {
        id: userId,
        name: `用户 ${userId}`,
        bio: '这是预加载的个人资料。'
    };
}
//@+node:swot.20261011162029.1: *3* async function loadProfile(userId)
/**
 * 把异常收进返回值，避免污染 fork 的错误边界
 * @param {number} userId
 * @returns {Promise<Result>}
 */
async function loadProfile(userId) {
    try {
        return {
            profile: await fetchProfile(userId)
        };
    }
    catch (e) {
        console.error(e);
        return { error: '加载失败' };
    }
}
//@+node:swot.20261011162234.1: *3* function preloadProfile(userId)
/**
 * 1. 预加载：fork 内只做一次同步写入，请求随即在隔离上下文中起飞
 * @param {number} userId
 */
function preloadProfile(userId) {
    if (pendingFork) {
        pendingFork.discard();
    }

    pendingFork = fork(() => {
        selectedId = userId;
    });

    isPreloading = true;
}
//@+node:swot.20261011162310.1: *3* async function commitProfile(userId)
/**
 * 2. 提交：把 fork 批次一次性写实。
 *    预取到的结果本身就在 batch.current 里，所以不会二次请求
 * @param {number} userId
 */
async function commitProfile(userId) {
    if (pendingFork) {
        isPreloading = false; // 已提交，不再是投机预载
        await pendingFork.commit();
        pendingFork = null;
    }
    // 没有预加载：键盘(Enter/Space)、触屏点按、程序化点击都不会先触发 mouseenter
    // 加载态由模板里的 $effect.pending() 显示，这里只需触发数据请求
    else {
        selectedId = userId;
    }
}
//@+node:swot.20261011162329.1: *3* function cancelPreload()
/** 3. 取消：作废 fork，其中挂起的 async derived 会被 reject(OBSOLETE) */
function cancelPreload() {
    if (pendingFork) {
        pendingFork.discard();
        pendingFork = null;
    }
    isPreloading = false;
}
//@-others
//@-<< script >>
//@delims <!-- --> 
</script>

<!--@delims /* */ -->
<style>
/*@+<< style >>*/
/*@+node:swot.20261011155549.5: ** << style >>*/
/*@-<< style >>*/
/*@delims <!-- --> */
</style>

<!--@+doc-->
<!--
测试预加载功能 fork()
-->
<!--@-leo-->
