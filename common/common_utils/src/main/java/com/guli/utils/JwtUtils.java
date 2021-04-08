package com.guli.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtils {
    public static final long EXPIRE=1000*60*60*24;
    public static final String APP_SECRET="oaSOPADPAPoaiffasdasda";


    //加密获取token
    public static String getJwtToken(String id, String nickName){
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")

                .setSubject("guli-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))

                .claim("id",id)
                .claim("nickName",nickName)


                .signWith(SignatureAlgorithm.HS256,APP_SECRET)
                .compact();
    }

    //判断token是否有效
    public static boolean checkToken(String jwtToken){
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
        }
    //判断token是否有效
    public static boolean checkToken(HttpServletRequest request){
        try {
            String jwtToken=request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //从token中获取信息
    public static String getIdByToken(HttpServletRequest request){
        String jwtToken=request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims=claimsJws.getBody();
        return String.valueOf(claims.get("id"));
    }


}
