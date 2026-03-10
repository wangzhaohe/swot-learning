// @clean code/testBasicSyntax/src/ExceptionExample/Run.java
package ExceptionThrowsExample;

public class Run {
    public static void main(String[] args) {
        CardReader reader = new CardReader();  // 创建读卡器对象

        // 场景 A：正常卡片
        reader.readCard(new StudentCard("  S1234567  ", "小明"));
        System.out.println("------");

        // 场景 B：非法长度卡片
        reader.readCard(new StudentCard("B99", "非法卡号"));  // <1>
        System.out.println("------");

        // 场景 C：传入 null (模拟硬件未感应到对象)
        reader.readCard(null);
        System.out.println("------");

        // 场景 D：传入正常的管理员卡
        reader.readCard(new AdminCard("A1234567", "王老师"));
    }
}
