package br.italolima.taskmanager.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.italolima.taskmanager.models.User;

@Service
public class TokenService {

	public String createToken(User user) {
		return JWT.create()
				.withIssuer("italolima")
				.withSubject(user.getUsername())
				.withClaim("id", user.getId())
				.withExpiresAt(LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256("qazwsxedcqweasdzxc"));
	}

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("qazwsxedcqweasdzxc"))
                .withIssuer("italolima")
                .build().verify(token).getSubject();
    }
}
