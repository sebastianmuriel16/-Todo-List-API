package practica_spring_boot.Todo_List_API.mappers;

import org.mapstruct.Mapper;
import practica_spring_boot.Todo_List_API.entities.Todo;
import practica_spring_boot.Todo_List_API.model.TodoDTO;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    Todo toEntity(TodoDTO todoDTO);

    TodoDTO toDTO(Todo todo);

}
