package practica_spring_boot.Todo_List_API.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TodoDTO {

    private Long id;

    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    @Size(message = "Title have to 3 character or more")
    private String title;


    private String description;

    private boolean completed;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
