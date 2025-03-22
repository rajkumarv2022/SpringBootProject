package com.college.sri.eshwar.security;//package com.college.sri.eshwar.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//
//@Component
//public class JwtUtil {
//
////    String secret_key="";
////
////    public String GenarateToken(String username)
////    {
////        return Jwts.builder()
////                .setSubject(username)
////                .setIssuedAt(new Date(System.currentTimeMillis()))
////                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
////                .signWith(SignatureAlgorithm.HS256,secret_key)
////                .compact();
////    }
////
////    public boolean validatetoken(String token,String username)
////    {
////        return ( username.equals(extractUsername(token)) && !isTokenExpired(token) );
////    }
////
////    public String extractUsername(String token)
////    {
////        return extractClaim(token, Claims::getSubject);
////    }
////
////    public Date extractExpiration(String token)
////    {
////        return extractClaim(token,Claims::getExpiration);
////    }
////
////    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver)
////    {
////        final Claims claims=extractAllClaims(token);
////        return claimsResolver.apply(claims);
////    }
////
////    public Claims extractAllClaims(String token)
////    {
////        return Jwts.parser()
////                .setSigningKey(secret_key)
////                .parseClaimsJws(token)
////                .getBody();
////    }
////
////    public boolean isTokenExpired(String token)
////    {
////        return extractExpiration(token).before(new Date());
////    }
//
////    String secret_key="srieshwar";
////    long validity=5*60*1000;
////
////    public String generateToken(String username)
////    {
////
////        Map<String,Object> claims=new HashMap<>();
////        String token=generateToken(username,claims);
////
////        return token;
////
////    }
////
////    public String generateToken(String username,Map<String,Object>claims)
////    {
////        return Jwts.builder()
////                .setClaims(claims)
////                .setSubject(username)
////                .setIssuedAt(new Date(System.currentTimeMillis()))
////                .setExpiration(new Date(System.currentTimeMillis()+validity))
////                .signWith(SignatureAlgorithm.HS256,secret_key)
////                .compact();
////    }
////
////    public boolean validateToken(String token,String username)
////    {
////        if(token==null||token.isEmpty()==true)
////        {
////            return false;
////        }
////        final String extractedUsername=extractUsername(token);
////        return ( extractedUsername.equals(username) && !isTokenExpired(token) );
////    }
////
////    public String extractUsername(String token)
////    {
////        return extractAllclaims(token).getSubject();
////    }
////
////    public Claims extractAllclaims(String token)
////    {
////        return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
////    }
////
////    public boolean isTokenExpired(String token)
////    {
////        return extractAllclaims(token).getExpiration().before(new Date());
////    }
//
//}


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {

    private static final String SECRET_KEY = "srieshwar123456789012345678901234"; // Should be at least 32 chars long
    private static final long VALIDITY = 5 * 60 * 1000; // 5 minutes

    private Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username) {
        return generateToken(username, new HashMap<>());
    }

    public String generateToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        final String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
