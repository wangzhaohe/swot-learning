<template>
	<view>
		<uni-easyinput  v-model="item.name" placeholder="name" />
		<uni-easyinput  v-model="item.phone" placeholder="phone" />
		<button type="default" @click="submit">提交</button>
	</view>
</template>

<script setup>
	import { ref } from 'vue';
	import { onLoad } from '@dcloudio/uni-app';
	
	const item = ref({
		_id: "",  // 在已有的记录中，这是固定的
		name: "",
		phone: ""
	});
	
	function submit(){
		const db = uniCloud.database()
		let tmp = {...item.value}
		delete tmp._id;
		console.log("tmp:", tmp);
		db.collection("contacts")
		  .doc(item.value._id)
		  .update(tmp)
		  .then(e=>{
			console.log(e);
		})
	}
	// 启动时获取 url 传递的参数 
	onLoad((options) => {
		// console.log("options:", options);
		item.value = JSON.parse(decodeURIComponent(options.item));
		// console.log("item:", item.value);
	})
</script>

<style>
	       
</style>
