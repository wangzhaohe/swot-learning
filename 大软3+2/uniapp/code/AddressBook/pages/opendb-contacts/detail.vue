<template>
	<view class="page-container">
		<!-- 查看模式 -->
		<view v-if="!isEditing" class="detail-view">
			<!-- 头像区域 -->
			<view class="detail-header">
				<view class="detail-avatar">
					<text class="detail-avatar-text">{{ detailData.username ? detailData.username.charAt(0) : '?' }}</text>
				</view>
				<text class="detail-name">{{ detailData.username || '未命名' }}</text>
				<text class="detail-gender" :class="genderClass(detailData.gender)">
					{{ genderText(detailData.gender) }}
				</text>
			</view>

			<!-- 信息卡片 -->
			<view class="info-card">
				<view class="info-row">
					<text class="info-label">手机号</text>
					<text class="info-value" @click="onCallPhone(detailData.mobile)">
						{{ detailData.mobile || '未填写' }}
					</text>
					<uni-icons
						v-if="detailData.mobile"
						type="phone"
						size="18"
						color="#667eea"
						class="info-action"
					/>
				</view>
				<view class="info-row">
					<text class="info-label">邮箱</text>
					<text class="info-value">{{ detailData.email || '未填写' }}</text>
				</view>
				<view class="info-row">
					<text class="info-label">备注</text>
					<text class="info-value">{{ detailData.comment || '无' }}</text>
				</view>
				<view class="info-row">
					<text class="info-label">创建时间</text>
					<text class="info-value info-time">
						{{ formatTime(detailData.create_date) }}
					</text>
				</view>
			</view>

			<!-- 操作按钮 -->
			<view class="btn-group">
				<button class="btn-edit" @click="startEdit">编辑</button>
				<button class="btn-delete" @click="onDelete">删除</button>
			</view>
		</view>

		<!-- 编辑模式 -->
		<view v-else class="edit-view">
			<uni-forms
				ref="formRef"
				:model="editData"
				:rules="formRules"
				label-width="80"
				label-align="right"
			>
				<uni-forms-item label="姓名" name="username" required>
					<uni-easyinput
						v-model="editData.username"
						placeholder="请输入姓名"
						trim="both"
					/>
				</uni-forms-item>

				<uni-forms-item label="性别" name="gender">
					<uni-data-checkbox
						v-model="editData.gender"
						:localdata="genderOptions"
					/>
				</uni-forms-item>

				<uni-forms-item label="手机号" name="mobile" required>
					<uni-easyinput
						v-model="editData.mobile"
						placeholder="请输入手机号"
						trim="both"
						type="number"
						@input="editData.mobile = String($event).replace(/[^\d\-+]/g, '')"
					/>
				</uni-forms-item>

				<uni-forms-item label="邮箱" name="email">
					<uni-easyinput
						v-model="editData.email"
						placeholder="请输入邮箱（选填）"
						trim="both"
					/>
				</uni-forms-item>

				<uni-forms-item label="备注" name="comment">
					<uni-easyinput
						v-model="editData.comment"
						type="textarea"
						placeholder="请输入备注（选填）"
						maxlength="200"
						auto-height
					/>
				</uni-forms-item>
			</uni-forms>

			<view class="btn-group">
				<button class="btn-submit" :loading="submitting" @click="onSave">保存</button>
				<button class="btn-cancel" @click="cancelEdit">取消</button>
			</view>
		</view>
	</view>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { onLoad } from '@dcloudio/uni-app';
import { genderText, genderClass, genderOptions } from './utils.js';

const formRef = ref(null);
const isEditing = ref(false);
const submitting = ref(false);
const contactId = ref('');

// 查看模式数据
const detailData = reactive({
	username: '',
	gender: 0,
	mobile: '',
	email: '',
	comment: '',
	create_date: null,
});

// 编辑模式数据
const editData = reactive({
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

onLoad((options) => {
	if (options._id) {
		contactId.value = options._id;
		loadDetail(options._id);
	}
});

// 加载详情
function loadDetail(id) {
	const db = uniCloud.database();
	db.collection('opendb-contacts')
		.doc(id)
		.get()
		.then((res) => {
			if (res.result.data && res.result.data.length > 0) {
				const data = res.result.data[0];
				// 先重置所有字段为默认值，避免切换联系人时残留旧字段
				Object.assign(detailData, {
					username: '',
					gender: 0,
					mobile: '',
					email: '',
					comment: '',
					create_date: null,
				});
				Object.assign(detailData, data);
			}
		})
		.catch((err) => {
			uni.showToast({ title: '加载失败', icon: 'error' });
			console.error(err);
		});
}

// 进入编辑模式
function startEdit() {
	// 回填数据
	editData.username = detailData.username || '';
	editData.gender = detailData.gender ?? 0;
	editData.mobile = detailData.mobile || '';
	editData.email = detailData.email || '';
	editData.comment = detailData.comment || '';
	isEditing.value = true;
}

// 取消编辑
function cancelEdit() {
	isEditing.value = false;
}

// 保存更新
function onSave() {
	if (submitting.value) return;
	formRef.value.validate().then((valid) => {
		if (valid) {
			submitting.value = true;
			const db = uniCloud.database();
			const data = {};
			Object.keys(editData).forEach((key) => {
				if (editData[key] !== null && editData[key] !== undefined) {
					data[key] = editData[key];
				}
			});
			db.collection('opendb-contacts')
				.doc(contactId.value)
				.update(data)
				.then(() => {
					uni.showToast({ title: '保存成功', icon: 'success' });
					// 更新查看模式数据（使用完整的 editData，避免过滤后遗漏已清空字段）
					Object.assign(detailData, editData);
					isEditing.value = false;
				})
				.catch((err) => {
					uni.showToast({ title: '保存失败', icon: 'error' });
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

// 删除
function onDelete() {
	uni.showModal({
		title: '确认删除',
		content: `确定要删除联系人「${detailData.username}」吗？删除后不可恢复。`,
		confirmColor: '#dd524d',
		success: (res) => {
			if (res.confirm) {
				const db = uniCloud.database();
				db.collection('opendb-contacts')
					.doc(contactId.value)
					.remove()
					.then(() => {
						uni.showToast({ title: '删除成功', icon: 'success' });
						setTimeout(() => {
							uni.navigateBack();
						}, 800);
					})
					.catch((err) => {
						uni.showToast({ title: '删除失败', icon: 'error' });
						console.error(err);
					});
			}
		},
	});
}

// 拨打电话
function onCallPhone(phone) {
	if (phone) {
		uni.makePhoneCall({
			phoneNumber: phone,
		});
	}
}

// 格式化时间（兼容 uniCloud 时间戳：数字、ISO 字符串、{ $date: "..." } 对象）
function formatTime(timestamp) {
	if (!timestamp) return '未知';
	let ms;
	if (typeof timestamp === 'object' && timestamp.$date) {
		const val = timestamp.$date;
		ms = typeof val === 'number' ? val : new Date(val).getTime();
	} else if (typeof timestamp === 'number') {
		ms = timestamp;
	} else {
		ms = new Date(timestamp).getTime();
	}
	if (isNaN(ms)) return '未知';
	const date = new Date(ms);
	const y = date.getFullYear();
	const m = String(date.getMonth() + 1).padStart(2, '0');
	const d = String(date.getDate()).padStart(2, '0');
	const h = String(date.getHours()).padStart(2, '0');
	const min = String(date.getMinutes()).padStart(2, '0');
	return `${y}-${m}-${d} ${h}:${min}`;
}

</script>

<style scoped>
.page-container {
	background-color: #f5f5f5;
	min-height: 100vh;
}

/* 查看模式 */
.detail-view {
	padding-bottom: 40rpx;
}

.detail-header {
	display: flex;
	flex-direction: column;
	align-items: center;
	padding: 60rpx 0 40rpx;
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.detail-avatar {
	width: 120rpx;
	height: 120rpx;
	border-radius: 50%;
	background: rgba(255, 255, 255, 0.3);
	display: flex;
	align-items: center;
	justify-content: center;
	margin-bottom: 20rpx;
}

.detail-avatar-text {
	color: #fff;
	font-size: 48rpx;
	font-weight: bold;
}

.detail-name {
	font-size: 36rpx;
	color: #fff;
	font-weight: bold;
}

.detail-gender {
	font-size: 22rpx;
	padding: 4rpx 16rpx;
	border-radius: 20rpx;
	background: rgba(255, 255, 255, 0.3);
	color: #fff;
	margin-top: 10rpx;
}

/* 信息卡片 */
.info-card {
	margin: 30rpx;
	background: #fff;
	border-radius: 16rpx;
	overflow: hidden;
}

.info-row {
	display: flex;
	align-items: center;
	padding: 28rpx 30rpx;
	border-bottom: 1rpx solid #f5f5f5;
}

.info-row:last-child {
	border-bottom: none;
}

.info-label {
	font-size: 28rpx;
	color: #999;
	width: 140rpx;
	flex-shrink: 0;
}

.info-value {
	font-size: 28rpx;
	color: #333;
	flex: 1;
}

.info-value:active {
	color: #667eea;
}

.info-time {
	font-size: 24rpx;
	color: #aaa;
}

.info-action {
	margin-left: 10rpx;
}

/* 按钮组 */
.btn-group {
	padding: 40rpx 30rpx 0;
}

.btn-edit {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	color: #fff;
	border-radius: 50rpx;
	height: 88rpx;
	line-height: 88rpx;
	font-size: 32rpx;
	border: none;
}

.btn-edit::after {
	border: none;
}

.btn-delete {
	margin-top: 24rpx;
	background: #fff;
	color: #dd524d;
	border-radius: 50rpx;
	height: 88rpx;
	line-height: 88rpx;
	font-size: 32rpx;
	border: 1rpx solid #dd524d;
}

.btn-delete::after {
	border: none;
}

/* 编辑模式 */
.edit-view {
	padding: 30rpx;
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

.gender-male {
	color: #1976d2;
}

.gender-female {
	color: #c2185b;
}

.gender-unknown {
	color: #fff;
	background: rgba(255, 255, 255, 0.25);
}
</style>
