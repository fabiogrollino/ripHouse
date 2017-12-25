package org.riphouse.token;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.ForbiddenException;

import org.riphouse.config.LoaderConfig;
import org.riphouse.config.RipHouseConfig;
import org.riphouse.exceptions.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {

	private Logger logger = LoggerFactory.getLogger(TokenHandler.class);

	private final String AUTHENTICATION_SCHEME = "bearer ";
	private final int preTokenLenght = 6;
	private Key key;
	private RipHouseConfig cfg = LoaderConfig.getConfig();


	public TokenHandler() {
		byte[] key = cfg.getKey().getBytes(StandardCharsets.UTF_8);
		this.key = new SecretKeySpec(key, "HS512");
	}

	public String getStringToken(String authorizationHeader) throws AuthenticationException {
		if (authorizationHeader == null || !authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME)) {
			throw new AuthenticationException("Token is required!");
		}
		return authorizationHeader.substring(preTokenLenght).trim();
	}

	public String generateToken(InfoToken infoToken) {
		String json = new Gson().toJson(infoToken);
		Date expirationDate = cfg.getExpirationDate();
		return Jwts.builder().setExpiration(expirationDate).setSubject(json).signWith(SignatureAlgorithm.HS512, key).compact();
	}

	public InfoToken getInfoToken(String token) {
		try {
			Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			String json = jwsClaims.getBody().getSubject();
			InfoToken infoTokenBo = new Gson().fromJson(json, InfoToken.class);
			return infoTokenBo;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ForbiddenException(e.getMessage());
		}

	}
}
