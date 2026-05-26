// pages/login/login.js
Page({

  data: {
    phone: '',
    code: '',
    agreed: true,
    countdown: 0,
    loading: false,
    phoneError: false,
    codeError: false,
    statusBarHeight: 0,
    navPlaceholderHeight: 88,
    backBtnTop: 48,
  },

  onLoad() {
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

  // ===== 手机号输入 =====
  onPhoneInput(e) {
    let val = e.detail.value.replace(/\D/g, '').slice(0, 11);
    this.setData({ phone: val, phoneError: false });
  },

  // ===== 验证手机号格式 =====
  validatePhone() {
    const phone = this.data.phone;
    if (phone && !/^1[3-9]\d{9}$/.test(phone)) {
      this.setData({ phoneError: true });
    } else {
      this.setData({ phoneError: false });
    }
  },

  // ===== 清除手机号错误状态 =====
  clearPhoneError() {
    this.setData({ phoneError: false });
  },

  // ===== 清除手机号 =====
  clearPhone() {
    this.setData({ phone: '', phoneError: false });
  },

  // ===== 验证码输入 =====
  onCodeInput(e) {
    let val = e.detail.value.replace(/\D/g, '').slice(0, 6);
    this.setData({ code: val, codeError: false });
  },

  // ===== 清除验证码错误状态 =====
  clearCodeError() {
    this.setData({ codeError: false });
  },

  // ===== 清除验证码 =====
  clearCode() {
    this.setData({ code: '', codeError: false });
  },

  // ===== 发送验证码 =====
  sendCode() {
    const phone = this.data.phone;
    const countdown = this.data.countdown;
    if (countdown > 0) return;
    if (!/^1[3-9]\d{9}$/.test(phone)) {
      wx.showToast({ title: '请输入正确的手机号', icon: 'none' });
      this.setData({ phoneError: true });
      return;
    }
    wx.showToast({ title: '验证码已发送', icon: 'success' });
    this.setData({ countdown: 60 });
    this.startCountdown();
  },

  // ===== 倒计时 =====
  startCountdown() {
    if (this._timer) clearInterval(this._timer);
    this._timer = setInterval(() => {
      if (this.data.countdown <= 1) {
        clearInterval(this._timer);
        this._timer = null;
        this.setData({ countdown: 0 });
      } else {
        this.setData({ countdown: this.data.countdown - 1 });
      }
    }, 1000);
  },

  // ===== 登录 =====
  doLogin() {
    const { phone, code, agreed, loading } = this.data;
    if (loading) return;
    if (!agreed) {
      wx.showToast({ title: '请先同意用户协议', icon: 'none' });
      return;
    }
    if (!/^1[3-9]\d{9}$/.test(phone)) {
      wx.showToast({ title: '请输入正确的手机号', icon: 'none' });
      this.setData({ phoneError: true });
      return;
    }
    if (!code || code.length < 4) {
      wx.showToast({ title: '请输入验证码', icon: 'none' });
      this.setData({ codeError: true });
      return;
    }
    this.setData({ loading: true });
    wx.showToast({ title: '登录成功，跳转中...', icon: 'none', duration: 2000 });
    setTimeout(() => {
      this.setData({ loading: false });
      wx.redirectTo({ url: '/pages/index/index' });
    }, 1500);
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
          wx.showToast({ title: '登录成功', icon: 'success' });
          setTimeout(() => {
            wx.redirectTo({ url: '/pages/index/index' });
          }, 1500);
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

  // ===== 显示协议 =====
  showAgreement() {
    wx.showModal({
      title: '用户协议',
      content: '请阅读用户协议相关内容...',
      showCancel: false,
    });
  },

  // ===== 显示隐私政策 =====
  showPrivacy() {
    wx.showModal({
      title: '隐私政策',
      content: '请阅读隐私政策相关内容...',
      showCancel: false,
    });
  },

  // ===== 帮助 =====
  showHelp() {
    wx.showModal({
      title: '帮助',
      content: '客服：400-xxx-xxxx',
      showCancel: false,
    });
  },

  // ===== 页面卸载时清除定时器 =====
  onUnload() {
    if (this._timer) {
      clearInterval(this._timer);
      this._timer = null;
    }
  },
});