// @clean code/testBasicSyntax/src/jdbcExample/DBUtils.java
package jdbcExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 专门负责开/关数据库的工具类
 */
public class DBUtils {
    // 根据实际 Podman 映射的端口和密码修改
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PWD = "123456";

    public static Connection getConnection() throws SQLException {
        // 步骤1&2：加载驱动并拨通电话
        return DriverManager.getConnection(URL, USER, PWD);
    }
}
