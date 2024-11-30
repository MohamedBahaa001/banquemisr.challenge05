package banquemisr.challenge05.Task.Service;

import banquemisr.challenge05.Task.Exceptions.ApiRequestException;
import banquemisr.challenge05.Task.Models.Entities.User;
import banquemisr.challenge05.Task.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public String getEmailByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return user.get().getEmail();
        } else {

            throw new ApiRequestException("User not found");
        }
    }
}
