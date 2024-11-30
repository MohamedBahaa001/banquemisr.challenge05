package banquemisr.challenge05.Task.Models.Entities;


import banquemisr.challenge05.Task.Models.Enums.Priority;
import banquemisr.challenge05.Task.Models.Enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "tasks")
@Entity
@Data //generating getters and setters using lombok
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//automatically increment the id
    private Long id;

    @NotBlank(message = "Title is required.")
    private String title;

    @NotBlank(message = "Please write a description.")
    @Size(max = 200, message = "Description cannot be longer than 200 characters.")
    private String description;

    @NotNull(message = "Status is required.")
    @Enumerated(EnumType.STRING)
    private Status status;


    @NotNull(message = "Please Specify the Priority.")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NotNull(message = "Please enter the Due date.")
    private LocalDate dueDate;

    @NotBlank(message = "Please enter the username of the person assigned to the task")
    private String assignedTo;



}
