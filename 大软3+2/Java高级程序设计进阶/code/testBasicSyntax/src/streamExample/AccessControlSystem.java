// @clean code/testBasicSyntax/src/streamExample/AccessControlSystem.java
package streamExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;


class AccessControlSystem {

    // ArrayList：用于门禁卡名单的顺序维护和批量管理
    private ArrayList<BaseDoorCard> whitelist = new ArrayList<>();

    // HashMap：变量用于刷卡瞬间的 O(1) 极速检索
    private HashMap<String, BaseDoorCard> whitelistMap = new HashMap<>();

    public void registerCard(BaseDoorCard card) {

        whitelist.add(card); // 添加到列表
        whitelistMap.put(card.getSerialNumber(), card);

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
        BaseDoorCard card = whitelistMap.get(cardId);

        if (card == null) {
            System.out.println("【拒绝】此卡未在系统中登记。");
            return;  // 直接退出当前执行的方法
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
    // 一次性保存所有卡片的方法
    public void saveAllCards() {

        try (ObjectOutputStream oos = new ObjectOutputStream(
                                      new FileOutputStream("cards.ser"))) {
            oos.writeObject(whitelist); // 直接把整个 ArrayList 存进去  <2>
            System.out.println("全部对象序列化成功，已保存至 cards.ser");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 修改：反序列化读取整个集合
    @SuppressWarnings("unchecked")
    public ArrayList<BaseDoorCard> restoreAllCards() throws DoorException {

        try (ObjectInputStream ois = new ObjectInputStream(
                                     new FileInputStream("cards.ser"))) {
            // 反序列化出来的是 ArrayList 对象
            return (ArrayList<BaseDoorCard>) ois.readObject();  // <3>
        }
        catch (Exception e) {
            throw new DoorException("读取异常：文件损坏或类定义丢失");
        }
    }
}
