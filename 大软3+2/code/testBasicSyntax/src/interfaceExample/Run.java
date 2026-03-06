// @clean code/testBasicSyntax/src/interfaceExample/Run.java
package interfaceExample;

public class Run {
    public static void main(String[] args) {
        StudentCard studentCard = new StudentCard("S-2024-001", "小明");
        AdminCard adminCard = new AdminCard("A-007", "张老师");

        CanteenReader canteen = new CanteenReader();

        // 1. 学生卡实现了 Payable，可以打饭
        canteen.processPayment(studentCard, 15.0);

        // 2. 管理员卡没实现 Payable，下面这行代码在编译时就会报错
        // canteen.processPayment(adminCard, 20.0); // 编译不通过：张老师的卡不能在食堂付钱
    }
}
