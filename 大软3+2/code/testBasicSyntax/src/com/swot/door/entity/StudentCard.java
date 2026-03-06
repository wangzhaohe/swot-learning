// @clean code/testBasicSyntax/src/com/swot/door/entity/StudentCard.java
package com.swot.door.entity;
public class StudentCard extends BaseDoorCard {
    public StudentCard(String serialNumber, String ownerName) {
        super(serialNumber, ownerName); // 继承父类的构造
    }

    // 重写父类的方法 validate()
    @Override
    public void validate() {
        super.validate(); // 先执行父类的 validate() 方法，进行通用检测
        System.out.println("【学生卡】检测成功：确认 " + getOwnerName() + " 的图书馆访问权限。");
    }
}

