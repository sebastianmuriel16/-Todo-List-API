package practica_spring_boot.Todo_List_API.securiity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import practica_spring_boot.Todo_List_API.entities.User;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserTodoDetails implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public static UserTodoDetails build(User user){

        List<GrantedAuthority> authorities = List.of();
        return new UserTodoDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );

    }

    @Override
    public String getUsername() {
        return email;
    }
}
