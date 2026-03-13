// @clean code/testBasicSyntax/src/socketExample/CentralServer.java
package socketExample;

import java.io.*;
import java.net.*;

/**
 * 中央服务器类：模拟门禁系统的后端管理中心
 * 功能：监听 8888 端口，接收门禁终端上报的卡号，并下发开门指令
 */
public class CentralServer {
    public static void main(String[] args) throws Exception {
        // 1. 启动服务：在本地 8888 端口创建一个监听“热线”
        // 这个对象负责监听是否有客户端（门禁终端）发来连接请求
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务器已启动，正在 8888 端口等待门禁终端连接...");

        // 服务器通常需要 24/7 不间断运行，所以使用死循环
        while (true) {
            // 2. 阻塞等待：程序运行到这里会“停住”，直到有客户端连进来
            // 一旦连接成功，accept() 会返回一个 Socket 对象，它代表了服务器与该客户端之间的专属“双向通话通道”
            Socket client = server.accept();
            System.out.println("检测到终端接入，来源地址：" + client.getInetAddress());

            /* --- 输入流部分：读取终端发来的数据 --- */
            
            // getInputStream(): 获取底层的字节流
            // InputStreamReader: 将字节流(Byte)转为字符流(Char)，解决中文乱码等编码问题
            // BufferedReader: 包装成带缓冲的流，提供 readLine() 方法，能整行读取字符串，效率更高
            BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream())
            );

            // 读取客户端发送的第一行文本数据（例如：“卡号:A1234567”）
            String request = in.readLine();
            System.out.println("【数据上报】收到终端内容：" + request);

            /* --- 输出流部分：回传指令给终端 --- */

            // getOutputStream(): 获取指向客户端的输出字节流
            // PrintWriter: 字符打印流，方便直接输出字符串。
            // 第二个参数 true 表示“自动刷新(autoFlush)”，即调用 println 会立即将数据发送出去，无需手动 flush()
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            // 3. 业务逻辑判断
            // 模拟数据库校验过程：只有特定卡号才允许进入
            if (request != null && request.contains("A1234567")) {
                // 发送开门指令
                out.println("卡有效，请开门 (Status: OK)");
                System.out.println("【决策结果】验证通过，已下发开门指令。");
            } else {
                // 发送拒绝指令
                out.println("非法卡片，拒绝进入 (Status: Denied)");
                System.out.println("【决策结果】验证失败，已拦截非法请求。");
            }

            // 4. 短连接处理：任务完成后关闭当前会话
            // 注意：关闭 client 会同时关闭与之关联的输入/输出流
            client.close();
            System.out.println("当前事务处理完毕，已断开连接。\n--------------------------");
        }
    }
}
