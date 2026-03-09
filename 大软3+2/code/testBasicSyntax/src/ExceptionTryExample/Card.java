// @clean code/testBasicSyntax/src/ExceptionExample/Card.java
package ExceptionTryExample;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 类名：BaseDoorCardAbs (抽象类)
 * 演化点：使用了 abstract 关键字。
 * 1. 约束：禁止通过 new BaseDoorCard() 直接创建对象。
 * 2. 规范：强制子类必须实现特定的权限说明逻辑，如方法 showAccessScope()。
 */
abstract class BaseDoorCard {
    private final String serialNumber;  // 初始化后不可再变
    private String ownerName;

    public BaseDoorCard(String serialNumber, String ownerName) {
        // -- new -- 增加去除卡号多余空格功能
        this.serialNumber = (serialNumber != null) ? serialNumber.trim() : "";  // <1> 

        // -- new -- 增加卡号长度校验
        if (this.serialNumber.length() !=8) {  // <2>
            System.out.println("【数据异常】警告：新创建的卡号 [" + this.serialNumber + "] 长度非8位！");
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
    public boolean validate() {
        System.out.println("【系统日志】正在启动刷卡即时校验...");
        // -- new -- 增加卡号长度校验
        if (this.serialNumber.length() != 8) {  // <3>
            System.out.println("校验失败：卡号格式非法，拒绝通行！");
            return false;
        }
        // 还可以配合 equals 检查是否在白名单中
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
        System.out.println("【食堂消费】学生 " + getOwnerName() + " 支付了 " + amount + " 元。");
    }

    @Override
    public boolean validate() {
        boolean isValid = super.validate();
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
    public boolean validate() {
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
 * 读卡器服务类：系统核心调度员
 * 多态设计：它可以接收 StudentCard，也可以接收 AdminCard，甚至未来的 TeacherCard
 * 职责：记录时间、触发物理校验、识别特殊身份、展示多态权限
 */
class CardReader {
    // 预设的超级管理员卡号：用于 equals() 比对卡号类别
    private static final String ADMIN_ID = "A1234567";

    // 这里传入的是 BaseDoorCard，展示了多态的兼容性
    public void readCard(BaseDoorCard card) {
        // 1. 【时间记录】记录刷卡的“那一瞬间”
        // yyyy-MM-dd HH:mm:ss 是固定写法，将 Date 转换为人能看懂的文字
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeLog = sdf.format(new Date());

        System.out.println("\n[" + timeLog + "] 读卡器感应中...");

        try {
            if (card.validate()) {
                // 注意：字符串比较一定要用 .equals()，不能用 ==
                if (card.getSerialNumber().equals(ADMIN_ID)) {
                    System.out.println("身份确认：[特权管理员] " + card.getOwnerName() + "，欢迎进入。");
                }
            } else {
                // 若 validate() 失败，系统在 BaseDoorCard 内部也打印了错误信息
                System.out.println("系统提示：请更换有效卡片。");
            }
        } catch(NullPointerException e) {
            // 专门捕获“没读到卡”或“空指针”的情况
            System.out.println("【硬件报警】未检测到有效卡片对象！(NullPointerException)");
        } finally {
            // 无论成功还是失败，都要重置读卡器状态
            System.out.println(">> [状态流] 读卡器指示灯已重置为绿色闲置状态。");
        }
    }
}
/**
 * 支付接口：定义了“能够支付”的规范
 */
interface Payable {
    void pay(double amount); // 谁实现这个接口，谁就必须具备支付功能
}

