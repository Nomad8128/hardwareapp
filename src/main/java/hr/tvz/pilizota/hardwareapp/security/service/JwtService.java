package hr.tvz.pilizota.hardwareapp.security.service;

import hr.tvz.pilizota.hardwareapp.security.domain.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
