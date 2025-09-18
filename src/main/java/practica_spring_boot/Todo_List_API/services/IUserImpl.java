package practica_spring_boot.Todo_List_API.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import practica_spring_boot.Todo_List_API.entities.User;
import practica_spring_boot.Todo_List_API.exception.CustomException;
import practica_spring_boot.Todo_List_API.mappers.UserMapper;
import practica_spring_boot.Todo_List_API.model.UserDTO;
import practica_spring_boot.Todo_List_API.repositories.UserRepository;


@Service
@RequiredArgsConstructor
public class IUserImpl implements IUser {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new CustomException("Email already exists: " + userDTO.getEmail(),409);
        }

        User user = userMapper.toEntity(userDTO);
        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        return userMapper.toDTO(user);
    }
}
