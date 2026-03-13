// @edit code/testBasicSyntax/src/VariableDemo.java
public class VariableDemo {
    public static void main(String[] args) {
        // 声明并初始化变量
        String userName = "张三";
        String cardId = "A1234567";
        int swipeCount = 0;          // 刷卡次数
        boolean isActive = true;     // 卡片是否激活？true 代表已激活，false 代表未激活

        System.out.println("用户：" + userName + "，卡号：" + cardId + "，状态正常：" + isActive);
    }
}
