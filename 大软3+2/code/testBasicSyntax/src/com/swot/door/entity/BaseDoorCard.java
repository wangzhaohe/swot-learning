// @clean code/testBasicSyntax/src/com/swot/door/entity/BaseDoorCard.java
package com.swot.door.entity;
/**
 * 类名：BaseDoorCard (由 DoorCard 演化而来)
 */
public class BaseDoorCard {
    private String serialNumber;
    private String ownerName;

    // 全参构造器
    public BaseDoorCard(String serialNumber, String ownerName) {
        this.serialNumber = serialNumber;
        this.ownerName = ownerName;
    }

    // 封装逻辑：Getter
    public String getOwnerName() {
        return ownerName;
    }

    // 多态的核心：父类定义一个“动作标准”
    public void validate() {
        System.out.println("【基础校验】正在核验卡片物理信息...");
    }
}
