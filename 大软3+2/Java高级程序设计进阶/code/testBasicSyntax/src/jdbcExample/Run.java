// @clean code/testBasicSyntax/src/jdbcExample/Run.java
package jdbcExample;
import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {
        // 1. 软件系统初始化
        AccessControlSystem acs = new AccessControlSystem();

        // --- 写入数据库阶段 ---
        acs.registerCard(new StudentCard("S1234567", "小明"));
        acs.registerCard(new StudentCard("BAD-99", "异常者"));
        acs.registerCard(new AdminCard("A0000001", "王校长"));

        // 2. 硬件设备初始化
        CardReader reader = new CardReader();

        // 3. 模拟现实中的物理交互流 (实时从数据库校验)
        String idFromHardware = reader.readPhysicalSignal("S1234567");
        acs.processAccessRequest(idFromHardware);

        String idFromBadCard = reader.readPhysicalSignal("BAD-99");
        acs.processAccessRequest(idFromBadCard);

        String unknownId = reader.readPhysicalSignal("UNKNOWN-001");
        acs.processAccessRequest(unknownId);

        // 4. 批量加载演示 (替代原先的反序列化)
        try {
            System.out.println("\n--- 正在从数据库批量加载全校授权名单 ---");
            ArrayList<BaseDoorCard> restoredCards = acs.loadAllCards();
            System.out.println("成功加载 " + restoredCards.size() + " 条记录:");
            for (BaseDoorCard card : restoredCards) {
                System.out.println(card);
            }
        } catch (DoorException e) {
            System.err.println("业务异常: " + e.getMessage());
        }
    }
}
