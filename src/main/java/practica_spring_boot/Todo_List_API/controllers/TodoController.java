package practica_spring_boot.Todo_List_API.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import practica_spring_boot.Todo_List_API.model.PagedResponse;
import practica_spring_boot.Todo_List_API.model.TodoDTO;
import practica_spring_boot.Todo_List_API.securiity.UserTodoDetails;
import practica_spring_boot.Todo_List_API.services.ITodo;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final ITodo todoService;

    @GetMapping
    public PagedResponse<TodoDTO> listTodos(@RequestParam(value = "page", required = false) Integer pageNumber,
                                            @RequestParam(value = "limit", required = false) Integer pageSize,
                                            @AuthenticationPrincipal UserTodoDetails userTodoDetails)
    {
        Long userId = userTodoDetails.getId();

        return todoService.listTodos(userId,pageNumber,pageSize);
    }

    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(
            @AuthenticationPrincipal UserTodoDetails userTodoDetails,
            @RequestBody TodoDTO todoDTO)
    {
        Long userId = userTodoDetails.getId();
        TodoDTO newTodo = todoService.createTodo(todoDTO,userId);

        return ResponseEntity.ok(newTodo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable("id") Long id,
                                               @AuthenticationPrincipal UserTodoDetails userTodoDetails)
    {
        Long userId = userTodoDetails.getId();
        TodoDTO foundTodo = todoService.getTodoById(id,userId);
        return ResponseEntity.ok(foundTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodoById(@PathVariable("id") Long id,@Valid @RequestBody TodoDTO todoDTO,
                                                  @AuthenticationPrincipal UserTodoDetails userTodoDetails)
    {
        Long userId = userTodoDetails.getId();

        TodoDTO updatedTodo = todoService.updateTodo(id,todoDTO,userId);

        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodoById(@PathVariable("id") Long id,
                                         @AuthenticationPrincipal UserTodoDetails userTodoDetails)
    {
        Long userId = userTodoDetails.getId();
        todoService.deleteTodo(id,userId);

        return ResponseEntity.noContent().build();

    }



}
