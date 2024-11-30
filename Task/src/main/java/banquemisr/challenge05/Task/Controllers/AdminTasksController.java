package banquemisr.challenge05.Task.Controllers;

import banquemisr.challenge05.Task.Service.AdminTaskService;
import banquemisr.challenge05.Task.Models.Entities.Task;
import banquemisr.challenge05.Task.Models.Enums.Priority;
import banquemisr.challenge05.Task.Models.Enums.Status;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/tasks")
public class AdminTasksController {

    @Autowired
    private AdminTaskService adminTaskService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = adminTaskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<Page<Task>> getAllTasks(Pageable pageable) {
        Page<Task> tasks = adminTaskService.getAllTasks(pageable);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = adminTaskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = adminTaskService.updateTask(id, task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        adminTaskService.deleteTask(id);
        return new ResponseEntity<>("Task Deleted Successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/filter")
    public ResponseEntity<Page<Task>> filterTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dueDate,
            @RequestParam(required = false) String assignedTo,
            Pageable pageable) {
        Page<Task> tasks = adminTaskService.filterTasks(title, description, priority, status, dueDate, assignedTo, pageable);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/searchByTitle")
    public ResponseEntity<Page<Task>> searchByTitle(
            @RequestParam String title,
            Pageable pageable) {
        Page<Task> tasks = adminTaskService.searchByTitle(title, pageable);

        if (tasks.isEmpty()) {
            return new ResponseEntity<>(tasks, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
