// @clean code/testBasicSyntax/src/HashMapExample/Run.java
package HashMapExample;

public class Run {
    public static void main(String[] args) {
        // 1. 软件系统初始化
        AccessControlSystem system = new AccessControlSystem();
        system.registerCard(new StudentCard("S1234567", "小明")); // 正常卡
        system.registerCard(new StudentCard("BAD-99", "异常者")); // 格式错误卡

        // 2. 硬件设备初始化 (它是独立的)
        CardReader reader = new CardReader();

        // 3. 模拟现实中的物理交互流
        // 硬件读到信号 -> 得到 ID -> 交给系统处理
        String idFromHardware = reader.readPhysicalSignal("S1234567");
        system.processAccessRequest(idFromHardware);

        String idFromBadCard = reader.readPhysicalSignal("BAD-99");
        system.processAccessRequest(idFromBadCard);

        String unknownId = reader.readPhysicalSignal("UNKNOWN-001");
        system.processAccessRequest(unknownId);
    }
}
