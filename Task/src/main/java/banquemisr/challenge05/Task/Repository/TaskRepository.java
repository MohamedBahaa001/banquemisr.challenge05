package banquemisr.challenge05.Task.Repository;

import banquemisr.challenge05.Task.Models.Entities.Task;
import banquemisr.challenge05.Task.Models.Enums.Priority;
import banquemisr.challenge05.Task.Models.Enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //for admin
    Page<Task> findByTitle(String title, Pageable pageable);

    Page<Task> findByDescription(String description, Pageable pageable);

    Page<Task> findByPriority(Priority priority, Pageable pageable);

    Page<Task> findByStatus(Status status, Pageable pageable);

    Page<Task> findByDueDate(LocalDate dueDate, Pageable pageable);

    Page<Task> findByTitleContaining(String title, Pageable pageable);

    Page<Task> findByAssignedTo(String assignedTo, Pageable pageable);


    //for user
    Page<Task> findByAssignedToAndTitleContaining(String assignedTo, String title, Pageable pageable);

    Page<Task> findByAssignedToAndDescriptionContaining(String assignedTo, String description, Pageable pageable);

    Page<Task> findByAssignedToAndPriority(String assignedTo, Priority priority, Pageable pageable);

    Page<Task> findByAssignedToAndStatus(String assignedTo, Status status, Pageable pageable);

    Page<Task> findByAssignedToAndDueDate(String assignedTo, LocalDate dueDate, Pageable pageable);



}