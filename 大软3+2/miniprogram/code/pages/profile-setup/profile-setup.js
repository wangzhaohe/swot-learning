// pages/profile-setup/profile-setup.js
const AuthService = require('../../utils/auth.service');

const defaultAvatarUrl = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0'

Page({
  data: {
    avatarUrl: defaultAvatarUrl,
    nickName: '',
    canSubmit: false,
    isUploading: false,
  },

  onLoad() {
    // 如果已登录且已有昵称，直接跳转首页
    const userInfo = AuthService.getUserInfo() || {};
    if (userInfo.nickname) {
      wx.redirectTo({ url: '/pages/index/index' });
    }
  },

  // 选择头像
  onChooseAvatar(e) {
    const { avatarUrl } = e.detail;
    this.setData({
      avatarUrl,
      canSubmit: this.checkCanSubmit(avatarUrl, this.data.nickName),
    });
  },

  // 输入昵称
  onInputNickname(e) {
    const nickName = e.detail.value;
    this.setData({
      nickName,
      canSubmit: this.checkCanSubmit(this.data.avatarUrl, nickName),
    });
  },

  // 检查是否可以提交
  checkCanSubmit(avatarUrl, nickName) {
    return nickName && nickName.trim() && avatarUrl && avatarUrl !== defaultAvatarUrl;
  },

  // 提交资料
  async submitProfile() {
    if (!this.data.canSubmit) return;
    if (this.data.isUploading) return;

    const token = AuthService.getToken();
    if (!token) {
      wx.showToast({ title: '登录已过期，请重新登录', icon: 'none' });
      wx.redirectTo({ url: '/pages/login/login' });
      return;
    }

    this.setData({ isUploading: true });
    wx.showLoading({ title: '保存中...' });

    try {
      // 1. 先上传头像（如果是临时路径）
      let avatarUrl = this.data.avatarUrl;
      // 微信临时文件以 wxfile:// 或 http://tmp/ 开头
      if (avatarUrl && (avatarUrl.startsWith('wxfile://') || avatarUrl.startsWith('http://tmp/'))) {
        // 临时文件，需要上传到服务器
        const uploadedUrl = await this.uploadAvatar(token);
        avatarUrl = uploadedUrl;
      }

      // 2. 保存昵称到数据库
      await this.saveNickname(token, this.data.nickName.trim());

      wx.hideLoading();
      wx.showToast({ title: '设置成功', icon: 'success' });

      // 3. 更新本地缓存并跳转首页
      const auth = AuthService.getAuth();
      if (auth) {
        auth.userInfo = {
          ...auth.userInfo,
          nickname: this.data.nickName.trim(),
          avatarUrl: this.data.avatarUrl,
        };
        wx.setStorageSync('auth', auth);
      }

      setTimeout(() => {
        wx.redirectTo({ url: '/pages/index/index' });
      }, 1500);
    } catch (err) {
      wx.hideLoading();
      this.setData({ isUploading: false });
      wx.showToast({ title: err.message || '保存失败，请重试', icon: 'none' });
    }
  },

  // 上传头像到服务器
  uploadAvatar(token) {
    return new Promise((resolve, reject) => {
      wx.uploadFile({
        url: 'http://10.1.254.117:3000/api/upload/avatar',
        filePath: this.data.avatarUrl,
        name: 'avatar',
        header: {
          'Authorization': `Bearer ${token}`,
        },
        success: (res) => {
          const data = JSON.parse(res.data);
          if (data.code === 200) {
            this.setData({ avatarUrl: data.data.avatarUrl });
            resolve(data.data.avatarUrl);
          } else {
            reject(new Error(data.message || '头像上传失败'));
          }
        },
        fail: (err) => {
          console.log('err', err);
          reject(new Error('头像上传网络错误'));
        }
      });
    });
  },

  // 保存昵称
  saveNickname(token, nickname) {
    return new Promise((resolve, reject) => {
      wx.request({
        url: 'http://localhost:3000/api/user/profile',
        method: 'POST',
        data: { nickname },
        header: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
        success: (res) => {
          if (res.statusCode === 200 && res.data?.code === 200) {
            resolve(res.data.data);
          } else {
            reject(new Error(res.data?.message || '昵称保存失败'));
          }
        },
        fail: () => {
          reject(new Error('网络错误，请重试'));
        }
      });
    });
  },
});
