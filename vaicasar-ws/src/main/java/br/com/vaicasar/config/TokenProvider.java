package br.com.vaicasar.config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider implements Serializable {

	private static final long serialVersionUID = -3874832499291768423L;
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(JwtConstants.SIGNING_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/* by default 1 hour */
	public String generateToken(Authentication authentication) {
		final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		return Jwts.builder().setSubject(authentication.getName()).claim(JwtConstants.AUTHORITIES_KEY, authorities)
				.signWith(SignatureAlgorithm.HS256, JwtConstants.SIGNING_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JwtConstants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
				.compact();
	}

	/* 1 week token expiry setting */
	// 604800000 = 168*3600000
	// 3600000 - 1 hour in milliseconds
	// 168 - 7* 24 ( 7 week per day * 24 hours per day)
	public String generateTokenForAWeekExpiry(Authentication authentication) {
		final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		return Jwts.builder().setSubject(authentication.getName()).claim(JwtConstants.AUTHORITIES_KEY, authorities)
				.signWith(SignatureAlgorithm.HS256, JwtConstants.SIGNING_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 604800000)).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth,
			final UserDetails userDetails) {
		UsernamePasswordAuthenticationToken temp = null;
		try {
			final JwtParser jwtParser = Jwts.parser().setSigningKey(JwtConstants.SIGNING_KEY);

			final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

			final Claims claims = claimsJws.getBody();

			final Collection<? extends GrantedAuthority> authorities = Arrays
					.stream(claims.get(JwtConstants.AUTHORITIES_KEY).toString().split(","))
					.map(SimpleGrantedAuthority::new).collect(Collectors.toList());

			temp = new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return temp;
	}

}
