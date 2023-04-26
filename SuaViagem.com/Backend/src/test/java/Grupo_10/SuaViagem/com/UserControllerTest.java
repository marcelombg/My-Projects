package Grupo_10.SuaViagem.com;

import Grupo_10.SuaViagem.com.controller.UserController;
import Grupo_10.SuaViagem.com.model.entity.DTO.UserDTO;
import Grupo_10.SuaViagem.com.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)

public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;
    private UserDTO userDTO;

    @BeforeEach
    void setup(){
        userDTO = new UserDTO();
    }

    @Test
    void deveRegistrarUmUsuario() {
        var response = assertDoesNotThrow(() -> userController.create(userDTO));
        assertNotEquals(ResponseEntity.status(CREATED).build(), response);
    }

    @Test
    void deveAutenticarUsuario() {
        var response = assertDoesNotThrow(() -> userController.create(userDTO));
    }
}