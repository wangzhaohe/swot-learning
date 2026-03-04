// @edit code/testBasicSyntax/src/LoopDemo.java
import java.util.Scanner;

public class LoopDemo {
    public static void main(String[] args) {
        // --- for 循环演示：批量给 3 张新卡初始化 ---
        System.out.println("--- 批量发卡程序启动 ---");
        for (int i = 1; i <= 3; i++) {
            System.out.println("正在制作第 " + i + " 张门禁卡...");
        }
        // --- while 循环演示：模拟读卡器持续工作 ---
        Scanner scanner = new Scanner(System.in);
        boolean powerOn = true;

        System.out.println("\n--- 读卡器已就绪，请输入卡号 (输入 exit 关闭机器) ---");

        while (powerOn) {
            System.out.print("请刷卡 > ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                System.out.println("收到关机指令，读卡器关闭。");
                powerOn = false; // 打断循环条件
                // break; // 也可以直接使用 break 跳出循环
            } else {
                System.out.println("读取到卡号：" + input + "，处理中...");
            }
        }
        scanner.close();
    }
}
