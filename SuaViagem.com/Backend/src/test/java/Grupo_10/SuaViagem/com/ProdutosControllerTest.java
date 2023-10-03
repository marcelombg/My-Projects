package Grupo_10.SuaViagem.com;

import Grupo_10.SuaViagem.com.controller.ProdutosController;
import Grupo_10.SuaViagem.com.model.entity.DTO.ProdutosDTO;
import Grupo_10.SuaViagem.com.model.entity.ProdutosEntity;
import Grupo_10.SuaViagem.com.model.entity.ReservasEntity;
import Grupo_10.SuaViagem.com.service.impl.ProdutosServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)

public class ProdutosControllerTest {
    @InjectMocks
    private ProdutosController produtosController;

    @Mock
    private ProdutosServiceImpl produtosService;

    private ProdutosDTO produtosDTO;

    private ReservasEntity reservasEntity;

    @BeforeEach
    void setup() {
        produtosDTO = new ProdutosDTO();
    }

    @Test
    void deveRegistrarUmProduto() {
        var response = assertDoesNotThrow(() -> produtosController.register(produtosDTO));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
    }

    @Test
    void deveEncontrarTodosProdutosRegistradas() {
        var response = assertDoesNotThrow(() -> produtosController.findAll());
        assertNotNull(response);

    }

    @Test
    void deveEncontrarUmProdutoPeloId() {
        var response = assertDoesNotThrow(() -> produtosController.findById(1));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
        assertNotNull(response);
    }

    @Test
    void deveEncontrarUmProdutoPeloNome() {
        var response = assertDoesNotThrow(() -> produtosController.findByCidadesEntityNome(""));
        assertNotNull(response);
    }

    //@Test
    //void deveEncontrarProdutoPorCidadeEData() {
    //    var response = assertDoesNotThrow(() -> produtosService.findByCidadeAndDatas(reservasEntity);
     //   assertNotNull(response);
    //}

    @Test
    void deveEncontrarUmProdutoPelaDescricao() {
        var response = assertDoesNotThrow(() -> produtosController.findByCategoryEntityDescricao(""));
        assertNotNull(response);
    }

    @Test
    void deveDeletarProdutoPeloId() {
        var response = assertDoesNotThrow(() -> produtosController.delete(1));
    }

    @Test
    void deveAlterarProdutoPeloId() {
        var response = assertDoesNotThrow(() -> produtosController.edit(produtosDTO,1));
    }
}