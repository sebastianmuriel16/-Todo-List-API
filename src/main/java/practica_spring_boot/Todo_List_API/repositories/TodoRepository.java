package practica_spring_boot.Todo_List_API.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import practica_spring_boot.Todo_List_API.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    Page<Todo> findByUserId(Long userId, Pageable pageable);
}
