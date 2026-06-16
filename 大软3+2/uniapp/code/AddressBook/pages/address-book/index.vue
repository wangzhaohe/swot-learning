<template>
	<view>
		<unicloud-db ref="udb" v-slot:default="{data, loading, error, options}" collection="contacts">
			<view v-if="error">{{error.message}}</view>
			<view v-else>
				 <uni-list>
					<!-- rmItem: 删除条目 -->
					<!-- updateFn: 更新条目 -->
				 	<uni-list-item 
						v-for="item in data"
						@longpress="rmItem(item._id)"
						@click="updateFn(item)"
						:key="item._id" 
						:title="item.name"
						:note="'' + item.phone"
						link
					>
					</uni-list-item>
				 </uni-list>
			</view>
		</unicloud-db>
	</view>
</template>

<script setup>
import { ref } from 'vue'

const udb = ref(null)

// 直接删除条目
function rmItem(id) {
	udb.value.remove(id)
}

// 跳转到更新
function updateFn(item){
	let tmp = JSON.stringify(item);
	console.log("tmp:", tmp);
	uni.navigateTo({
		url: './update/update?item=' + tmp,
		success: res => {},
		fail: () => {},
		complete: () => {}
	})
}
</script>

<style>
	       
</style>
