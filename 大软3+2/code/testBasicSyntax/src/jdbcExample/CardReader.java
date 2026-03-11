// @clean code/testBasicSyntax/src/jdbcExample/CardReader.java
package jdbcExample;

class CardReader {
    public String readPhysicalSignal(String simulatedInput) {
        System.out.println("[硬件层] 传感器感应到物理信号...");
        return simulatedInput;
    }
}
