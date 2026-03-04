/**
 * 类名：BaseDoorCard (由 DoorCard 演化而来)
 */
class BaseDoorCard {
    private String serialNumber;
    private String ownerName;

    public BaseDoorCard(String serialNumber, String ownerName) {
        this.serialNumber = serialNumber;
        this.ownerName = ownerName;
    }

    // 封装逻辑：Getter
    public String getOwnerName() { return ownerName; }

    // 多态的核心：父类定义一个“动作标准”
    public void validate() {
        System.out.println("【基础校验】正在核验卡片物理信息...");
    }
}
class StudentCard extends BaseDoorCard {
    public StudentCard(String serialNumber, String ownerName) {
        super(serialNumber, ownerName); // 继承父类的构造
    }

    @Override
    public void validate() {
        super.validate(); // 先执行父类的通用检测
        System.out.println("【学生卡】检测成功：确认 " + getOwnerName() + " 的图书馆访问权限。");
    }
}
class AdminCard extends BaseDoorCard {
    public AdminCard(String serialNumber, String ownerName) {
        super(serialNumber, ownerName);
    }

    @Override
    public void validate() {
        super.validate(); // 先执行父类的通用检测
        System.out.println("【管理员卡】检测成功：开启全校所有通道。");
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
public class InheritanceAndPolymorphismDemo {
    public static void main(String[] args) {
        CardReader cardReader = new CardReader();

        // 准备好不同的卡片（向上转型）
        BaseDoorCard studentCard = new StudentCard("S-2024-001", "小明");
        BaseDoorCard adminCard = new AdminCard("A-007", "张老师");

        // 统一调用读卡器的同一个方法，产生不同的行为
        cardReader.readCard(studentCard); // 输出学生权限
        cardReader.readCard(adminCard); // 输出管理员权限
    }
}

