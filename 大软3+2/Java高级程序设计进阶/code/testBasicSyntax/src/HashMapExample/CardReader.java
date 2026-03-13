// @clean code/testBasicSyntax/src/HashMapExample/CardReader.java
package HashMapExample;

/**
 * 硬件读卡器：仅模拟物理信号采集
 * 不依赖任何业务系统
 */
class CardReader {
    // 直接定义了一个读卡的方法
    public String readPhysicalSignal(String simulatedInput) {
        System.out.println("[硬件层] 传感器感应到物理信号...");
        // 模拟磁条或芯片读取过程，返回原始 ID 字符串
        return simulatedInput;
    }
}
