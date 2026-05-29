// index.js
const defaultAvatarUrl = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0'
const AuthService = require('../../utils/auth.service');

Page({
  data: {
    motto: 'Hello World',
    userInfo: {
      avatarUrl: defaultAvatarUrl,
      nickName: '',
    },
    hasUserInfo: false,
  },

  onShow() {
    // 每次页面显示时从本地缓存读取最新用户信息
    this.loadUserInfo();
  },

  /** 从 AuthService 缓存加载用户头像和昵称 */
  loadUserInfo() {
    const cachedUser = AuthService.getUserInfo() || {};
    const avatarUrl = cachedUser.avatarUrl || defaultAvatarUrl;
    const nickName = cachedUser.nickname || '';
    this.setData({
      'userInfo.avatarUrl': avatarUrl,
      'userInfo.nickName': nickName,
      hasUserInfo: !!(nickName && avatarUrl !== defaultAvatarUrl),
    });
  },

  bindViewTap() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
})
