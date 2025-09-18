package practica_spring_boot.Todo_List_API.services;

import org.springframework.data.domain.Page;
import practica_spring_boot.Todo_List_API.model.PagedResponse;
import practica_spring_boot.Todo_List_API.model.TodoDTO;

import java.util.List;
import java.util.Optional;

public interface ITodo {

    PagedResponse<TodoDTO> listTodos(Long id, Integer pageNumber, Integer pageSize);

    TodoDTO createTodo(TodoDTO todoDTO,Long id);

    TodoDTO  updateTodo(Long id, TodoDTO todoDTO,Long userId);

    TodoDTO getTodoById(Long id,Long userId);

    void deleteTodo(Long id,Long userId);
}
