public class Main {
    public static void main(String[] args) {
        System.out.println("========= Podman 环境测试 =========");

        // 验证运行操作系统（如果是 Linux，说明 Podman 成功了）
        String os = System.getProperty("os.name");
        System.out.println("当前运行操作系统: " + os);

        // 验证 JDK 版本
        String version = System.getProperty("java.version");
        System.out.println("当前 JDK 版本: " + version);

        // 验证环境变量
        String user = System.getProperty("user.name");
        System.out.println("当前容器用户: " + user);

        if ("Linux".equalsIgnoreCase(os)) {
            System.out.println("✅ 成功！代码正在 Podman 容器内运行。");
        } else {
            System.out.println("❌ 警告：代码仍在 macOS 本地运行。");
        }

        System.out.println("==================================");
    }
}
