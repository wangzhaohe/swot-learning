// @clean code/testBasicSyntax/src/streamExample/FileDemo.java
package streamExample;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        // 创建一个文件对象（注意：这只是建立了联系，不代表文件一定存在）
        File file = new File("cards.txt");

        if (file.exists()) {
            System.out.println("找到名单文件了！大小是：" + file.length() + " 字节");
        } else {
            System.out.println("找不到文件，系统稍后将自动新建。");
        }
    }
}
