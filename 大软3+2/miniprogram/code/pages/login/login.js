// pages/login/login.js
const AuthService = require('../../utils/auth.service');

Page({

  data: {
    agreed: true,
    statusBarHeight: 0,
    navPlaceholderHeight: 88,
    backBtnTop: 48,
  },

  onLoad() {
    console.log('[login] onLoad 当前 auth:', JSON.stringify(AuthService.getAuth()));

    const sys = wx.getSystemInfoSync();
    const capsule = wx.getMenuButtonBoundingClientRect();
    // 占位区：从页面顶部到胶囊底部，保证内容不被遮挡
    const navPlaceholderHeight = capsule.bottom;
    // 返回按钮 top：让返回按钮中心对齐胶囊中心
    // 返回按钮高36px，所以 top = capsule.top + (capsule.height - 36) / 2
    const backBtnTop = capsule.top + (capsule.height - 36) / 2;
    this.setData({
      statusBarHeight: sys.statusBarHeight,
      navPlaceholderHeight: navPlaceholderHeight,
      backBtnTop: backBtnTop,
    });
  },

  // ===== 返回上页 =====
  goBack() {
    wx.navigateBack({ delta: 1, fail: () => { wx.redirectTo({ url: '/pages/index/index' }); } });
  },

  // ===== 微信授权登录 =====
  wechatLogin() {
    if (!this.data.agreed) {
      wx.showToast({ title: '请先同意用户协议', icon: 'none' });
      return;
    }
    wx.showLoading({ title: '正在获取授权...' });
    wx.login({
      success: (res) => {
        wx.hideLoading();
        if (res.code) {
          wx.showLoading({ title: '正在登录...' });
          wx.request({
            url: 'http://localhost:3000/api/login',
            method: 'POST',
            data: { code: res.code },
            success: (loginRes) => {
              wx.hideLoading();
              console.log('[login] 微信登录接口返回:', JSON.stringify(loginRes.data));
              if (loginRes.statusCode === 200 && loginRes.data?.code === 200) {
                const { token, loginType, userInfo } = loginRes.data.data || {};
                console.log('[login] 微信登录解构:', { token, loginType, userInfo });
                if (token) {
                  console.log('[login] 调用 setAuth 前:', JSON.stringify(AuthService.getAuth()));
                  AuthService.setAuth(token, loginType, userInfo);
                  console.log('[login] 调用 setAuth 后:', JSON.stringify(AuthService.getAuth()));
                  wx.showToast({ title: '登录成功', icon: 'success' });
                  setTimeout(() => {
                    wx.redirectTo({ url: '/pages/index/index' });
                  }, 1500);
                } else {
                  wx.showToast({ title: '登录失败，请重试', icon: 'none' });
                }
              } else {
                wx.showToast({ title: '登录失败，请重试', icon: 'none' });
              }
            },
            fail: (err) => {
              wx.hideLoading();
              console.error('登录请求失败:', err);
              wx.showToast({ title: '网络错误，请重试', icon: 'none' });
            }
          });
        } else {
          wx.showToast({ title: '授权失败，请重试', icon: 'none' });
        }
      },
      fail: () => {
        wx.hideLoading();
        wx.showToast({ title: '授权失败，请重试', icon: 'none' });
      }
    });
  },

  // ===== 切换协议勾选 =====
  toggleAgree() {
    this.setData({ agreed: !this.data.agreed });
  },

  // ===== 显示用户协议 =====
  showAgreement() {
    wx.navigateTo({ url: '/pages/doc-display/doc-display?type=agreement' });
  },

  // ===== 显示隐私政策 =====
  showPrivacy() {
    wx.navigateTo({ url: '/pages/doc-display/doc-display?type=privacy' });
  },

  // ===== 帮助 =====
  showHelp() {
    wx.navigateTo({ url: '/pages/help/help' });
  },
});