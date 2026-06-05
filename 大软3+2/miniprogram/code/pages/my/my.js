// pages/my/my.js
const AuthService = require('../../utils/auth.service');
const defaultAvatarUrl = '/images/avatar_gray.png';

Page({
  data: {
    userInfo: {
      avatarUrl: defaultAvatarUrl,
      nickName: '',
    },
    hasUserInfo: false,
  },

  onShow() {
    this.loadUserInfo();
  },

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

  // 跳转到个人资料设置
  goToProfile() {
    wx.navigateTo({
      url: '/pages/profile-setup/profile-setup'
    });
  },

  // 跳转到帮助页面
  goToHelp() {
    wx.navigateTo({
      url: '/pages/help/help'
    });
  },

  // 退出登录
  logout() {
    wx.showModal({
      title: '提示',
      content: '确定要退出登录吗？',
      success: (res) => {
        if (res.confirm) {
          wx.removeStorageSync('auth');
          wx.removeStorageSync('token');
          this.setData({
            'userInfo.avatarUrl': defaultAvatarUrl,
            'userInfo.nickName': '',
            hasUserInfo: false,
          });
          wx.showToast({ title: '已退出登录', icon: 'success' });
        }
      }
    });
  },
});
