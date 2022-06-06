package hr.tvz.pilizota.hardwareapp.security.service;

import hr.tvz.pilizota.hardwareapp.security.service.AuthenticationService;
import hr.tvz.pilizota.hardwareapp.security.command.LoginCommand;
import hr.tvz.pilizota.hardwareapp.security.domain.User;
import hr.tvz.pilizota.hardwareapp.security.dto.LoginDTO;
import hr.tvz.pilizota.hardwareapp.security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final hr.tvz.pilizota.hardwareapp.security.service.JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(hr.tvz.pilizota.hardwareapp.security.service.JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<User> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();

        // TODO - implementirati provjeru odgovara li lozinka, koju je unio korisnik, enkriptiranoj lozinki u bazi
        try{
            return b.matches(rawPassword, encryptedPassword);
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }

    }
}
