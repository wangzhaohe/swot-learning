// @clean code/testBasicSyntax/src/socketExample/DoorClient.java
package socketExample;

import java.io.*;
import java.net.*;

/**
 * 门禁终端客户端类：模拟安装在门口的刷卡机
 * 功能：主动连接服务器，发送卡号并接收服务器的开门/拒绝指令
 */
public class DoorClient {
    public static void main(String[] args) {
        // try-with-resources 语法：在括号内声明资源，代码块结束后会自动关闭 socket 和流
        // 这样可以避免因忘记写 .close() 导致的端口占用或内存泄漏
        try (
            // 1. 尝试连接：向 IP 地址为 127.0.0.1 (本机) 的 8888 端口发起连接请求
            // 如果服务器没启动，这里会直接抛出 IOException 进入 catch 块
            Socket socket = new Socket("127.0.0.1", 8888);

            // 2. 建立输出流：用于向服务器“说话”
            // PrintWriter 方便发送字符串，true 表示自动刷新缓冲区(autoFlush)
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 3. 建立输入流：用于听取服务器“回话”
            // 将字节流包装成字符流，再包装成缓冲流，以便调用 readLine() 按行读取文本
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            )
        ) {
            // --- 步骤 1：发送刷卡数据 ---
            // 构造协议格式字符串，模拟刷卡操作
            String cardData = "CARD_ID:A1234567;ACTION:OPEN";
            out.println(cardData); 
            System.out.println("【客户端】已将刷卡数据传回中央服务器：" + cardData);

            // --- 步骤 2：读取服务器的回传信息 ---
            // readLine() 是“阻塞式”的：如果服务器还没回话，程序会停在这里等
            // 只有当服务器发送了内容并带有换行符（\n），或者连接关闭时，它才会继续
            String response = in.readLine();
            
            if (response != null) {
                System.out.println("【终端显示】服务器反馈结果：" + response);
            } else {
                System.out.println("【终端警告】服务器异常断开，未返回明确指令。");
            }

        } catch (IOException e) {
            // 网络异常处理：例如服务器未开启、网络闪断、防火墙拦截等
            System.out.println("【系统告警】网络连接失败，门禁自动进入“离线应急模式”。");
            // e.printStackTrace(); // 调试时可以取消注释查看具体报错原因
        }
        // 代码执行到此处，try 括号里的资源（socket, out, in）会被自动关闭
    }
}
