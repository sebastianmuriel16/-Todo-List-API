package practica_spring_boot.Todo_List_API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import practica_spring_boot.Todo_List_API.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
