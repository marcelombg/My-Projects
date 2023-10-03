package Grupo_10.SuaViagem.com;

import Grupo_10.SuaViagem.com.controller.CategoryController;
import Grupo_10.SuaViagem.com.model.entity.DTO.CategoriasDTO;
import Grupo_10.SuaViagem.com.service.impl.CategoriasServiceImpl;
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

public class CategoryControllerTest {
    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoriasServiceImpl categoryService;
    private CategoriasDTO categoriasDTO;

    @BeforeEach
    void setup(){
        categoriasDTO = new CategoriasDTO();
    }

    @Test
    void deveRegistrarUmaCategoria() {
        var response = assertDoesNotThrow(() -> categoryController.register(categoriasDTO));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
    }

    @Test
    void deveEncontrarTodasCategoriasRegistradas() {
        var response = assertDoesNotThrow(() -> categoryController.findAll());
        assertNotNull(response);

    }

    @Test
    void deveEncontrarUmaCategoriaPeloId() {
        var response = assertDoesNotThrow(() -> categoryController.findById(1));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
        assertNotNull(response);
    }

    @Test
    void deveDeletarCategoriaPeloId() {
        var response = assertDoesNotThrow(() -> categoryController.delete(1));
    }

    @Test
    void deveAlterarCategoriaPeloId() {
        var response = assertDoesNotThrow(() -> categoryController.edit(categoriasDTO,1));
    }
}

