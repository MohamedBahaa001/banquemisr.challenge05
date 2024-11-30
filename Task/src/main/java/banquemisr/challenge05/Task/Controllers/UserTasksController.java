package banquemisr.challenge05.Task.Controllers;


import banquemisr.challenge05.Task.Models.Entities.Task;
import banquemisr.challenge05.Task.Models.Enums.Priority;
import banquemisr.challenge05.Task.Models.Enums.Status;
import banquemisr.challenge05.Task.Service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/user/tasks")
public class UserTasksController {

    @Autowired
    private UserTaskService userTaskService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/myTasks")
    public ResponseEntity<Page<Task>> getTasksAssignedToUser(Pageable pageable) {
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<Task> tasks = userTaskService.getTasksAssignedToUser(authenticatedUsername, pageable);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/searchByTitle")
    public ResponseEntity<Page<Task>> searchMyTasksByTitle(@RequestParam String title, Pageable pageable) {
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<Task> tasks = userTaskService.searchUserTasksByTitle(authenticatedUsername, title, pageable);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateUserTaskStatus(@RequestParam Long taskId, @RequestParam Status status) {
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        String responseMessage = userTaskService.updateUserTask(authenticatedUsername, taskId, status);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/filter")
    public ResponseEntity<Page<Task>> filterMyTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dueDate,
            Pageable pageable) {
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<Task> tasks = userTaskService.filterUserTasks(authenticatedUsername, title, description, priority, status, dueDate, pageable);

        if (tasks.isEmpty()) {
            return new ResponseEntity<>(tasks, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
