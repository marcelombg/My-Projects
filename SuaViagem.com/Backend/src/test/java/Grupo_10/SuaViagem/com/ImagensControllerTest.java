package Grupo_10.SuaViagem.com;

import Grupo_10.SuaViagem.com.controller.ImagensController;
import Grupo_10.SuaViagem.com.model.entity.DTO.ImagensDTO;
import Grupo_10.SuaViagem.com.service.impl.ImagensServiceImpl;
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

@ExtendWith(MockitoExtension.class)

public class ImagensControllerTest {
    @InjectMocks
    private ImagensController imagensController;

    @Mock
    private ImagensServiceImpl imagensService;
    private ImagensDTO imagensDTO;

    @BeforeEach
    void setup(){
        imagensDTO = new ImagensDTO();
    }

    @Test
    void deveRegistrarUmaFuncao() {
        var response = assertDoesNotThrow(() -> imagensController.register(imagensDTO));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
    }

    @Test
    void deveEncontrarTodasFuncaoRegistradas() {
        var response = assertDoesNotThrow(() -> imagensController.findAll());
        assertNotNull(response);

    }

    @Test
    void deveEncontrarUmaFuncaoPeloId() {
        var response = assertDoesNotThrow(() -> imagensController.findById(1));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
        assertNotNull(response);
    }

    @Test
    void deveDeletarFuncaoPeloId() {
        var response = assertDoesNotThrow(() -> imagensController.delete(1));
    }

    @Test
    void deveAlterarFuncaoPeloId() {
        var response = assertDoesNotThrow(() -> imagensController.edit(imagensDTO,1));
    }
}