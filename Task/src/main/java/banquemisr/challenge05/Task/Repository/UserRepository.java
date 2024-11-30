package banquemisr.challenge05.Task.Repository;

import banquemisr.challenge05.Task.Models.Entities.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(@NotBlank(message="Please enter a username to proceed") String username);
}

