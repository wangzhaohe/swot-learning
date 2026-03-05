/**
 * 门禁卡类：模拟卡片内部的存储芯片。
 * 核心思想：数据安全，受控访问。
 */
class DoorCard {
    // 私有化属性：像芯片数据一样锁起来，外部无法直接用 . 来获取
    private String serialNumber; // 卡片序列号 (硬件固化，绝对不可改)
    private String ownerId;      // 持卡人工号 (写卡后不可随意更改)
    private String ownerName;    // 持卡人姓名 (允许修改，但需校验)
    private boolean active;      // 激活状态 (支持挂失/解挂)

    // 无参数构造方法：Java 编程习惯
    public DoorCard() {}

    // 初始发卡：一键写入初始数据
    public DoorCard(String serialNumber, String ownerId, String ownerName, boolean active) {
        this.serialNumber = serialNumber;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.active = active;
    }

    // 序列号：只有 Getter，没有 Setter (真正的硬件只读)
    public String getSerialNumber() {
        return serialNumber;
    }
    // 工号：只有 Getter，没有 Setter (一旦绑定，禁止外部直接篡改)
    public String getOwnerId() {
        return ownerId;
    }
    // 姓名：提供 Setter，但加入“安检”逻辑
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        if (ownerName != null && ownerName.length() >= 2) {
            this.ownerName = ownerName;
        } else {
            System.out.println("【系统纠错】更名失败：姓名格式合法性校验未通过！");
        }
    }
    // 状态：标准的读写接口，isActive 比 getActive 更容易理解
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
// code/testBasicSyntax/src/DoorCardDemo.java
public class DoorCardDemo {
    public static void main(String[] args) {
        // 制作一张新卡
        DoorCard myCard = new DoorCard("SN-9527", "EMP001", "周星星", true);
        System.out.println("--- 读卡成功 ---");
        System.out.println("序列号：" + myCard.getSerialNumber());
        System.out.println("持卡人：" + myCard.getOwnerName());

        // 模拟非法篡改核心数据（序列号）
        // myCard.serialNumber = "SN-0001";   // 编译报错：private 属性不允许直接读取
        // myCard.setSerialNumber("SN-0001"); // 编译报错：根本没写这个方法，改不了！

        // 通过合法渠道修改信息
        System.out.println("\n--- 正在办理更名手续 ---");
        myCard.setOwnerName("周星驰");  // 成功
        myCard.setOwnerName("");       // 拦截：触发 Setter 内部校验

        // 模拟卡片挂失操作
        System.out.println("\n--- 模拟挂失操作 ---");
        myCard.setActive(false);
        System.out.println("卡片状态：" + (myCard.isActive() ? "有效" : "已失效/已挂失"));
    }
}
