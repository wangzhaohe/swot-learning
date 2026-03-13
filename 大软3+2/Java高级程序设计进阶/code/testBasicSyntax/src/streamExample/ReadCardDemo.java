// @clean code/testBasicSyntax/src/streamExample/ReadCardDemo.java
package streamExample;

import java.io.*;

// 使用了静态方法 static，这样可以让 main 直接调用，而不用实例化。
public class ReadCardDemo {
    public static void read() throws DoorException {
        try (BufferedReader reader = new BufferedReader(new FileReader("cards02.txt"))) {
            String line = reader.readLine();
            if (line == null) {
                // 手动抛出你定义的自定义异常
                throw new DoorException("读取失败：门禁卡文件为空！");
            }
            System.out.println("成功读取到数据：" + line);
        }
        catch (IOException e) {
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
