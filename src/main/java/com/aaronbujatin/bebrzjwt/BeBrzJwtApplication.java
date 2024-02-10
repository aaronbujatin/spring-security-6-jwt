package com.aaronbujatin.bebrzjwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.aaronbujatin.bebrzjwt.jwt.JwtService.SECRET;

@SpringBootApplication
public class BeBrzJwtApplication {
	private static final String SECRET_KEY = "yourSecretKey";

	public static void main(String[] args) {

		// Example of creating a token for a user
		String username = "john.doe";
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", 123);
		claims.put("roles", "USER");

		String token = createToken(claims, username);

		// Print the generated token
		System.out.println("Generated Token: " + token);

		SpringApplication.run(BeBrzJwtApplication.class, args);


	}

	private static String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60)) // Token expiration time (in milliseconds)
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private static Key getSignKey(){
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
