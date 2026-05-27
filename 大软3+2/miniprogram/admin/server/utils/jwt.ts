/**
 * JWT 工具 - 签发与验证 token
 * 使用 jose 库（轻量、原生 ESM 支持）
 */
import { SignJWT, jwtVerify, type JWTPayload } from 'jose'

const TOKEN_EXPIRY = '7d' // token 有效期 7 天

interface UserJWTPayload extends JWTPayload {
  openid: string
  unionid?: string
}

/**
 * 使用 HS256 算法签发 JWT token
 */
export async function signToken(openid: string, unionid?: string): Promise<string> {
  const { jwtSecret } = useRuntimeConfig()
  const encoder = new TextEncoder()

  return new SignJWT({ openid, ...(unionid && { unionid }) })
    .setProtectedHeader({ alg: 'HS256' })
    .setIssuedAt()
    .setExpirationTime(TOKEN_EXPIRY)
    .sign(encoder.encode(jwtSecret))
}

/**
 * 验证并解析 JWT token
 */
export async function verifyToken(token: string): Promise<UserJWTPayload> {
  const { jwtSecret } = useRuntimeConfig()
  const encoder = new TextEncoder()

  const { payload } = await jwtVerify(token, encoder.encode(jwtSecret))
  return payload as UserJWTPayload
}
