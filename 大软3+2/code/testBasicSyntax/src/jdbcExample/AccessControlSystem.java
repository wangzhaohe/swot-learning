// @clean code/testBasicSyntax/src/jdbcExample/AccessControlSystem.java
package jdbcExample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


class AccessControlSystem {
    /**
     * 将卡片信息持久化到 PostgreSQL 数据库
     */
    public void registerCard(BaseDoorCard card) {

        String sql = "INSERT INTO cards (serial_number, owner_name, card_type) VALUES (?, ?, ?) ON CONFLICT (serial_number) DO NOTHING";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // <1>

            // 像填空题一样安全地设置参数，防止 SQL 注入
            pstmt.setString(1, card.getSerialNumber());
            pstmt.setString(2, card.getOwnerName());
            pstmt.setString(3, card instanceof StudentCard ? "Student" : "Admin");

            pstmt.executeUpdate(); // <2>
            System.out.println("系统：已将授权人员 " + card.getOwnerName() + " 写入数据库。");
        }
        catch (SQLException e) {
            System.err.println("数据库写入失败: " + e.getMessage());
        }
    }
    /**
     * 核心校验逻辑：接收纯文本 ID，从数据库中实时检索并验证
     */
    public void processAccessRequest(String cardId) {
        System.out.println("\n[系统层] 接收到 ID: " + cardId + " 的通行请求...");

        String sql = "SELECT * FROM cards WHERE serial_number = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cardId);
            ResultSet rs = pstmt.executeQuery(); // <3>

            if (rs.next()) {
                String name = rs.getString("owner_name");
                String type = rs.getString("card_type");

                // 根据数据库中的类型动态复原对象
                BaseDoorCard card = "Student".equals(type) ?
                                    new StudentCard(cardId, name) :
                                    new AdminCard(cardId, name);
                try {
                    if (card.validate()) {
                        System.out.println("【准许】持卡人：" + card.getOwnerName());
                    }
                } catch (DoorException e) {
                    System.out.println("【拦截】异常卡片：" + e.getMessage());
                }
            } else {
                System.out.println("【拒绝】此卡未在系统中登记。");
            }
        } catch (SQLException e) {
            System.out.println("【系统异常】数据库连接中断！");
        }
    }
    /**
     * 代替反序列化：一次性从数据库中读取所有卡片记录
     */
    public ArrayList<BaseDoorCard> loadAllCards() throws DoorException {

        ArrayList<BaseDoorCard> list = new ArrayList<>();
        String sql = "SELECT * FROM cards";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) { // <4>
                String id = rs.getString("serial_number");
                String name = rs.getString("owner_name");
                String type = rs.getString("card_type");

                if ("Student".equals(type)) {
                    list.add(new StudentCard(id, name));
                } else {
                    list.add(new AdminCard(id, name));
                }
            }
        } catch (SQLException e) {
            throw new DoorException("读取异常：无法连接至 PostgreSQL");
        }
        return list;
    }
}
