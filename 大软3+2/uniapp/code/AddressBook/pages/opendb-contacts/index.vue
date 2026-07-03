<template>
	<view class="page-container">
		<!-- 搜索栏 -->
		<view class="search-wrapper">
			<uni-search-bar
				v-model="searchText"
				placeholder="搜索姓名或手机号"
				@confirm="onSearch"
				@cancel="onClearSearch"
				@clear="onClearSearch"
			/>
		</view>

		<!-- 联系人列表 -->
		<unicloud-db
			ref="udb"
			v-slot:default="{ data, loading, error }"
			collection="opendb-contacts"
			field="_id, username, gender, mobile, email, comment, create_date"
			:where="where"
			orderby="create_date desc"
			:page-size="1"
			loadtime="manual"
		>
			<!-- 加载中 -->
			<view v-if="loading" class="loading-wrapper">
				<uni-load-more status="loading" />
			</view>

			<!-- 错误态 -->
			<view v-else-if="error" class="error-wrapper">
				<uni-icons type="info-filled" size="48" color="#ddd" />
				<text class="error-text">加载失败，请下拉重试</text>
			</view>

			<!-- 空状态 / 搜索无结果 -->
			<view v-else-if="!data || data.length === 0" class="empty-wrapper">
				<image class="empty-image" src="/static/logo.png" mode="aspectFit" />
				<text class="empty-text">{{ searchText ? '没有匹配的联系人' : '暂无联系人' }}</text>
				<text class="empty-hint">{{ searchText ? '换个关键词试试' : '点击右下角按钮添加新联系人' }}</text>
			</view>

			<!-- 列表数据 -->
			<view v-else class="list-wrapper">
				<uni-swipe-action>
					<uni-swipe-action-item
						v-for="item in data"
						:key="item._id"
						:right-options="swipeOptions"
						@click="onSwipeClick($event, item)"
					>
						<view class="list-item" @click="goDetail(item._id)">
							<view class="avatar">
								<text class="avatar-text">{{ item.username ? item.username.charAt(0) : '?' }}</text>
							</view>
							<view class="info">
								<view class="name-row">
									<text class="name">{{ item.username || '未命名' }}</text>
									<text class="gender-tag" :class="genderClass(item.gender)">
										{{ genderText(item.gender) }}
									</text>
								</view>
								<text class="mobile">{{ item.mobile || '未填写电话' }}</text>
							</view>
							<uni-icons type="right" size="16" color="#ccc" />
						</view>
					</uni-swipe-action-item>
				</uni-swipe-action>

				<!-- 触底加载状态 -->
				<view class="load-more-footer">
					<uni-load-more
						v-if="loadMoreStatus === 'loading'"
						status="loading"
						contentText="加载中..."
					/>
					<uni-load-more
						v-else-if="loadMoreStatus === 'noMore'"
						status="noMore"
						contentText="没有更多了"
					/>
				</view>
			</view>
		</unicloud-db>

		<!-- 浮动新增按钮 -->
		<view class="fab-btn" @click="goAdd">
			<uni-icons type="plus" size="28" color="#fff" />
		</view>
	</view>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { onPullDownRefresh, onReachBottom, onShow } from '@dcloudio/uni-app';
import { genderText, genderClass } from './utils.js';

const udb = ref(null);
const searchText = ref('');
const loadMoreStatus = ref('more'); // 'more' | 'loading' | 'noMore'

// 服务端模糊搜索：JQL test 运算符实现 LIKE '%keyword%'
const where = computed(() => {
	const keyword = searchText.value.trim();
	if (!keyword) return '';
	return `${new RegExp(keyword, 'i')}.test(username) || ${new RegExp(keyword, 'i')}.test(mobile)`;
});

// 左滑出现的操作按钮（uni-swipe-action-item 的 right-options 对应左滑出现）
const swipeOptions = [
	{ text: '编辑', style: { backgroundColor: '#007aff' } },
	{ text: '删除', style: { backgroundColor: '#dd524d' } },
];

onMounted(() => {
	loadData();
});

// 从新增/编辑/详情页返回时自动刷新数据
onShow(() => {
	loadData();
});

function loadData() {
	if (udb.value) {
		loadMoreStatus.value = 'more';
		udb.value.refresh();
	}
}

function onSearch() {
	loadData();
}

function onClearSearch() {
	searchText.value = '';
	loadData();
}

// 下拉刷新
onPullDownRefresh(() => {
	loadData();
	uni.stopPullDownRefresh();
});

// 触底加载更多
onReachBottom(async () => {
	if (loadMoreStatus.value !== 'more') return;
	if (!udb.value) return;
	loadMoreStatus.value = 'loading';
	try {
		await udb.value.loadMore();
		// loadMore 之后 unicloud-db 内部会更新 pagination，检查是否还有更多
		const pagination = udb.value.data?.pagination;
		if (pagination && pagination.current * pagination.size >= pagination.count) {
			loadMoreStatus.value = 'noMore';
		} else {
			loadMoreStatus.value = 'more';
		}
	} catch (e) {
		console.error('加载更多失败', e);
		loadMoreStatus.value = 'more';
	}
});

// 跳转详情
function goDetail(id) {
	uni.navigateTo({
		url: `/pages/opendb-contacts/detail?_id=${id}`,
	});
}

// 跳转新增
function goAdd() {
	uni.navigateTo({
		url: '/pages/opendb-contacts/add',
	});
}

// 左滑/右滑操作
function onSwipeClick(e, item) {
	if (e.content.text === '编辑') {
		goDetail(item._id);
	} else if (e.content.text === '删除') {
		onDelete(item);
	}
}

// 删除确认
function onDelete(item) {
	uni.showModal({
		title: '确认删除',
		content: `确定要删除联系人「${item.username}」吗？删除后不可恢复。`,
		confirmColor: '#dd524d',
		success: (res) => {
			if (res.confirm) {
				const db = uniCloud.database();
				db.collection('opendb-contacts')
					.doc(item._id)
					.remove()
					.then(() => {
						uni.showToast({ title: '删除成功', icon: 'success' });
						loadData();
					})
					.catch((err) => {
						uni.showToast({ title: '删除失败', icon: 'error' });
						console.error(err);
					});
			}
		},
	});
}

</script>

<style scoped>
.page-container {
	min-height: 100vh;
	background-color: #f5f5f5;
}

.search-wrapper {
	background-color: #fff;
	padding: 10rpx 0;
}

.loading-wrapper,
.error-wrapper,
.empty-wrapper {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	padding: 120rpx 0;
}

.error-text,
.empty-text {
	font-size: 28rpx;
	color: #999;
	margin-top: 20rpx;
}

.empty-hint {
	font-size: 24rpx;
	color: #bbb;
	margin-top: 10rpx;
}

.empty-image {
	width: 200rpx;
	height: 200rpx;
	opacity: 0.5;
}

.list-wrapper {
	padding: 0 0 120rpx 0;
}

.list-item {
	display: flex;
	align-items: center;
	padding: 24rpx 30rpx;
	background-color: #fff;
	border-bottom: 1rpx solid #f0f0f0;
}

.list-item:active {
	background-color: #f9f9f9;
}

.avatar {
	width: 80rpx;
	height: 80rpx;
	border-radius: 50%;
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	display: flex;
	align-items: center;
	justify-content: center;
	margin-right: 24rpx;
	flex-shrink: 0;
}

.avatar-text {
	color: #fff;
	font-size: 32rpx;
	font-weight: bold;
}

.info {
	flex: 1;
	overflow: hidden;
}

.name-row {
	display: flex;
	align-items: center;
	gap: 12rpx;
}

.name {
	font-size: 30rpx;
	color: #333;
	font-weight: 500;
}

.gender-tag {
	font-size: 20rpx;
	padding: 2rpx 12rpx;
	border-radius: 20rpx;
}

.gender-male {
	background-color: #e3f2fd;
	color: #1976d2;
}

.gender-female {
	background-color: #fce4ec;
	color: #c2185b;
}

.gender-unknown {
	background-color: #e8e8e8;
	color: #666;
}

.mobile {
	font-size: 24rpx;
	color: #888;
	margin-top: 6rpx;
	display: block;
}

.load-more-footer {
	padding: 16rpx 0 100rpx 0;
}

.fab-btn {
	position: fixed;
	right: 40rpx;
	bottom: 80rpx;
	width: 100rpx;
	height: 100rpx;
	border-radius: 50%;
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	display: flex;
	align-items: center;
	justify-content: center;
	box-shadow: 0 6rpx 20rpx rgba(102, 126, 234, 0.5);
	z-index: 100;
}

.fab-btn:active {
	transform: scale(0.92);
}
</style>
