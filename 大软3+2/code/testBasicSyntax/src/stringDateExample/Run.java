// @clean code/testBasicSyntax/src/stringDateExample/Run.java
package stringDateExample;

/**
 * 门禁系统完整演示代码
 * 涵盖知识点：抽象类、继承、多态、String(trim/length/equals)、Date、SimpleDateFormat
 */
public class Run {
    public static void main(String[] args) {
        // 初始化读卡器硬件
        CardReader reader = new CardReader();

        System.out.println("======= 门禁系统 1.0 启动 (测试环境) =======");

        // 场景 A：正常学生卡 (测试 trim 功能：去除两端多余空格)
        BaseDoorCard student = new StudentCard("  S2024001  ", "小明");
        reader.readCard(student);

        System.out.println("\n------------------------------------");

        // 场景 B：非法卡片 (测试 length 双重校验拦截)
        // 构造时会触发警告，刷卡时会因为长度不是 8 位被拦截
        BaseDoorCard badCard = new StudentCard("B999", "非法入侵者");
        reader.readCard(badCard);

        System.out.println("\n------------------------------------");

        // 场景 C：超级管理员卡 (测试 equals 识别特权身份)
        BaseDoorCard admin = new AdminCard("A1234567", "王老师");
        reader.readCard(admin);
    }
}

