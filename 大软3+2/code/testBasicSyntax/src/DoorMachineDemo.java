// 定义类（门禁机设计图纸）
class DoorMachineV01 {
    String location;  // 属性：安装位置（如：南大门、图书馆入口）
    String model;     // 属性：机器型号

    // 行为：开启大门的功能
    void openDoor(String userName) {
        System.out.println(
            "【" + location + "】验证通过！欢迎您，" + userName + "。大门已开启。"
        );
    }
}

// 使用类（安装并使用具体的门禁机）
public class DoorMachineDemo {
    public static void main(String[] args) {
        // 使用 new 关键字，根据图纸造出一台真实的门禁机
        DoorMachineV01 southGate = new DoorMachineV01();

        // 给这台具体的机器设置属性
        southGate.location = "学校南大门";
        southGate.model = "SWOT-A100";

        // 让这台机器执行功能：刷卡开门
        southGate.openDoor("张三");

        // 我们还可以根据同一张图纸，再造一台放在图书馆
        DoorMachineV01 libraryGate = new DoorMachineV01();
        libraryGate.location = "图书馆主楼";
        libraryGate.openDoor("李四");
    }
}
