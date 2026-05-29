/**
 * 用户服务 - 基于 SurrealDB 的用户查找/注册逻辑
 *
 * 使用 record ID 格式：user:{openid}，天然保证唯一性
 * 使用原始 SQL 查询（SDK v2 链式 API 在 SCHEMAFULL 表上有兼容问题）
 */

export interface UserInfo {
  openid: string
  unionid?: string
  nickname?: string
  avatarUrl?: string
  createdAt: Date
}

/**
 * 根据 openid 查找或注册用户
 * 返回用户信息（不含 session_key，遵循安全规范）
 */
export async function findOrCreateUser(
  openid: string,
  unionid?: string
): Promise<UserInfo> {
  const db = await getSurrealDB()
  const recordId = `user:${openid}`

  // 按 record ID 查找
  const [rows] = await db.query(`SELECT * FROM ${recordId}`)
  const user = (rows as any[])?.[0] as (UserInfo & { id: string }) | undefined

  if (user) {
    // 已存在用户，补全 unionid（如果之前没有）
    if (unionid && !user.unionid) {
      await db.query(`UPDATE ${recordId} MERGE { unionid: $unionid }`, { unionid })
      user.unionid = unionid
    }
    return { ...user, openid }
  }

  // 新用户注册（unionid 可能为空，不传则不在 CREATE 中设置该字段）
  const createQuery = unionid
    ? `CREATE ${recordId} SET unionid = $unionid`
    : `CREATE ${recordId}`
  const bindings = unionid ? { unionid } : undefined
  const [created] = await db.query(createQuery, bindings as any)
  const newUser = (created as any[])?.[0] as (UserInfo & { id: string }) | undefined
  if (!newUser) throw new Error('用户创建失败')
  return { ...newUser, openid }
}

/**
 * 更新用户信息（昵称、头像等）
 */
export async function updateUserProfile(
  openid: string,
  data: Partial<Pick<UserInfo, 'nickname' | 'avatarUrl'>>
): Promise<UserInfo | null> {
  const db = await getSurrealDB()
  const recordId = `user:${openid}`

  // 先查找确认存在
  const [rows] = await db.query(`SELECT * FROM ${recordId}`)
  const user = (rows as any[])?.[0] as (UserInfo & { id: string }) | undefined
  if (!user) return null

  // 更新字段
  const [merged] = await db.query(`UPDATE ${recordId} MERGE $data`, { data })
  const updated = (merged as any[])?.[0] as (UserInfo & { id: string }) | undefined
  return updated ? { ...updated, openid } : null
}
