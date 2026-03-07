// @clean code/testBasicSyntax/src/importPublicExample/Run.java
package importPublicExample;

import inheritanceExample.TeacherCard;

public class Run {
    public static void main(String[] args) {
        TeacherCard teacherCard = new TeacherCard("S-2024-001", "王老师");
        System.out.println("持卡人：" + teacherCard.getOwnerName());
    }
}
