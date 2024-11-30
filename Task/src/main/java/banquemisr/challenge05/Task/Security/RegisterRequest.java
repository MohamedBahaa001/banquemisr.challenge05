package banquemisr.challenge05.Task.Security;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message="Please enter a username to proceed")
    private String username;

    @NotBlank(message="Please enter a password to proceed")
    private String password;

    @NotBlank(message="Please enter an email to proceed")
    private String email;


}
