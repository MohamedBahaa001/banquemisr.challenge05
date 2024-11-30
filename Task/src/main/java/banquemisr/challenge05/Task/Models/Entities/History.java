package banquemisr.challenge05.Task.Models.Entities;

import banquemisr.challenge05.Task.Models.Enums.Priority;
import banquemisr.challenge05.Task.Models.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Table(name = "TasksHistory")
@Entity
@Data //generating getters and setters using lombok
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id")
    private Long taskId;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "completed_by")
    private String completedBy; //

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

}
