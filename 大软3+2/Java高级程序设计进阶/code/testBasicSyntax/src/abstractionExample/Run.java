// code/testBasicSyntax/src/use_abstraction/Run.java
// 通过声明包，解决类名冲突问题
package abstractionExample; // 必须匹配文件夹的名字

public class Run {
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
