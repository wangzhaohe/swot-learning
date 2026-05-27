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
    wx.setStorageSync(AUTH_KEY, authData);
  }

  /** 读取完整认证信息 */
  static getAuth() {
    return wx.getStorageSync(AUTH_KEY) || null;
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

  /**
   * 兼容旧版分散存储：迁移并清理旧 key
   * 旧 key: 'token', 'userInfo', 'phone'
   */
  static migrateOldStorage() {
    // 如果已有新版 auth，直接清理旧 key，避免旧数据覆盖新数据
    const currentAuth = wx.getStorageSync(AUTH_KEY);
    if (currentAuth) {
      wx.removeStorageSync('token');
      wx.removeStorageSync('userInfo');
      wx.removeStorageSync('phone');
      return;
    }
    const oldToken = wx.getStorageSync('token');
    const oldUserInfo = wx.getStorageSync('userInfo');
    if (oldToken && oldUserInfo) {
      // 旧数据无法区分登录方式，默认标记为 phone（因为旧版主要是手机号登录）
      this.setAuth(oldToken, 'phone', oldUserInfo);
    }
    wx.removeStorageSync('token');
    wx.removeStorageSync('userInfo');
    wx.removeStorageSync('phone');
  }
}

module.exports = AuthService;
