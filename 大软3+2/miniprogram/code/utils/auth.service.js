/**
 * AuthService - 统一认证工具类
 *
 * 统一处理两种登录方式（微信授权 / 手机号验证码）的本地存储，
 * 避免业务层需要区分不同登录来源的存储结构。
 *
 * Storage 结构（key = 'auth'）：
 * {
 *   token: string,
 *   loginType: 'wechat' | 'phone',
 *   userInfo: object,
 *   expireAt: number   // 时间戳，默认 7 天后过期
 * }
 */

const AUTH_KEY = 'auth';
const DEFAULT_EXPIRE_DAYS = 7;

class AuthService {
  /**
   * 统一写入认证信息
   * @param {string} token
   * @param {'wechat'|'phone'} loginType
   * @param {object} userInfo
   * @param {number} expireDays 有效期天数，默认 7 天
   */
  static setAuth(token, loginType, userInfo, expireDays = DEFAULT_EXPIRE_DAYS) {
    const expireAt = Date.now() + expireDays * 24 * 60 * 60 * 1000;
    const authData = { token, loginType, userInfo, expireAt };
    console.log('[AuthService] setAuth 写入:', JSON.stringify(authData));
    wx.setStorageSync(AUTH_KEY, authData);
    // 立即读取验证
    const verify = wx.getStorageSync(AUTH_KEY);
    console.log('[AuthService] setAuth 验证读取:', JSON.stringify(verify));
  }

  /** 读取完整认证信息 */
  static getAuth() {
    const auth = wx.getStorageSync(AUTH_KEY) || null;
    console.log('[AuthService] getAuth 读取:', JSON.stringify(auth));
    return auth;
  }

  /** 获取 token */
  static getToken() {
    const auth = this.getAuth();
    return auth?.token || null;
  }

  /** 获取登录方式 */
  static getLoginType() {
    const auth = this.getAuth();
    return auth?.loginType || null;
  }

  /** 获取用户信息 */
  static getUserInfo() {
    const auth = this.getAuth();
    return auth?.userInfo || null;
  }

  /** 判断是否已登录（且 token 未过期） */
  static isLoggedIn() {
    const auth = this.getAuth();
    if (!auth || !auth.token) return false;
    if (auth.expireAt && Date.now() > auth.expireAt) {
      this.clearAuth();
      return false;
    }
    return true;
  }

  /** 检查登录状态，未登录或过期时跳转登录页 */
  static checkLogin(redirectUrl = '/pages/login/login') {
    if (!this.isLoggedIn()) {
      wx.redirectTo({ url: redirectUrl });
      return false;
    }
    return true;
  }

  /** 清除认证信息 */
  static clearAuth() {
    wx.removeStorageSync(AUTH_KEY);
  }

}

module.exports = AuthService;
