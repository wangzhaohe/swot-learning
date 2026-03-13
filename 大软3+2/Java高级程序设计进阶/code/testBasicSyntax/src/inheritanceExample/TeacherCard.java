// @clean code/testBasicSyntax/src/inheritanceExample/TeacherCard.java
package inheritanceExample;

public class TeacherCard extends BaseDoorCard {
    public TeacherCard(String serialNumber, String ownerName) {
        super(serialNumber, ownerName); // 继承父类的构造
    }

    // 重写父类的方法 validate()
    @Override
    public void validate() {
        super.validate(); // 先执行父类的 validate() 方法，进行通用检测
        System.out.println("【教师卡】检测成功：确认 " + getOwnerName() + " 的实验室访问权限。");
    }
}

