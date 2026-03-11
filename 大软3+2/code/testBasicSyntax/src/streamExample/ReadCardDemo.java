// @clean code/testBasicSyntax/src/streamExample/ReadCardDemo.java
package streamExample;
import java.io.*;

public class ReadCardDemo {

    public static void read() throws DoorException {
        // Try-with-resources 语法在 JDK 17 中完全支持
        try (BufferedReader reader = new BufferedReader(new FileReader("cards02.txt"))) {
            String line = reader.readLine();
            if (line == null) {
                // 手动抛出你定义的自定义异常
                throw new DoorException("读取失败：名单文件为空！");
            }
            System.out.println("成功读取到数据：" + line);
        } catch (IOException e) {
            // 将底层的 IOException 包装成业务层的 DoorException
            throw new DoorException("读取异常：磁盘损坏或文件丢失");
        }
    }

    public static void main(String[] args) {
        try {
            read();
        } catch (DoorException e) {
            System.err.println("捕获到业务异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
