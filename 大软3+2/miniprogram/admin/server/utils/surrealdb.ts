import { Surreal } from 'surrealdb'

let db: Surreal | null = null

/**
 * 获取 SurrealDB 连接单例
 * 首次调用时完成连接、登录、指定命名空间/数据库和 schema 初始化
 */
export async function getSurrealDB(): Promise<Surreal> {
  if (db) return db

  const config = useRuntimeConfig()
  const { url, user, pass, namespace, database } = config.surrealdb

  console.log(`[SurrealDB] 正在连接 ${url}，ns=${namespace}，db=${database}`)

  db = new Surreal()
  try {
    await db.connect(url, {
      namespace,
      database,
      authentication: { username: user, password: pass },
    })

    // 初始化 schema（SCHEMAFULL 模式）
    await db.query(`
      DEFINE TABLE IF NOT EXISTS user SCHEMAFULL;
      DEFINE FIELD IF NOT EXISTS unionid   ON user TYPE option<string>;
      DEFINE FIELD IF NOT EXISTS nickname  ON user TYPE option<string>;
      DEFINE FIELD IF NOT EXISTS avatarUrl ON user TYPE option<string>;
      DEFINE FIELD IF NOT EXISTS createdAt ON user TYPE datetime DEFAULT time::now();
    `)

    console.log('[SurrealDB] 连接成功')
    return db
  } catch (err) {
    db = null
    console.error('[SurrealDB] 连接失败:', err)
    throw err
  }
}
