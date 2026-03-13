public class MethodDemo {
    /**
     * 自定义方法：专门用来校验门禁卡格式
     * @param cardId 输入的卡号
     * @return 如果以 A 开头且长度为 8，返回 true，否则返回 false
     */
    public static boolean checkCardFormat(String cardId) {
        System.out.println(">>> 安检流水线启动，正在扫描卡号: " + cardId);
        if (cardId.startsWith("A") && cardId.length() == 8) {
            return true; // 检验合格，打回 true，方法立刻结束
        } else {
            return false; // 检验不合格，打回 false
        }
    }

    // 主程序：系统的入口
    public static void main(String[] args) {
        String card1 = "A1234567";
        String card2 = "B999";
        
        // 直接“呼叫”我们写好的方法，并接收返回值
        boolean result1 = checkCardFormat(card1);
        boolean result2 = checkCardFormat(card2);
        
        System.out.println("卡号 " + card1 + " 校验结果：" + result1);
        System.out.println("卡号 " + card2 + " 校验结果：" + result2);
    }
}
