// pages/doc-display/doc-display.js

// ===== 用户协议内容 =====
const AGREEMENT_CONTENT = `
<div class="doc-title">司维者用户协议</div>
<div class="doc-version">版本号：V1.0 &nbsp;|&nbsp; 生效日期：2024年1月1日</div>

<div class="doc-section">
  <div class="section-title">一、协议的范围</div>
  <div class="section-content">欢迎使用"司维者"小程序（以下简称"本服务"）。本协议是您与司维者平台之间关于使用本服务所订立的契约。请您仔细阅读本协议，特别是免除或者限制责任的条款，此类条款将以加粗形式提示您注意。</div>
</div>

<div class="doc-section">
  <div class="section-title">二、服务内容</div>
  <div class="section-content">本服务是由司维者提供的3D打印、沙盘模型制作及数字孪生相关服务的平台，包括但不限于：</div>
  <div class="section-list">1. 3D打印服务：提供在线3D模型上传、报价、打印及配送服务；</div>
  <div class="section-list">2. 沙盘模型：提供沙盘模型设计、制作及安装服务；</div>
  <div class="section-list">3. 数字孪生：提供数字化建模、虚拟仿真及可视化服务；</div>
  <div class="section-list">4. 其他基于本平台提供的增值服务。</div>
</div>

<div class="doc-section">
  <div class="section-title">三、用户账号</div>
  <div class="section-content">1. 您需要通过微信授权登录方式注册并登录本服务，您的微信账号信息将作为您在本服务中的唯一身份标识。</div>
  <div class="section-content">2. 您应当妥善保管您的账号及个人信息，因您保管不善导致账号被他人使用所产生的任何损失，由您自行承担。</div>
  <div class="section-content">3. 您承诺不以任何方式转让或授权他人使用您的账号，否则由此产生的一切法律责任由您承担。</div>
</div>

<div class="doc-section">
  <div class="section-title">四、用户行为规范</div>
  <div class="section-content">您在使用本服务时，应当遵守中华人民共和国相关法律法规，不得利用本服务从事以下行为：</div>
  <div class="section-list">（1）发布、传播、储存违反国家法律、危害国家安全的内容；</div>
  <div class="section-list">（2）发布、传播、储存侵犯他人知识产权、肖像权、名誉权的内容；</div>
  <div class="section-list">（3）上传含有病毒、木马或其他恶意代码的文件；</div>
  <div class="section-list">（4）干扰本服务的正常运行，或入侵本服务的服务器；</div>
  <div class="section-list">（5）其他违反法律法规或损害本服务利益的行为。</div>
</div>

<div class="doc-section">
  <div class="section-title">五、知识产权</div>
  <div class="section-content">1. 本服务中由司维者提供的文字、图片、音频、视频、软件等内容的知识产权归司维者所有，未经许可，不得复制、修改、传播或用于商业用途。</div>
  <div class="section-content">2. 您上传至本服务的3D模型等文件的知识产权归您所有，但您同意授予司维者在全球范围内免费的、不可撤销的使用权，以便本服务为您提供打印、预览等技术支持。</div>
</div>

<div class="doc-section">
  <div class="section-title">六、免责声明</div>
  <div class="section-content">1. 本服务按"原样"提供，司维者不保证服务的不间断性、及时性、安全性或准确性。</div>
  <div class="section-content">2. 因不可抗力（包括但不限于自然灾害、政府行为、网络运营商故障等）导致服务中断或数据丢失的，司维者不承担责任。</div>
  <div class="section-content">3. 您理解并同意，在使用本服务过程中涉及的风险由您自行承担。</div>
</div>

<div class="doc-section">
  <div class="section-title">七、协议修改</div>
  <div class="section-content">司维者有权在必要时修改本协议条款，协议条款一旦发生变动，将会在本服务中公布修改后的协议。如果您不接受修改后的协议，可以停止使用本服务；如果您继续使用本服务，则视为您接受修改后的协议。</div>
</div>

<div class="doc-section">
  <div class="section-title">八、法律适用与争议解决</div>
  <div class="section-content">1. 本协议的订立、执行和解释及争议的解决均适用中华人民共和国法律。</div>
  <div class="section-content">2. 如双方就本协议内容或其执行发生任何争议，应尽量友好协商解决；协商不成时，任何一方均可向司维者所在地人民法院提起诉讼。</div>
</div>

<div class="doc-section">
  <div class="section-title">九、联系方式</div>
  <div class="section-content">如您对本协议有任何疑问，请通过以下方式联系我们：</div>
  <div class="section-list">客服电话：400-XXX-XXXX</div>
  <div class="section-list">客服邮箱：service@siweizhe.com</div>
  <div class="section-list">服务时间：工作日 9:00-18:00</div>
</div>
`;

// ===== 隐私政策内容 =====
const PRIVACY_CONTENT = `
<div class="doc-title">司维者隐私政策</div>
<div class="doc-version">版本号：V1.0 &nbsp;|&nbsp; 生效日期：2024年1月1日</div>

<div class="doc-section">
  <div class="section-title">一、引言</div>
  <div class="section-content">司维者（以下简称"我们"）非常重视您的个人信息和隐私保护。本隐私政策旨在向您说明我们如何收集、使用、存储和保护您的个人信息，以及您所享有的相关权利。请您在使用我们的服务前，仔细阅读并理解本政策。</div>
</div>

<div class="doc-section">
  <div class="section-title">二、个人信息的收集</div>
  <div class="section-content">在您使用本服务的过程中，我们可能会收集以下类型的信息：</div>
  <div class="section-list">1. <strong>账号信息</strong>：当您通过微信授权登录时，我们会获取您的微信昵称、头像、性别、地区等公开信息，用于建立您的账号体系。</div>
  <div class="section-list">2. <strong>联系方式</strong>：当您下单时，我们可能需要您提供手机号码、收货地址等信息，以便完成订单配送。</div>
  <div class="section-list">3. <strong>上传内容</strong>：您上传的3D模型文件、图片等，我们将用于处理您的打印订单。</div>
  <div class="section-list">4. <strong>设备信息</strong>：我们会收集您的设备型号、操作系统版本、IP地址等信息，用于保障服务安全及优化用户体验。</div>
  <div class="section-list">5. <strong>日志信息</strong>：我们会自动收集您使用服务时的操作日志，包括访问时间、浏览记录等，用于分析服务使用情况。</div>
</div>

<div class="doc-section">
  <div class="section-title">三、个人信息的使用</div>
  <div class="section-content">我们收集您的个人信息主要用于以下目的：</div>
  <div class="section-list">（1）为您提供3D打印、沙盘模型等服务；</div>
  <div class="section-list">（2）处理您的订单并完成配送；</div>
  <div class="section-list">（3）向您推送订单状态通知及服务更新；</div>
  <div class="section-list">（4）改进我们的产品和服务；</div>
  <div class="section-list">（5）防范安全风险，维护服务稳定。</div>
</div>

<div class="doc-section">
  <div class="section-title">四、个人信息的共享与披露</div>
  <div class="section-content">1. 我们不会向第三方出售、出租或共享您的个人信息，但以下情形除外：</div>
  <div class="section-list">（1）获得您的明确同意后；</div>
  <div class="section-list">（2）为完成您的订单，我们需要向物流合作方提供您的收货信息；</div>
  <div class="section-list">（3）根据法律法规要求，或出于公共安全需要，向有关部门提供；</div>
  <div class="section-list">（4）为保护我们或公众的合法权益，在法律允许的范围内进行披露。</div>
  <div class="section-content">2. 我们可能与以下类型的第三方共享信息：</div>
  <div class="section-list">• 物流服务商：用于订单配送；</div>
  <div class="section-list">• 支付服务商：用于订单支付；</div>
  <div class="section-list">• 云服务提供商：用于数据存储和技术支持。</div>
</div>

<div class="doc-section">
  <div class="section-title">五、个人信息的存储与安全</div>
  <div class="section-content">1. 我们会将您的个人信息存储在中国境内，存储期限为实现本政策所述目的所必需的最短时间。</div>
  <div class="section-content">2. 我们采取符合行业标准的安全措施来保护您的个人信息，防止信息丢失、滥用、未授权访问或披露。</div>
  <div class="section-content">3. 尽管我们采取了合理的安全措施，但请您理解，互联网环境并非绝对安全，我们无法保证信息的绝对安全。</div>
</div>

<div class="doc-section">
  <div class="section-title">六、您的权利</div>
  <div class="section-content">根据《个人信息保护法》等相关法律法规，您对您的个人信息享有以下权利：</div>
  <div class="section-list">1. <strong>查阅权</strong>：您有权查阅我们收集的关于您的个人信息。</div>
  <div class="section-list">2. <strong>更正权</strong>：如发现您的个人信息有误，您有权要求我们更正。</div>
  <div class="section-list">3. <strong>删除权</strong>：在特定情形下，您有权要求我们删除您的个人信息。</div>
  <div class="section-list">4. <strong>撤回同意权</strong>：您可以通过注销账号的方式撤回我们对您个人信息处理的同意。</div>
  <div class="section-list">5. <strong>注销权</strong>：您可以通过客服申请注销您的账号，我们将在核实后为您办理。</div>
  <div class="section-content">如需行使上述权利，请通过本政策第九条的联系方式与我们联系。</div>
</div>

<div class="doc-section">
  <div class="section-title">七、未成年人保护</div>
  <div class="section-content">1. 我们非常重视对未成年人个人信息的保护。若您是未满18周岁的未成年人，请在监护人指导下阅读本政策，并在征得监护人同意后使用我们的服务。</div>
  <div class="section-content">2. 对于经监护人同意而收集的未成年人个人信息，我们只会在法律允许、监护人同意或保护未成年人所必要的范围内使用或披露此信息。</div>
</div>

<div class="doc-section">
  <div class="section-title">八、政策更新</div>
  <div class="section-content">我们可能会适时更新本隐私政策。当政策发生重大变更时，我们会通过小程序公告或消息推送的方式通知您。请您定期查阅本政策以了解最新内容。如您继续使用我们的服务，即视为您接受更新后的隐私政策。</div>
</div>

<div class="doc-section">
  <div class="section-title">九、联系我们</div>
  <div class="section-content">如您对本隐私政策有任何疑问、意见或建议，请通过以下方式联系我们：</div>
  <div class="section-list">客服电话：400-XXX-XXXX</div>
  <div class="section-list">客服邮箱：privacy@siweizhe.com</div>
  <div class="section-list">服务时间：工作日 9:00-18:00</div>
  <div class="section-content">我们将在收到您的问题后15个工作日内予以回复。</div>
</div>
`;

Page({
  data: {
    title: '',
    content: '',
    navBarTop: 44, // 默认状态栏高度（iOS 刘海屏）
    navContentHeight: 44,
  },

  onLoad(options) {
    // 获取系统状态栏高度，确保自定义导航栏不遮挡状态栏
    const sysInfo = wx.getSystemInfoSync();
    const statusBarHeight = sysInfo.statusBarHeight || 44;
    this.setData({
      navBarTop: statusBarHeight,
    });

    const { type } = options || {};
    if (type === 'agreement') {
      this.setData({
        title: '用户协议',
        content: AGREEMENT_CONTENT,
      });
      wx.setNavigationBarTitle({ title: '用户协议' });
    } else if (type === 'privacy') {
      this.setData({
        title: '隐私政策',
        content: PRIVACY_CONTENT,
      });
      wx.setNavigationBarTitle({ title: '隐私政策' });
    }
  },

  goBack() {
    wx.navigateBack({ delta: 1 });
  },
});
