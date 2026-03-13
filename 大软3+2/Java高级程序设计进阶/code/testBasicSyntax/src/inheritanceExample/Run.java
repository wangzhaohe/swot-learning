// @clean code/testBasicSyntax/src/inheritanceExample/Run.java
package inheritanceExample;

public class Run {
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
