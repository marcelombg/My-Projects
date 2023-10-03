package Grupo_10.SuaViagem.com;

import Grupo_10.SuaViagem.com.controller.ReservasController;
import Grupo_10.SuaViagem.com.model.entity.DTO.ReservasDTO;
import Grupo_10.SuaViagem.com.service.impl.ReservasServiceImpl;
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

public class ReservasControllerTest {
    @InjectMocks
    private ReservasController reservasController;

    @Mock
    private ReservasServiceImpl reservasService;
    private ReservasDTO reservasDTO;

    @BeforeEach
    void setup(){
        reservasDTO = new ReservasDTO();
    }

    @Test
    void deveRegistrarUmaFuncao() {
        var response = assertDoesNotThrow(() -> reservasController.register(reservasDTO));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
    }

    @Test
    void deveEncontrarTodasFuncaoRegistradas() {
        var response = assertDoesNotThrow(() -> reservasController.findAll());
        assertNotNull(response);

    }

    @Test
    void deveEncontrarUmaFuncaoPeloId() {
        var response = assertDoesNotThrow(() -> reservasController.findById(1));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
        assertNotNull(response);
    }

    @Test
    void deveDeletarFuncaoPeloId() {
        var response = assertDoesNotThrow(() -> reservasController.delete(1));
    }

    @Test
    void deveAlterarFuncaoPeloId() {
        var response = assertDoesNotThrow(() -> reservasController.edit(reservasDTO,1));
    }
}