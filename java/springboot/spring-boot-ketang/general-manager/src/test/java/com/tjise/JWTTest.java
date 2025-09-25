//@+leo-ver=5-thin
//@+node:swot.20250816184928.1: * @file src/test/java/com/tjise/JWTTest.java
//@@language java
//@+others
//@+node:swot.20250816185036.1: ** @ignore-node import
//@@language java
//@+doc
// [source,java]
// ----
//@@c
package com.tjise;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//@+doc
// ----
//
//@+node:swot.20250816185106.1: ** @no-head public class JWTTest
//@@language java
//@+doc
// [source,java]
// ----
//@@c
public class JWTTest {
    //@+others
    //@+node:swot.20250816185206.1: *3* generateJwt() 测试创建 JWT
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    @Test
    public void generateJwt() {

        // 1. 准备自定义声明（要加入 jwt 的内容）
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "limuwan");

        // 2. 生成足够长度的密钥（≥256 bit），推荐盐为 32 字节
        //    32字节的密钥长度正好是256位，符合“≥256 bit”的安全要求
        SecretKey key = Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes());

        // 3. 构建并签名 JWT（0.12.x 写法）
        String jwt = Jwts
                .builder()
                .signWith(key, Jwts.SIG.HS256)                    // 先密钥，再算法
                .claims(claims)                                   // 设置自定义数据
               // .expiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000)) // 12h 过期
               // .expiration(new Date(System.currentTimeMillis() - 1000)) // 负 1s 马上过期
                .expiration(new Date(System.currentTimeMillis() + 90 * 1000)) // 1min 过期
                .compact();  // 生成令牌
        System.out.println(jwt);
    }
    //@+doc
    // ----
    //@+node:swot.20250816185148.1: *3* parseJwt()    测试解析 JWT
    //@@language java
    //@+doc
    // [source,java]
    // ----
    //@@c
    @Test
    public void parseJwt() {
        // 1. 生成/准备好与签发端一致的密钥
        SecretKey key = Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes());
        try {
            // 2. 创建线程安全的 JwtParser
            JwtParser parser = Jwts
                    .parser()
                    .verifyWith(key)   // 指定验证密钥
                    .build();

            // 3. 解析 generateJwt() 生成的 JWT 并返回载荷
            String jwtStr = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJsaW11d2FuIiwiZXhwIjoxNzU4NzY4NTc4fQ.lBnPP6inz7ZlaMumc6_JFA4RcpnmkNnNjg3nYS4zYe0";
            Jws<Claims> jws = parser.parseSignedClaims(jwtStr);
            Claims payload = jws.getPayload();// 就是以前版本的 getBody() 方法
            System.out.println(payload);

        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token 已过期", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Token 格式错误", e);
        } catch (JwtException e) {
            throw new RuntimeException("Token 验证失败", e);
        }
    }
    //@+doc
    // ----
    //@-others
}
//@+doc
// ----
//
//@-others
//@-leo
