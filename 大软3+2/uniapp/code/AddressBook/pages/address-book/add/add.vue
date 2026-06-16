<template>
	<view>
		<uni-easyinput  v-model="item.name" placeholder="name" />
		<uni-easyinput  v-model="item.phone" placeholder="phone" />
		<button type="default" @click="submit">提交</button>
	</view>
</template>

<script setup>
	import { ref } from 'vue';

	// _id 是自动生成的，不用管
	const item = ref({
		name: "",
		phone: ""
	});
	function submit(){
		const db = uniCloud.database()
		db.collection("contacts").add(item.value).then(e=>{
			console.log(e);
		})
	}
</script>

<style>
	       
</style>
