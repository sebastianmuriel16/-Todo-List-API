package practica_spring_boot.Todo_List_API.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import practica_spring_boot.Todo_List_API.entities.User;
import practica_spring_boot.Todo_List_API.exception.CustomException;
import practica_spring_boot.Todo_List_API.repositories.UserRepository;
import practica_spring_boot.Todo_List_API.securiity.JwtService;
import practica_spring_boot.Todo_List_API.securiity.UserTodoDetails;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String authenticate(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("Email not found",404));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new CustomException("Invalid password",401);
        }

        UserTodoDetails userTodoDetails = UserTodoDetails.build(user);


        return jwtService.generateToken(userTodoDetails);
    }

}
