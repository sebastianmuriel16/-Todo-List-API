package practica_spring_boot.Todo_List_API.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practica_spring_boot.Todo_List_API.model.UserDTO;
import practica_spring_boot.Todo_List_API.model.UserSimpleDTO;
import practica_spring_boot.Todo_List_API.services.AuthService;
import practica_spring_boot.Todo_List_API.services.IUser;

import java.net.URI;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final IUser userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO){
        UserDTO userRegisrter = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRegisrter);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@Valid @RequestBody UserSimpleDTO credentials){
        String email = credentials.getEmail();
        String password = credentials.getPassword();
        String token = authService.authenticate(email,password);

        return ResponseEntity.ok(Map.of("token",token));
    }


}
