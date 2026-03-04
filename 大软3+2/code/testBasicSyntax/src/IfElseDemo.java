// @edit code/testBasicSyntax/src/IfElseDemo.java
public class IfElseDemo {
    public static void main(String[] args) {
        boolean isCardValid = true;  // 卡片有效
        int currentHour = 23;        // 当前时间是晚上 23 点

        // 门禁规则：卡片必须有效，且时间必须在早6点到晚22点之间才能正常进入
        if (isCardValid && (currentHour >= 6 && currentHour <= 22)) {
            System.out.println("滴！验证通过，大门已开启。");
        } else if (!isCardValid) {
            System.out.println("警报：无效卡片！");
        } else {
            System.out.println("提示：已过门禁时间，请联系管理员。");
        }
    }
}
