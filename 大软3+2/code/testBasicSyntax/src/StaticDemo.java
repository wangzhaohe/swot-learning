class DoorMachineV02 {
    // 非静态变量：属于每台具体的机器（私有财产）
    String location;  // 属性：安装位置（如：南大门、图书馆入口）
    String model;     // 属性：机器型号

    // 静态变量：属于整个安保系统共享（全局统计）
    static int totalSwipes = 0;

    // 构造方法：出厂时设置安装位置
    public DoorMachineV02(String loc) {
        this.location = loc;
    }

    // 刷卡动作
    public void swipe() {
        System.out.println(this.location + "门禁机：收到刷卡请求！");
        totalSwipes++; // 每次刷卡，全局统计数据 +1
    }
}
public class StaticDemo {
    public static void main(String[] args) {
        // 1. 造两台具体的门禁机
        DoorMachineV02 southDoor = new DoorMachineV02("南大门");
        DoorMachineV02 northDoor = new DoorMachineV02("北大门");

        // 2. 它们各自独立工作（调用非静态方法）
        southDoor.swipe();
        northDoor.swipe();
        northDoor.swipe();

        // 3. 查看全局数据（调用静态变量，直接用【类名.变量名】）
        System.out.println("--- 监控中心系统 ---");
        System.out.println("今日全校累计刷卡总数：" + DoorMachineV02.totalSwipes);
    }
}
