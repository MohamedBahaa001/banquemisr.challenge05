package banquemisr.challenge05.Task.Service;

import banquemisr.challenge05.Task.Exceptions.ApiRequestException;
import banquemisr.challenge05.Task.Models.Entities.History;
import banquemisr.challenge05.Task.Models.Entities.Task;
import banquemisr.challenge05.Task.Models.Enums.Priority;
import banquemisr.challenge05.Task.Models.Enums.Status;
import banquemisr.challenge05.Task.Repository.HistoryRepository;
import banquemisr.challenge05.Task.Repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserTaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private HistoryRepository historyRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserTaskService.class);

    public Page<Task> getTasksAssignedToUser(String assignedTo, Pageable pageable) {
        logger.info("Fetching tasks for assigned user: {}", assignedTo);
        Page<Task> tasks = taskRepository.findByAssignedTo(assignedTo, pageable);

        if (tasks.isEmpty()) {
            logger.warn("No tasks found for user: {}", assignedTo);
            throw new ApiRequestException("No tasks assigned");
        }

        return tasks;
    }

    public Page<Task> searchUserTasksByTitle(String assignedTo, String title, Pageable pageable) {
        return taskRepository.findByAssignedToAndTitleContaining(assignedTo, title, pageable);
    }

    public Page<Task> filterUserTasks(String assignedTo, String title, String description, Priority priority, Status status, LocalDate dueDate, Pageable pageable) {
        if (title != null && !title.isEmpty()) {
            return taskRepository.findByAssignedToAndTitleContaining(assignedTo, title, pageable);
        }
        if (description != null && !description.isEmpty()) {
            return taskRepository.findByAssignedToAndDescriptionContaining(assignedTo, description, pageable);
        }
        if (priority != null) {
            return taskRepository.findByAssignedToAndPriority(assignedTo, priority, pageable);
        }
        if (status != null) {
            return taskRepository.findByAssignedToAndStatus(assignedTo, status, pageable);
        }
        if (dueDate != null) {
            return taskRepository.findByAssignedToAndDueDate(assignedTo, dueDate, pageable);
        }

        //if no parameter chosen fetch all tasks
        return taskRepository.findByAssignedTo(assignedTo, pageable);
    }

    public String updateUserTask(String username, Long taskId, Status status) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        logger.info("taskId: {}", taskId);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            if (!task.getAssignedTo().equals(username)) {
                throw new ApiRequestException("You are not authorized to update this task.");
            }

            if (status == Status.DONE) {
                History history = new History();
                history.setTaskId(task.getId());
                history.setTitle(task.getTitle());
                history.setDescription(task.getDescription());
                history.setStatus(status);
                history.setPriority(task.getPriority());
                history.setDueDate(task.getDueDate());
                history.setCompletedBy(username);
                history.setCompletionDate(LocalDateTime.now());
                historyRepository.save(history);

                taskRepository.delete(task);
            } else {
                task.setStatus(status);
                taskRepository.save(task);
            }

            return "Task updated successfully!";
        } else {
            throw new ApiRequestException("Task with the given ID not found.");
        }
    }
}
