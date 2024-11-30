package banquemisr.challenge05.Task.Security.Authentication;


import banquemisr.challenge05.Task.Models.Entities.User;
import banquemisr.challenge05.Task.Models.Enums.Role;
import banquemisr.challenge05.Task.Repository.UserRepository;
import banquemisr.challenge05.Task.Security.Jwt.JwtService;
import banquemisr.challenge05.Task.Security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
   private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user) ;
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.ADMIN)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user) ;
        return AuthenticationResponse.builder().token(jwtToken).build();
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
           var user = repository.findByUsername(request.getUsername()).orElseThrow();
           var jwtToken = jwtService.generateToken(user) ;
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
