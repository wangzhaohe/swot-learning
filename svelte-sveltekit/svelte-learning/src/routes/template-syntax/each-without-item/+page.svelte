<!--@+leo-ver=5-thin-->
<!--@+node:swot.20260918084548.1: * @file src/routes/template-syntax/each-without-item/+page.svelte-->
<!--@@language html-->
<div class="chess-board">
    {#each { length: 8 }, rank}
        {#each { length: 8 }, file}
            <div class:black={(rank + file) % 2 === 1}></div>
        {/each}
    {/each}
</div>

<style>
    .chess-board {
        display: grid;
        grid-template-columns: repeat(8, 1fr);
        grid-template-rows: repeat(8, 1fr);
        border: 1px solid black;
        aspect-ratio: 1;

        .black {
            background: black;
        }
    }
</style>
<!--@-leo-->
