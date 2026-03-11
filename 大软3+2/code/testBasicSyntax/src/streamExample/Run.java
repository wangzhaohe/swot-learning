// @clean code/testBasicSyntax/src/streamExample/Run.java
package streamExample;

import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {
        // 1. 软件系统初始化
        AccessControlSystem acs = new AccessControlSystem();
        acs.registerCard(new StudentCard("S1234567", "小明")); // 正常卡
        acs.registerCard(new StudentCard("BAD-99", "异常者")); // 格式错误卡
        acs.saveAllCards(); // <--- 统一保存

        // 2. 硬件设备初始化 (它是独立的)
        CardReader reader = new CardReader();

        // 3. 模拟现实中的物理交互流
        // 读卡器硬件读到信号 -> 得到 ID -> 交给系统处理
        String idFromHardware = reader.readPhysicalSignal("S1234567");
        acs.processAccessRequest(idFromHardware);

        String idFromBadCard = reader.readPhysicalSignal("BAD-99");
        acs.processAccessRequest(idFromBadCard);

        String unknownId = reader.readPhysicalSignal("UNKNOWN-001");
        acs.processAccessRequest(unknownId);
        try {
            ArrayList<BaseDoorCard> restoredCards = acs.restoreAllCards();  // <4>
            System.out.println("成功复活 " + restoredCards.size() + " 个对象:");
            
            for (BaseDoorCard card : restoredCards) {
                System.out.println(card); // 会自动调用 BaseDoorCard.toString()
            }
        }
        catch (DoorException e) {
            System.err.println("业务异常: " + e.getMessage());
        }
    }
}
