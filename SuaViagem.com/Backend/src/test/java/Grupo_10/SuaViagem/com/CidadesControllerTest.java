package Grupo_10.SuaViagem.com;

import Grupo_10.SuaViagem.com.controller.CidadesController;
import Grupo_10.SuaViagem.com.model.entity.DTO.CidadesDTO;
import Grupo_10.SuaViagem.com.service.impl.CidadesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

public class CidadesControllerTest {
    @InjectMocks
    private CidadesController cidadesController;

    @Mock
    private CidadesServiceImpl cidadeService;
    private CidadesDTO cidadeDTO;

    @BeforeEach
    void setup(){
        cidadeDTO = new CidadesDTO();
    }

    @Test
    void deveRegistrarUmaCidade() {
        var response = assertDoesNotThrow(() -> cidadesController.register(cidadeDTO));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
    }

    @Test
    void deveEncontrarTodasCidadesRegistradas() {
        var response = assertDoesNotThrow(() -> cidadesController.findAll());
        assertNotNull(response);

    }

    @Test
    void deveEncontrarUmCidadePeloId() {
        var response = assertDoesNotThrow(() -> cidadesController.findById(1));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
        assertNotNull(response);
    }

    @Test
    void deveDeletarCidadePeloId() {
        var response = assertDoesNotThrow(() -> cidadesController.delete(1));
    }

    @Test
    void deveAlterarCidadePeloId() {
        var response = assertDoesNotThrow(() -> cidadesController.edit(cidadeDTO,1));
    }
}
