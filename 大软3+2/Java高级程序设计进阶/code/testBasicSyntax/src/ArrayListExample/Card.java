// @clean code/testBasicSyntax/src/ArrayListExample/Card.java
package ArrayListExample;

/**
 * 类名：BaseDoorCardAbs (抽象类)
 * 演化点：使用了 abstract 关键字。
 * 1. 约束：禁止通过 new BaseDoorCard() 直接创建对象。
 * 2. 规范：强制子类必须实现特定的权限说明逻辑，如方法 showAccessScope()。
 * 3. validate() add throws
 */
abstract class BaseDoorCard {
    private final String serialNumber;  // 初始化后不可再变
    private String ownerName;

    public BaseDoorCard(String serialNumber, String ownerName) {
        this.serialNumber = (serialNumber != null) ? serialNumber.trim() : "";
        // -- new -- 增加卡号长度校验
        if (this.serialNumber.length() !=8) {
            System.out.println(
                "【数据异常】警告：新创建的卡号 [" + this.serialNumber + "] 长度非8位！"
            );
        }
        this.ownerName = ownerName;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 通用逻辑：所有卡片都要进行的物理核验。
     * 抽象类可以拥有具体实现的方法。
     */
    // -- New -- Added 自定义异常
    public boolean validate() throws DoorException {
        System.out.println("【系统日志】正在启动刷卡即时校验...");
        if (this.serialNumber.length() != 8) {
            // 使用自定义异常代替通用异常
            throw new DoorException(
                "校验失败：卡号 [" + this.serialNumber + "] 格式非法！"
            );
        }
        System.out.println("物理校验通过。");
        return true;
    }

    /**
     * 抽象方法：强制规范。
     * 每一个具体的卡片子类，必须通过此方法明确自己的权限范围 。
     */
    public abstract void showAccessScope();
}
/**
 * 学生卡：既是门禁卡，又能支付 (多重身份)
 */
class StudentCard extends BaseDoorCard implements Payable {
    public StudentCard(String serialNumber, String ownerName) {
        super(serialNumber, ownerName); // 继承并调用父类构造器
    }

    @Override
    public void pay(double amount) {
        System.out.println(
            "【食堂消费】学生 " + getOwnerName() + " 支付了 " + amount + " 元。"
        );
    }

    @Override
    public boolean validate() throws DoorException {  // --new-- add 自定义异常
        boolean isValid = super.validate();  // 这里可能会抛出异常，中断后续逻辑
        if (isValid) {
            showAccessScope(); // 执行个性化权限展示
        }
        return isValid;
    }

    @Override
    public void showAccessScope() {
        System.out.println("【权限范围】学生卡：确认 " + getOwnerName() + " 的图书馆/教学楼访问权限。");
    }
}
class AdminCard extends BaseDoorCard {

    public AdminCard(String serialNumber, String ownerName) {
        super(serialNumber, ownerName);
    }

    @Override
    public boolean validate() throws DoorException {  // --new-- add 自定义异常
        boolean isValid = super.validate();
        if (isValid) {
            showAccessScope();
        }
        return isValid;
    }

    @Override
    public void showAccessScope() {
        System.out.println("【权限范围】管理员卡：开启全校所有通道，含机房与档案室。");
    }
}
/**
 * 支付接口：定义了“能够支付”的规范
 */
interface Payable {
    void pay(double amount); // 谁实现这个接口，谁就必须具备支付功能
}

