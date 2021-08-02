package com.nepathya.archive.system.security.factory;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenFactory implements Serializable {
	private static final long serialVersionUID = 7625524834584106326L;

	@Value("${jwt.expires_in}")
	private long expirationTime;

	@Value("${jwt.secret}")
	private String tokenSecret;

	public String createToken(String email,String roleName,String firstName,String lastName, boolean isActive, UUID userId) {
		System.out.println(userId);
		return Jwts.builder().setSubject(email).claim("role", roleName).claim("firstName", firstName).claim("lastName",lastName).claim("isActive", isActive).claim("userId", userId).setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS512, tokenSecret).compact();
	}
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody().getSubject();
	}
}
