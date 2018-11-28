package com.example.demo.util;
//https://blog.csdn.net/kkkun_joe/article/details/81878231
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
	private static final long EXPIRE_TIME=15*60*1000;
	private static final String TOKEN_SECRET="d8sd8gfgf9gfs0pqmcnjs7";
	/**
	 * 加密，返回token字符串
	 * @param name
	 * @param userId
	 * @return
	 */
	public static String sign(String name,String userId) {
		try {
			Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);
			
			Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
			Map<String,Object> header=new HashMap<>(2);
			header.put("typ", "JWT");
			header.put("alg", "HS256");
			
			return JWT.create()
					.withHeader(header)
					.withClaim("loginName", name)
					.withClaim("userId", userId)
					.withExpiresAt(date)
					.sign(algorithm);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	/**
	 * 校验token是否正确
	 * @param token
	 * @return
	 */
	public static boolean verify(String token) {
		try {
			Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifier=JWT.require(algorithm).build();
			DecodedJWT jwt=verifier.verify(token);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
