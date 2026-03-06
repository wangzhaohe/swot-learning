// @clean code/testBasicSyntax/src/com/swot/door/entity/AdminCard.java
package com.swot.door.entity;
public class AdminCard extends BaseDoorCard {
    public AdminCard(String serialNumber, String ownerName) {
        super(serialNumber, ownerName);
    }

    @Override
    public void validate() {
        super.validate(); // 先执行父类的通用检测
        System.out.println("【管理员卡】检测成功：开启全校所有通道。");
    }
}

