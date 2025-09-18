package practica_spring_boot.Todo_List_API.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be valid")
    @Size(min = 3, max = 36,message = "Name have to be 3 characters or more")
    private String name;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 16, message = "Password have to be 8 characters or more")
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<TodoDTO> todos;



}
