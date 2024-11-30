package banquemisr.challenge05.Task.Service;

import banquemisr.challenge05.Task.Exceptions.ApiRequestException;
import banquemisr.challenge05.Task.Models.Entities.Task;
import banquemisr.challenge05.Task.Models.Enums.Priority;
import banquemisr.challenge05.Task.Models.Enums.Status;
import banquemisr.challenge05.Task.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AdminTaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }



    public Task getTaskById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            return task.get();
        }else{
            throw new ApiRequestException("Task with the following id is not found!");
        }
    }

    public Task updateTask(Long id, Task task) {
        Optional<Task> existingTask = taskRepository.findById(id);

        if (existingTask.isPresent()) {
            Task updatedTask = existingTask.get();

            updatedTask.setTitle(task.getTitle());
            updatedTask.setDescription(task.getDescription());
            updatedTask.setStatus(task.getStatus());
            updatedTask.setPriority(task.getPriority());
            updatedTask.setDueDate(task.getDueDate());
            updatedTask.setAssignedTo(task.getAssignedTo());
            return taskRepository.save(updatedTask);
        } else {
            throw new ApiRequestException("Task with the following id is not found!");
        }
    }

    public void deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new ApiRequestException("Task with the following id is not found!");
        }
    }

    public Page<Task> filterTasks(String title, String description, Priority priority, Status status, LocalDate dueDate, String assignedTo, Pageable pageable) {
        if (title != null && !title.isEmpty()) {
            return taskRepository.findByTitleContaining(title, pageable);
        }
        if (description != null && !description.isEmpty()) {
            return taskRepository.findByDescription(description, pageable);
        }
        if (priority != null) {
            return taskRepository.findByPriority(priority, pageable);
        }
        if (status != null) {
            return taskRepository.findByStatus(status, pageable);
        }
        if (dueDate != null) {
            return taskRepository.findByDueDate(dueDate, pageable);
        }
        if (assignedTo != null && !assignedTo.isEmpty()) {
            return taskRepository.findByAssignedTo(assignedTo, pageable);
        }

        return taskRepository.findAll(pageable);
    }

    public Page<Task> searchByTitle(String title, Pageable pageable) {
        return taskRepository.findByTitleContaining(title, pageable);
    }


}
