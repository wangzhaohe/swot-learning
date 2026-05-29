// pages/help/help.js

Page({
  data: {
    openFaq: [false, false, false, false, false], // 控制5个FAQ的展开状态
  },

  onLoad() {
    // 页面加载时的初始化
  },

  // ===== 返回上一页 =====
  goBack() {
    wx.navigateBack({ delta: 1 });
  },

  // ===== 切换FAQ展开/收起 =====
  toggleFaq(e) {
    const index = e.currentTarget.dataset.index;
    const openFaq = this.data.openFaq;
    openFaq[index] = !openFaq[index];
    this.setData({ openFaq });
  },

  // ===== 拨打电话 =====
  makePhoneCall() {
    wx.makePhoneCall({
      phoneNumber: '400-XXX-XXXX',
      fail() {
        wx.showToast({ title: '拨打失败', icon: 'none' });
      },
    });
  },

  // ===== 打开反馈页面 =====
  openFeedback() {
    // 跳转到反馈页面（如果有的话），或者显示反馈弹窗
    wx.showToast({ title: '反馈功能开发中', icon: 'none' });
    
    // 如果已有反馈页面，可以这样跳转：
    // wx.navigateTo({ url: '/pages/feedback/feedback' });
  },
});
