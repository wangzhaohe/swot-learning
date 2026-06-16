<template>
	<view class="page-container">
		<uni-forms
			ref="formRef"
			:model="formData"
			:rules="formRules"
			label-width="80"
			label-align="right"
		>
			<uni-forms-item label="姓名" name="username" required>
				<uni-easyinput
					v-model="formData.username"
					placeholder="请输入姓名"
					trim="both"
				/>
			</uni-forms-item>

			<uni-forms-item label="性别" name="gender">
				<uni-data-checkbox
					v-model="formData.gender"
					:localdata="genderOptions"
				/>
			</uni-forms-item>

			<uni-forms-item label="手机号" name="mobile" required>
				<uni-easyinput
					v-model="formData.mobile"
					placeholder="请输入手机号"
					trim="both"
					type="number"
					@input="formData.mobile = String($event).replace(/[^\d\-+]/g, '')"
				/>
			</uni-forms-item>

			<uni-forms-item label="邮箱" name="email">
				<uni-easyinput
					v-model="formData.email"
					placeholder="请输入邮箱（选填）"
					trim="both"
				/>
			</uni-forms-item>

			<uni-forms-item label="备注" name="comment">
				<uni-easyinput
					v-model="formData.comment"
					type="textarea"
					placeholder="请输入备注（选填）"
					maxlength="200"
					auto-height
				/>
			</uni-forms-item>
		</uni-forms>

		<!-- 提交按钮 -->
		<view class="btn-group">
			<button class="btn-submit" :loading="submitting" @click="onSubmit">保存</button>
			<button class="btn-cancel" @click="onCancel">取消</button>
		</view>
	</view>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { genderOptions } from './utils.js';

const formRef = ref(null);
const submitting = ref(false);

// 表单数据
const formData = reactive({
	username: '',
	gender: 0,
	mobile: '',
	email: '',
	comment: '',
});

// 表单校验规则
const formRules = {
	username: {
		rules: [
			{ required: true, errorMessage: '请输入姓名' },
			{ minLength: 1, maxLength: 50, errorMessage: '姓名长度在 1 到 50 个字符' },
		],
	},
	mobile: {
		rules: [
			{ required: true, errorMessage: '请输入手机号' },
			{
				pattern: /^\+?[0-9\-]{3,20}$/,
				errorMessage: '手机号格式不正确',
			},
		],
	},
	email: {
		rules: [
			{
				validateFunction: (rule, value, data, callback) => {
					if (!value || value.trim() === '') {
						callback(); // 空值跳过校验
					} else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value)) {
						callback('邮箱格式不正确');
					} else {
						callback();
					}
				},
			},
		],
	},
};

// 提交
function onSubmit() {
	if (submitting.value) return;
	formRef.value.validate().then((valid) => {
		if (valid) {
			submitting.value = true;
			const db = uniCloud.database();
			// 清理空值
			const data = {};
			Object.keys(formData).forEach((key) => {
				if (formData[key] !== '' && formData[key] !== null && formData[key] !== undefined) {
					data[key] = formData[key];
				}
			});
			db.collection('opendb-contacts')
				.add(data)
				.then(() => {
					uni.showToast({ title: '添加成功', icon: 'success' });
					setTimeout(() => {
						uni.navigateBack();
					}, 800);
				})
				.catch((err) => {
					uni.showToast({ title: '添加失败', icon: 'error' });
					console.error(err);
				})
				.finally(() => {
					submitting.value = false;
				});
		}
	}).catch((err) => {
		console.log('表单校验失败:', err);
	});
}

// 取消
function onCancel() {
	uni.navigateBack();
}
</script>

<style scoped>
.page-container {
	padding: 30rpx;
	background-color: #f5f5f5;
	min-height: 100vh;
}

.btn-group {
	margin-top: 60rpx;
	padding: 0 20rpx;
}

.btn-submit {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	color: #fff;
	border-radius: 50rpx;
	height: 88rpx;
	line-height: 88rpx;
	font-size: 32rpx;
	border: none;
}

.btn-submit::after {
	border: none;
}

.btn-cancel {
	margin-top: 24rpx;
	background: #fff;
	color: #666;
	border-radius: 50rpx;
	height: 88rpx;
	line-height: 88rpx;
	font-size: 32rpx;
	border: 1rpx solid #ddd;
}

.btn-cancel::after {
	border: none;
}
</style>
