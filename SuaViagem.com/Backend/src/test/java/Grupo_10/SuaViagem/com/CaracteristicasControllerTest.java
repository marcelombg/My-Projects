package Grupo_10.SuaViagem.com;

import Grupo_10.SuaViagem.com.controller.CaracteristicasController;
import Grupo_10.SuaViagem.com.model.entity.DTO.CaracteristicasDTO;
import Grupo_10.SuaViagem.com.service.impl.CaracteristicasServiceImpl;
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

public class CaracteristicasControllerTest {
    @InjectMocks
    private CaracteristicasController caracteristicasController;

    @Mock
    private CaracteristicasServiceImpl caracteristicasService;
    private CaracteristicasDTO caracteristicasDTO;

    @BeforeEach
    void setup(){
        caracteristicasDTO = new CaracteristicasDTO();
    }

    @Test
    void deveRegistrarUmaCaracteristica() {
        var response = assertDoesNotThrow(() -> caracteristicasController.register(caracteristicasDTO));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
    }

    @Test
    void deveEncontrarTodasCaracteristicaRegistradas() {
        var response = assertDoesNotThrow(() -> caracteristicasController.findAll());
        assertNotNull(response);

    }

    @Test
    void deveEncontrarUmaCaracteristicaPeloId() {
        var response = assertDoesNotThrow(() -> caracteristicasController.findById(1));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
        assertNotNull(response);
    }

    @Test
    void deveDeletarCaracteristicaPeloId() {
        var response = assertDoesNotThrow(() -> caracteristicasController.delete(1));
    }

    @Test
    void deveAlterarCaracteristicaPeloId() {
        var response = assertDoesNotThrow(() -> caracteristicasController.edit(caracteristicasDTO,1));
    }
}