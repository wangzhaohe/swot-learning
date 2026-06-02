// pages/posts/posts.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      newsList: [
        {
          id: 1,
          avatar: "https://i.pravatar.cc/150?img=1",
          publishTime: "2025-12-12 12:12:12",
          title: "2026 LPL 夏季赛季后赛观赛指南",
          imageUrl: "https://images.unsplash.com/photo-1493711662062-fa541adb3fc8?w=750&h=300&fit=crop",
          desc: "2026年LPL夏季赛季后赛即将开战，多支顶级战队蓄势待发，争夺世界赛名额。",
          likeCount: 20,
          viewCount: 100
        },
        {
          id: 2,
          avatar: "https://i.pravatar.cc/150?img=2",
          publishTime: "2025-12-13 09:30:00",
          title: "AI 技术突破：大模型迎来新一轮革新",
          imageUrl: "https://images.unsplash.com/photo-1677442136019-21780ecad995?w=750&h=300&fit=crop",
          desc: "多家科技巨头发布最新大语言模型，在推理能力和多模态理解方面取得显著进展。",
          likeCount: 56,
          viewCount: 320
        },
        {
          id: 3,
          avatar: "https://i.pravatar.cc/150?img=3",
          publishTime: "2025-12-14 18:45:00",
          title: "碳中和目标下的绿色能源发展新趋势",
          imageUrl: "https://images.unsplash.com/photo-1473341304170-971dccb5ac1e?w=750&h=300&fit=crop",
          desc: "全球各国加速推进可再生能源建设，光伏和风电装机容量创历史新高。",
          likeCount: 38,
          viewCount: 215
        }
      ]
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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})