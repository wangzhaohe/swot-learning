// @clean code/testBasicSyntax/src/jdbcExample/Card.java
package jdbcExample;

/**
 * 类名：BaseDoorCard (抽象类)
 * 演化点：移除了 Serializable，因为数据将由数据库字段来承载。
 */
abstract class BaseDoorCard {
    private final String serialNumber;
    private String ownerName;

    public BaseDoorCard(String serialNumber, String ownerName) {
        this.ownerName = ownerName;
        this.serialNumber = (serialNumber != null) ? serialNumber.trim() : "";
        if (this.serialNumber.length() != 8) {
            System.out.println("【数据异常】警告：新创建的卡号 [" + this.serialNumber + "] 长度非8位！");
        }
    }
    
    public String getSerialNumber() { return serialNumber; }
    public String getOwnerName() { return ownerName; }

    public boolean validate() throws DoorException {
        System.out.println("【系统日志】正在启动刷卡即时校验...");
        if (this.serialNumber.length() != 8) {
            throw new DoorException("校验失败：卡号 [" + this.serialNumber + "] 格式非法！");
        }
        System.out.println("物理校验通过。");
        return true;
    }

    public abstract void showAccessScope();

    @Override
    public String toString() {
        return "门禁卡信息 { 卡号='" + serialNumber + "', 持卡人='" + ownerName + "' }";
    }
}
class StudentCard extends BaseDoorCard implements Payable {

    public StudentCard(String serialNumber, String ownerName) {
        super(serialNumber, ownerName);
    }

    @Override
    public void pay(double amount) {
        System.out.println("【食堂消费】学生 " + getOwnerName() + " 支付了 " + amount + " 元。");
    }

    @Override
    public boolean validate() throws DoorException {
        boolean isValid = super.validate();
        if (isValid) {
            showAccessScope();
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
    public boolean validate() throws DoorException {
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
interface Payable {
    void pay(double amount);
}

