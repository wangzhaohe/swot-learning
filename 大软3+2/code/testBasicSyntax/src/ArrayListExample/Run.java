// @clean code/testBasicSyntax/src/ArrayListExample/Run.java
package ArrayListExample;

public class Run {
    public static void main(String[] args) {
        AccessControlSystem system = new AccessControlSystem();

        // 1. 动态录入名单（ArrayList 的灵活性）
        system.registerCard(new StudentCard("S1234567", "小明")); // 正常卡
        system.registerCard(new StudentCard("BAD-99", "非法者")); // 长度非法触发异常
        system.registerCard(new AdminCard("A8888888", "王老师")); // 正常卡

        // 2. 启动批量校验，模拟批量刷卡操作
        system.executeBatchCheck();
    }
}
