package practica_spring_boot.Todo_List_API.mappers;

import org.mapstruct.Mapper;
import practica_spring_boot.Todo_List_API.entities.User;
import practica_spring_boot.Todo_List_API.model.UserDTO;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

}
