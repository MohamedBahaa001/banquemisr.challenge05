package banquemisr.challenge05.Task.Controllers;

import banquemisr.challenge05.Task.Models.Entities.Task;
import banquemisr.challenge05.Task.Service.AdminTaskService;
import banquemisr.challenge05.Task.Service.EmailNotificationsService;
import banquemisr.challenge05.Task.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email/admin")
public class EmailNotificationController {

    @Autowired
    private EmailNotificationsService emailNotificationsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminTaskService adminTaskService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/createTaskAndNotifyByEmail")
    public ResponseEntity<String> createTaskAndNotifyByEmail(@RequestBody Task task) {

        Task createdTask = adminTaskService.createTask(task);


        String assignedTo = task.getAssignedTo();
        String userEmail = userService.getEmailByUsername(assignedTo);

        //sending email for user
        String subject = "New Task Assigned";
        String body = "Dear " + assignedTo + ",\n\n" +
                "A new task has been assigned to you. Please make sure to finish it before the deadline.\n\n" +
                "Best Regards,\nAdmin";


        emailNotificationsService.sendEmail(userEmail, subject, body);


        return new ResponseEntity<>("Task created and notification sent to the assigned user.", HttpStatus.CREATED);
    }
}
