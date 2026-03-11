// @clean code/testBasicSyntax/src/streamExample/SaveCardDemo.java
package streamExample;

import java.io.*;

public class SaveCardDemo {
    public static void main(String[] args) {
        // try-with-resources 语法：括号里的资源会自动关闭，省心省力
        try (BufferedWriter writer = new BufferedWriter(
                                     new FileWriter("cards02.txt", true))) {
            // 模拟存入一个用户信息
            writer.write("小明,S1234567");
            writer.newLine(); // 换行，准备写下一条记录
            System.out.println("保存成功！用户信息已写入硬盘。");
        }
        catch (IOException e) {
            System.err.println("发生意外！文件写不进去了：" + e.getMessage());
        }
    }
}
