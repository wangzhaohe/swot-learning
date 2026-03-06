// @clean code/testBasicSyntax/src/com/swot/door/entity/CardReader.java
package com.swot.door.entity;
// 读卡器服务类 (展示多态的精髓)
// 它可以接收 StudentCard，也可以接收 AdminCard，甚至未来的 TeacherCard
public class CardReader {
    // 这里传入的是 BaseDoorCard，展示了多态的兼容性
    public void readCard(BaseDoorCard card) {
        System.out.print("读卡器感应 -> ");
        card.validate(); // 自动触发对应的子类方法
    }
}

