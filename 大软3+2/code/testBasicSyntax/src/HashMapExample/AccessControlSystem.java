// @clean code/testBasicSyntax/src/HashMapExample/AccessControlSystem.java
package HashMapExample;

import java.util.ArrayList;
import java.util.HashMap;

class AccessControlSystem {

    // ArrayList：用于名单的顺序维护和批量管理
    private ArrayList<BaseDoorCard> whitelist = new ArrayList<>();

    // HashMap：用于刷卡瞬间的 O(1) 极速检索 <1>
    private HashMap<String, BaseDoorCard> whitelistMap = new HashMap<>();

    public void registerCard(BaseDoorCard card) {

        whitelist.add(card); // 添加到列表
        whitelistMap.put(card.getSerialNumber(), card); // 建立键值映射 <2>

        System.out.println("系统：已授权 " + card.getOwnerName() +
                           " (ID: " + card.getSerialNumber() + ")"
        );
    }
    /**
     * 核心校验逻辑：接收纯文本 ID，返回校验结果
     */
    public void processAccessRequest(String cardId) {

        System.out.println("\n[系统层] 接收到 ID: " + cardId + " 的通行请求...");

        // 1. 利用 HashMap 快速检索是否存在
        BaseDoorCard card = whitelistMap.get(cardId);  // <3>

        if (card == null) {
            System.out.println("【拒绝】此卡未在系统中登记。");
            return;
        }

        try {
            // 2. 触发多态校验逻辑
            if (card.validate()) {
                System.out.println("【准许】持卡人：" + card.getOwnerName());
                card.showAccessScope(); // 个性化权限展示
            }
        } catch (DoorException e) {
            System.out.println("【拦截】异常卡片：" + e.getMessage());
        }
    }
}
