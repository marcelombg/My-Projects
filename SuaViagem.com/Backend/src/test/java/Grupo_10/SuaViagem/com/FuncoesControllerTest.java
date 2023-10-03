package Grupo_10.SuaViagem.com;

import Grupo_10.SuaViagem.com.controller.FuncoesController;
import Grupo_10.SuaViagem.com.model.entity.DTO.FuncoesDTO;
import Grupo_10.SuaViagem.com.service.impl.FuncoesServiceImpl;
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

public class FuncoesControllerTest {
    @InjectMocks
    private FuncoesController funcoesController;

    @Mock
    private FuncoesServiceImpl funcoesService;
    private FuncoesDTO funcoesDTO;

    @BeforeEach
    void setup(){
        funcoesDTO = new FuncoesDTO();
    }

    @Test
    void deveRegistrarUmaFuncao() {
        var response = assertDoesNotThrow(() -> funcoesController.register(funcoesDTO));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
    }

    @Test
    void deveEncontrarTodasFuncaoRegistradas() {
        var response = assertDoesNotThrow(() -> funcoesController.findAll());
        assertNotNull(response);

    }

    @Test
    void deveEncontrarUmaFuncaoPeloId() {
        var response = assertDoesNotThrow(() -> funcoesController.findById(1));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
        assertNotNull(response);
    }

    @Test
    void deveDeletarFuncaoPeloId() {
        var response = assertDoesNotThrow(() -> funcoesController.delete(1));
    }

    @Test
    void deveAlterarFuncaoPeloId() {
        var response = assertDoesNotThrow(() -> funcoesController.edit(funcoesDTO,1));
    }
}