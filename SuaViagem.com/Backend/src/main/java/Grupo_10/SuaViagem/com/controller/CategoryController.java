package Grupo_10.SuaViagem.com.controller;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.DTO.CategoriasDTO;
import Grupo_10.SuaViagem.com.service.impl.CategoriasServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoriasServiceImpl categoryService;

    @PostMapping("/register")
    @Operation(
            summary = "Registra uma categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoria registrada com sucesso!"
                    )
            }
    )
    public ResponseEntity<CategoriasDTO> register(@RequestBody CategoriasDTO categoriasDTO) {
        ResponseEntity responseEntity = null;

        if(categoriasDTO != null) {
            CategoriasDTO categoriasDTO1 = categoryService.register(categoriasDTO);
            responseEntity = new ResponseEntity<>(categoriasDTO1, HttpStatus.OK);
        }
        return responseEntity;
    }
    @GetMapping("/findAll")
    @Operation(
            summary = "Localiza todos registros da categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categorias localizadas com sucesso!"
                    )
            }
    )
    public List<CategoriasDTO> findAll() {
        return categoryService.findAll();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deleta registro específico da categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoria deletada com sucesso!"
                    )
            }
    )
    public String delete(@PathVariable int id){
        return categoryService.delete(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Edita registro específico da categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoria editada com sucesso!"
                    )
            }
    )
    public CategoriasDTO edit(@RequestBody CategoriasDTO categoriasDTO, @PathVariable int id){
        return categoryService.edit(categoriasDTO, id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Localiza registro específico da categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoria localizada com sucesso!"
                    )
            }
    )
    public ResponseEntity<CategoriasDTO> findById(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }
}