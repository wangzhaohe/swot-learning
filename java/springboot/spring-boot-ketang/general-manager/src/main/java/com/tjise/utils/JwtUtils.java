//@+leo-ver=5-thin
//@+node:swot.20250816210418.1: * @file src/main/java/com/tjise/utils/JwtUtils.java
//@@language java
//@+others
//@+node:swot.20250816210630.1: ** @ignore-node import
//@@language java
//@+doc
// [source,java]
// ----
//@@c
package com.tjise.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
//@+doc
// ----
//
//@+node:swot.20250816210648.1: ** public class JwtUtils
//@@language java
//@+doc
// [source,java]
// ----
//@@c
public class JwtUtils {
    private static SecretKey key = Keys.hmacShaKeyFor(
            "12345678901234567890123456789012".getBytes());  // 盐值
    public static Long expire = 1000L * 10;          // 10s , 测试用
    // public static Long expire = - 1000L           // 负 1s 马上过期
    // public static Long expire = 12 * 3600 * 1000L // 12h 过期
    //@+others
    //@+node:swot.20250816210929.1: *3* generateJwt 生成JWT令牌
    //@+doc
    // * @param claims JWT第二部分负载 payload 中存储的内容
    //
    // [source,java]
    // ----
    //@@c
    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts
                .builder()
                .signWith(key, Jwts.SIG.HS256)          // 先密钥，再算法
                .claims(claims)                         // 设置自定义数据
                .expiration(new Date(System.currentTimeMillis() + expire))
                .compact();  // 生成令牌
        return jwt;
    }
    //@+doc
    // ----
    //@+node:swot.20250816210859.1: *3* parseJWT 解析JWT令牌
    //@+doc
    // * @param jwt JWT令牌
    // * @return JWT第二部分负载 payload 中存储的内容
    //
    // [source,java]
    // ----
    //@@c
    public static Claims parseJWT(String jwt) {

        // 创建线程安全的 JwtParser
        JwtParser parser = Jwts.parser().verifyWith(key).build();
        return parser.parseSignedClaims(jwt).getPayload();

        /*
        // 在实际开发中，这里不用 try catch，由业务来捕获异常。
        // 因为这里捕获了，业务就看不到异常了，无法给前端提示了。
        try {
            // 创建线程安全的 JwtParser
            JwtParser parser = Jwts.parser().verifyWith(key).build();
            return parser.parseSignedClaims(jwt).getPayload();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token 已过期", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Token 格式错误", e);
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Token 验证失败", e);
        }
        */
    }
    //@+doc
    // ----
    //@-others
}
//@+doc
// ----
//@-others
//@-leo
