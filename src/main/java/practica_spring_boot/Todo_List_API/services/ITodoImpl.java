package practica_spring_boot.Todo_List_API.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import practica_spring_boot.Todo_List_API.entities.Todo;
import practica_spring_boot.Todo_List_API.entities.User;
import practica_spring_boot.Todo_List_API.exception.CustomException;
import practica_spring_boot.Todo_List_API.mappers.TodoMapper;
import practica_spring_boot.Todo_List_API.model.PagedResponse;
import practica_spring_boot.Todo_List_API.model.TodoDTO;
import practica_spring_boot.Todo_List_API.repositories.TodoRepository;
import practica_spring_boot.Todo_List_API.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ITodoImpl implements ITodo {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final UserRepository userRepository;

    private static final int DEFAULT_PAGE=0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    public PageRequest buildPageRequest(Integer pageNumber,Integer pageSize){
        int queryPageNumber;
        int queryPageSize;

        if(pageNumber != null && pageNumber > 0){
            queryPageNumber = pageNumber -1;
        }else{
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize ==null){
            queryPageSize = DEFAULT_PAGE_SIZE;
        }
        else{
            if(pageSize>100){
                queryPageSize = 100;
            }else {
                queryPageSize = pageSize;
            }
        }
        return PageRequest.of(queryPageNumber,queryPageSize);
    }

    @Override
    public PagedResponse<TodoDTO>   listTodos(Long id,Integer pageNumber,Integer pageSize) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found",404));

        PageRequest pageRequest = buildPageRequest(pageNumber,pageSize);

        Page<Todo> todoPage;

        todoPage = todoRepository.findByUserId(id,pageRequest);

        List<TodoDTO> todos = todoPage.map(todoMapper::toDTO).getContent();

        return new PagedResponse<>(
                todos,
                todoPage.getNumber() + 1,
                todoPage.getSize(),
                todoPage.getTotalPages()
        );
    }

    @Override
    public TodoDTO createTodo(TodoDTO todoDTO,Long id) {
        Todo newTodo = todoMapper.toEntity(todoDTO);
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found",404));
        newTodo.setUser(user);
        todoRepository.save(newTodo);

        return todoMapper.toDTO(newTodo);
    }

    @Override
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO,Long userId) {

        Todo foundTodo = todoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Todo with id: " + id + " not found",404));

        Long creatorTodoId = foundTodo.getUser().getId();

        if(creatorTodoId != userId){
            throw new CustomException("Todo with id: "  +id + " not found",404);
        }

        foundTodo.setTitle(todoDTO.getTitle());
        foundTodo.setDescription(todoDTO.getDescription());
        foundTodo.setCompleted(todoDTO.isCompleted());

        todoRepository.save(foundTodo);

        return todoMapper.toDTO(foundTodo);
    }

    @Override
    public TodoDTO getTodoById(Long id,Long userId) {

        Todo foundTodo = todoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Todo with id: " + id + " not found",404));

        Long creatorTodoId = foundTodo.getUser().getId();

        if(creatorTodoId != userId){
            throw new CustomException("Todo with id: "  +id + " not found",404);
        }

        return todoMapper.toDTO(foundTodo);
    }

    @Override
    public void deleteTodo(Long id,Long userId) {

        Todo foundTodo = todoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Todo with id: "  +id + " not found",404));

        Long creatorTodoId = foundTodo.getUser().getId();

        if(creatorTodoId != userId){
            throw new CustomException("Todo with id: "  +id + " not found",404);
        }

        todoRepository.delete(foundTodo);
    }
}
