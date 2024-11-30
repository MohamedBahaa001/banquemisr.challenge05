package banquemisr.challenge05.Task;

import banquemisr.challenge05.Task.Security.Authentication.AuthenticationResponse;
import banquemisr.challenge05.Task.Security.Authentication.AuthenticationService;
import banquemisr.challenge05.Task.Security.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}
}


//to ease testing this startuploader creates a user and an admin in the h2-database as default to be added upon running the application
@Component
class StartupDataLoader {

	@Autowired
	private AuthenticationService authenticationService;

	@EventListener(ApplicationReadyEvent.class)
	public void createDefaultUser() {

		//default user to test the application with
		RegisterRequest defaultUser = RegisterRequest.builder()
				.username("mohamed")
				.password("12345") //passwords are encoded in the database however we can view the defaultUser pass here for testing purpose
				.email("mohamedspring@yopmail.com")
				.build();

		AuthenticationResponse response = authenticationService.register(defaultUser);

		System.out.println("Default user created with token: " + response.getToken());



		//default Admin to test the application with
		RegisterRequest defaultAdmin = RegisterRequest.builder()
				.username("admin")
				.password("12345")//passwords are encoded in the database however we can view the defaultAdmin pass here for testing purpose
				.email("ataskmanagementsystem@gmail.com")
				.build();

		AuthenticationResponse response2 = authenticationService.registerAdmin(defaultAdmin);

		System.out.println("Default Admin created with token: " + response2.getToken());
	}


}
