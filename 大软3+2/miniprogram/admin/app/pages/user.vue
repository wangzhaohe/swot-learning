<script setup lang="ts">
const { data, pending, error, refresh } = await useFetch('/api/users', {
	lazy: true
})

const users = computed(() => data.value?.data || [])
</script>

<template>
	<div class="user-management">
		<div class="page-header">
			<h1>用户管理</h1>
			<button @click="refresh" :disabled="pending" class="refresh-btn">
				{{ pending ? '刷新中...' : '刷新' }}
			</button>
		</div>

		<div v-if="pending" class="loading">加载中...</div>

		<div v-else-if="error" class="error">
			加载失败: {{ error.message }}
			<button @click="refresh">重试</button>
		</div>

		<div v-else-if="users.length === 0" class="empty">
			暂无用户数据
		</div>

		<div v-else class="user-table">
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>头像</th>
						<th>昵称</th>
						<th>注册时间</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="user in users" :key="user.id">
						<td class="id-cell">{{ user.id }}</td>
						<td class="avatar-cell">
							<img v-if="user.avatarUrl" :src="user.avatarUrl" :alt="user.nickname || '用户'"
								class="avatar" />
							<span v-else class="no-avatar">无</span>
						</td>
						<td>{{ user.nickname || '未设置' }}</td>
						<td>{{ new Date(user.createdAt).toLocaleString('zh-CN') }}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</template>

<style scoped>
.user-management {
	padding: 20px;
	max-width: 1200px;
	margin: 0 auto;
}

.page-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

.page-header h1 {
	margin: 0;
	color: #333;
}

.refresh-btn {
	padding: 8px 16px;
	background: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.refresh-btn:disabled {
	background: #ccc;
	cursor: not-allowed;
}

.loading,
.error,
.empty {
	text-align: center;
	padding: 40px;
	color: #666;
}

.error {
	color: #dc3545;
}

.user-table {
	overflow-x: auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	background: white;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

th,
td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #e0e0e0;
}

th {
	background: #f8f9fa;
	font-weight: 600;
	color: #333;
}

.id-cell,
.unionid-cell {
	font-family: monospace;
	font-size: 0.9em;
	color: #666;
}

.avatar {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	object-fit: cover;
}

.no-avatar {
	color: #999;
	font-size: 0.9em;
}

tr:hover {
	background: #f8f9fa;
}
</style>
