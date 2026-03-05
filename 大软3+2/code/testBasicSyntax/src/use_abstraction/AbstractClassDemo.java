// code/testBasicSyntax/src/use_abstraction/AbstractClassDemo.java
// 通过声明包，解决类名冲突问题
package use_abstraction; // 必须匹配文件夹的名字

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

// --- 子类必须实现抽象方法 ---
class StudentCard extends BaseDoorCard {
  public StudentCard(String serialNumber, String ownerName) {
    super(serialNumber, ownerName); // 继承并调用父类构造器
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


// 读卡器服务类 (展示多态的精髓)
// 它可以接收 StudentCard，也可以接收 AdminCard，甚至未来的 TeacherCard
class CardReader {
  // 这里传入的是 BaseDoorCard，展示了多态的兼容性
  public void readCard(BaseDoorCard card) {
    System.out.print("读卡器感应 -> ");
    card.validate(); // 自动触发对应的子类方法
  }
}

public class AbstractClassDemo {
  public static void main(String[] args) {
    CardReader cardReader = new CardReader();

    // 向上转型：父类引用指向子类对象
    BaseDoorCard studentCard = new StudentCard("S-2024-001", "小明");
    BaseDoorCard adminCard = new AdminCard("A-007", "张老师");

    cardReader.readCard(studentCard);
    System.out.println("--------------------");
    cardReader.readCard(adminCard);

    // 演示抽象类的约束：
    // BaseDoorCard mysteryCard = new BaseDoorCard("X-000", "未知");
    // 上行代码会报错：'BaseDoorCard' is abstract; cannot be instantiated
  }
}
