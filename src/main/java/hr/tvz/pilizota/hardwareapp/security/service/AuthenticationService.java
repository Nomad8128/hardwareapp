package hr.tvz.pilizota.hardwareapp.security.service;

import hr.tvz.pilizota.hardwareapp.security.command.LoginCommand;
import hr.tvz.pilizota.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
