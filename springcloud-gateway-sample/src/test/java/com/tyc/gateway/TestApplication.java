package com.tyc.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-04-06 15:19:31
 */
public class TestApplication {
    public static void main(String[] args) {
        testToken();
    }

    public static void testToken(){
        //创建一个JwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder()
                //声明的标识{"jti":"666"}
                .setId("666")
                //主体，用户{"sub":"Fox"}
                .setSubject("Fox")
                //创建日期{"ita":"xxxxxx"}
                .setIssuedAt(new Date())
                //签名手段，参数1：算法，参数2：盐
                .signWith(SignatureAlgorithm.HS256, "123123");
        //获取token
        String token = jwtBuilder.compact();
        System.out.println(token);
        // 解析token
        Claims claims = Jwts.parser()
                .setSigningKey("123123")
                .parseClaimsJws(token)
                .getBody();

        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("issuedAt:"+claims.getIssuedAt());
    }
}
