// pages/post-detail/post-detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    news: {}
  },
  onShareAppMessage() {
    const news = this.data.news;
    console.log("news:", news.title, news.id)
    return {
      title: news.title,
      path: '/pages/post-detail/post-detail?newsid=' + news.id,
      imageUrl: '/images/me.jpg'
    };
  },

  // onShareTimeline() {
  //   return {
  //     title: '这是分享到朋友圈的新闻标题'
  //   };
  // },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 配置右上角分享按钮（分享到朋友圈需要显式启用）
    wx.showShareMenu({
      withShareTicket: true,
      menus: ["shareAppMessage"]  // 同时显示"发送给朋友"和"分享到朋友圈"
    });    
    console.log("options:", options)
    let id = options.newsid
    // 将此id 发送给后端 api 获取该新闻的详情
    let api_response = {
      id: "1",
      title: "2026 LPL 夏季赛季后赛观赛指南"
    }
    this.setData({
      news: api_response
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  }
})