/**
 * 用户服务 - 基于 openid 的用户查找/注册逻辑
 *
 * 当前使用内存存储，生产环境可替换为数据库（Prisma/Drizzle 等）
 */

export interface UserInfo {
  openid: string
  unionid?: string
  nickname?: string
  avatarUrl?: string
  createdAt: Date
}

// 内存用户存储（开发用）
const users = new Map<string, UserInfo>()

/**
 * 根据 openid 查找或注册用户
 * 返回用户信息（不含 session_key，遵循安全规范）
 */
export async function findOrCreateUser(
  openid: string,
  unionid?: string
): Promise<UserInfo> {
  let user = users.get(openid)

  if (user) {
    // 已存在用户，更新 unionid（如果之前没有）
    if (unionid && !user.unionid) {
      user.unionid = unionid
    }
    return user
  }

  // 新用户注册
  user = {
    openid,
    unionid,
    createdAt: new Date()
  }
  users.set(openid, user)

  return user
}

/**
 * 更新用户信息（昵称、头像等）
 */
export async function updateUserProfile(
  openid: string,
  data: Partial<Pick<UserInfo, 'nickname' | 'avatarUrl'>>
): Promise<UserInfo | null> {
  const user = users.get(openid)
  if (!user) return null

  Object.assign(user, data)
  return user
}
