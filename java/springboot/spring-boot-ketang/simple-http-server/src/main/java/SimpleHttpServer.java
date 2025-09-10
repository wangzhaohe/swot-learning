//@+leo-ver=5-thin
//@+node:swot.20241011155227.22: * @file spring-boot-ketang/simple-http-server/src/main/java/SimpleHttpServer.java
//@@language java
//@+doc
// [source,java,linenums]
// ----
//@@c
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SimpleHttpServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started on port 8080...");

            while (true) {
                // serverSocket.accept() 阻塞等待新的客户端连接，一旦有客户端连接，它返回一个 Socket 对象，表示与该客户端之间的通信。
                try (Socket clientSocket = serverSocket.accept()) {
                    handleClient(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) throws IOException {
        // 获取输入流读取请求数据
        InputStream input = clientSocket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        // 解析请求行
        String requestLine = reader.readLine();
        System.out.println("Request Line: " + requestLine);

        // 解析请求头
        Map<String, String> headers = new HashMap<>();
        String headerLine;
        while (!(headerLine = reader.readLine()).isEmpty()) {
            String[] header = headerLine.split(": ");
            headers.put(header[0], header[1]);
        }

        System.out.println("\nRequest Headers:");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 读取请求体 (仅处理 POST 请求)
        if (requestLine.startsWith("POST")) {
            int contentLength = Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
            char[] body = new char[contentLength];
            reader.read(body, 0, contentLength);
            System.out.println("\nRequest Body: ");
            System.out.println(new String(body));
        }

        // 发送响应
        OutputStream output = clientSocket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/plain");
        // 正确设置 Content-Length
        String responseBody = "Hello from SimpleHttpServer";
        writer.println("Content-Length: " + responseBody.length());
        writer.println();
        writer.println("Hello from SimpleHttpServer");
    }
}
//@+doc
// ----
//@-leo
