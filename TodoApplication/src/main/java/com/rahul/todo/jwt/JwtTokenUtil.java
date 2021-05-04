package com.rahul.todo.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

/*
 * Utility class for jwt related operations
 */
@Component
public class JwtTokenUtil implements Serializable {

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "iat";
	private static final long serialVersionUID = -3301605591108950415L;
	private Clock clock = DefaultClock.INSTANCE;

	@Value("${jwt.signing.key.secret}")
	private String secret;

	@Value("${jwt.token.expiration.in.seconds}")
	private Long expiration;

	public String getUsernameFromToken(String token) {
		System.out.println("JwtTokenUtil.getUsernameFromToken()-returns the JWT sub value if present else null ");

		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		System.out.println("JwtTokenUtil.getIssuedAtDateFromToken()-returns the JWT iat value if present else null ");

		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		System.out.println("JwtTokenUtil.getExpirationDateFromToken()-returns the JWT exp value if present else null ");

		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		System.out.println("JwtTokenUtil.getClaimFromToken()-returns the claims present in JWT, if present else null ");

		final Claims claims = getAllClaimsFromToken(token);
		System.out.println(claims.toString());
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		System.out.println(
				"JwtTokenUtil.getAllClaimsFromToken()-parse JWT token using secret key and return the JWT body from header,body,signature");

		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		System.out.println("JwtTokenUtil.isTokenExpired()");

		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(clock.now());
	}

	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}

	public String generateToken(UserDetails userDetails) {
		System.out.println("JwtTokenUtil.generateToken()-Generate token with the help of user details from memory or DB");

		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		System.out.println(
				"JwtTokenUtil.doGenerateToken()-Generate token with the help of userName,createdDate,expirationDate and sign with HS512+mysecret");

		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
				.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean canTokenBeRefreshed(String token) {
		System.out.println("JwtTokenUtil.canTokenBeRefreshed()-check expiry date for the token");

		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	public String refreshToken(String token) {
		System.out.println(
				"JwtTokenUtil.refreshToken()-Generate a refreshed token by updating issuedAt,Expirationand sign with HS512+mysecret");

		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);

		final Claims claims = getAllClaimsFromToken(token);

		claims.setIssuedAt(createdDate);
		claims.setExpiration(expirationDate);

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		System.out.println("JwtTokenUtil.validateToken()-Validate token with username and expiryDate");
		
		JwtUserDetails user = (JwtUserDetails) userDetails;
		final String username = getUsernameFromToken(token);
		
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}

	private Date calculateExpirationDate(Date createdDate) {
		System.out.println("JwtTokenUtil.calculateExpirationDate()");

		return new Date(createdDate.getTime() + expiration * 1000);
	}
}
