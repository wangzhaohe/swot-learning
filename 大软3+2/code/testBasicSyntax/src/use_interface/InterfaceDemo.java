// code/testBasicSyntax/src/use_interface/InterfaceDemo.java
package use_interface;

/**
 * 支付接口：定义了“能够支付”的规范
 */
interface Payable {
  void pay(double amount); // 谁实现这个接口，谁就必须具备支付功能
}

/**
 * 类名：BaseDoorCardAbs (抽象类)
 * 演化点：使用了 abstract 关键字。
 * 1. 约束：禁止通过 new BaseDoorCard() 直接创建对象。
 * 2. 规范：强制子类必须实现特定的权限说明逻辑。
 */
abstract class BaseDoorCard {
  private String serialNumber;
  private String ownerName;

  public BaseDoorCard(String serialNumber, String ownerName) {
    this.serialNumber = serialNumber;
    this.ownerName = ownerName;
  }

  public String getOwnerName() {
    return ownerName;
  }

  /**
   * 通用逻辑：所有卡片都要进行的物理核验。
   * 抽象类可以拥有具体实现的方法。
   */
  public void validate() {
    System.out.println("【基础校验】正在核验卡片物理信息...");
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
  public void validate() {
    super.validate(); // 执行通用检测
    showAccessScope(); // 执行个性化权限展示
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
  public void validate() {
    super.validate();
    showAccessScope();
  }

  @Override
  public void showAccessScope() {
    System.out.println("【权限范围】管理员卡：开启全校所有通道，含机房与档案室。");
  }
}

/**
 * 食堂刷卡机：它不关心你是哪种卡，它只认“Payable”接口
 */
class CanteenReader {
  public void processPayment(Payable card, double price) {
    System.out.println("--- 食堂刷卡机感应中 ---");
    card.pay(price); // 只要实现了 Payable，就一定有 pay 方法
  }
}

public class InterfaceDemo {
  public static void main(String[] args) {
    StudentCard studentCard = new StudentCard("S-2024-001", "小明");
    AdminCard adminCard = new AdminCard("A-007", "张老师");

    CanteenReader canteen = new CanteenReader();

    // 1. 学生卡实现了 Payable，可以打饭
    canteen.processPayment(studentCard, 15.0);

    // 2. 管理员卡没实现 Payable，下面这行代码在编译时就会报错
    // canteen.processPayment(adminCard, 20.0); // 编译不通过：张老师的卡不能在食堂付钱
  }
}
