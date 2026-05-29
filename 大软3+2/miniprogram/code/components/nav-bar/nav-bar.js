// components/nav-bar/nav-bar.js
Component({
  properties: {
    title: {
      type: String,
      value: '',
    },
    showBack: {
      type: Boolean,
      value: true,
    },
    navHeight: {
      type: Number,
      value: 44,
    },
  },

  data: {
    statusBarHeight: 44,
  },

  lifetimes: {
    attached() {
      const sys = wx.getSystemInfoSync();
      this.setData({ statusBarHeight: sys.statusBarHeight || 44 });
    },
  },

  methods: {
    onBack() {
      this.triggerEvent('back');
    },
  },
});
