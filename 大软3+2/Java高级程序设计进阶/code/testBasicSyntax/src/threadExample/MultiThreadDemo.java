// @clean code/testBasicSyntax/src/threadExample/MultiThreadDemo.java
package threadExample;

public class MultiThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        // 启动检查系统状态子线程
        HealthCheckThread check = new HealthCheckThread();
        check.start();

        // 让主线程运行 12 秒，确保子线程跑了几次循环，能打印一些信息出来。
        Thread.sleep(12000);  // 主线程为什么叫 Thread？
        // Thread 是 JVM 分配给 main 方法的逻辑执行流，调用 sleep 会让当前执行流进入计时等待状态。
        // --------- 到上面就停，12秒后继续执行 ------------

        System.out.println("【主线程】检测到程序即将关闭，发送中断信号给子线程...");

        // 发送中断信号，而不是暴力杀掉
        check.interrupt();

        // 等待子线程彻底结束后，主线程再退出
        check.join();

        System.out.println("【主线程】所有任务已结束，程序退出。");
    }
}
class HealthCheckThread extends Thread {
    @Override
    public void run() {
        // 核心业务逻辑：检查当前线程是否被下达了“中断”指令
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("系统自检中：硬件状态正常...");
                // 模拟耗时操作
                Thread.sleep(5000);
            }
            catch (InterruptedException e) {
                // 重要：当线程在 sleep 时被 interrupt()，会抛出此异常
                // catch 块是在子线程“睡觉”被吵醒的那一瞬间执行的
                System.out.println("【子线程】在睡眠中收到中断信号，准备清理现场并退出...");
                // 抛出异常后中断标志位（Interrupted Status）会被重置，通常需要跳出循环
                break;
            }
        }
        System.out.println("【子线程】已安全关闭。");
    }
}
