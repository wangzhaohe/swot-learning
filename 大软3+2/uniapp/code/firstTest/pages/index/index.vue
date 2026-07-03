<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
		<button size="mini" :disabled="false" hover-start-time=20 >按钮2</button>
		<button size="mini" :disabled="buttondisble" hover-start-time=20 >{{buttonText}}</button>
		<button size="mini" @click="goto('/pages/about/about')">按钮4</button>
		<!-- 分割线 -->
		<view class="divider"></view>
		<view>
			<uni-rate :size="18" v-model="rateValue" />
			<text>当前评分：{{ rateValue }} 星</text>
			<button @click="submitRating">提交评分</button>
		</view>
		
	</view>
</template>

<script setup>
import { ref } from 'vue';
import { onLoad } from '@dcloudio/uni-app';

// 替代 data
const title = ref('Hello');
const buttonText = ref('按钮3');
const buttondisble = ref(false);

// 测试评分
const rateValue = ref(5)

// 替代 methods
const goto = (url) => {
    console.log("按钮被点击了，且传入的参数是：" + url);
};

onLoad((options) => {
    console.log('页面加载了', options);
});

// 提交评分的方法
const submitRating = () => {
	console.log('提交的评分：', rateValue.value)
	// 调用接口提交数据
	// api.submit({ rating: rateValue.value })
}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
	.divider {
	  background-color: #e9e9e9;  /* 分割线颜色 */
	  width: 100%;                 /* 宽度 */
	  height: 2rpx;               /* 高度（使用rpx单位） */
	  margin: 20rpx 0;            /* 上下间距 */
	}
</style>
