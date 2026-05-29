// server/api/users.get.ts
import { getSurrealDB } from '../utils/surrealdb'

export default defineEventHandler(async (event) => {
  try {
    const db = await getSurrealDB()
    
    // 查询所有用户，按创建时间倒序
    const result = await db.query(`
      SELECT * FROM user ORDER BY createdAt DESC;
    `)
    
    const users = result[0] || []
    
    return {
      success: true,
      data: users
    }
  } catch (error: any) {
    console.error('[API] 获取用户列表失败:', error)
    throw createError({
      statusCode: 500,
      statusMessage: error.message || '获取用户列表失败'
    })
  }
})
