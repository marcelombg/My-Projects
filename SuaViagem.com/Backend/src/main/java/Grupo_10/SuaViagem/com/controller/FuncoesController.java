package Grupo_10.SuaViagem.com.controller;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.DTO.FuncoesDTO;
import Grupo_10.SuaViagem.com.service.impl.FuncoesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/funcoes")
@RestController
public class FuncoesController {

    @Autowired
    private FuncoesServiceImpl funcoesService;

    @PostMapping("/register")
    @Operation(
            summary = "Registra uma função",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Função registrada com sucesso"
                    )
            }
    )
    public ResponseEntity<FuncoesDTO> register(@RequestBody FuncoesDTO funcoesDTO) {
        ResponseEntity responseEntity = null;

        if(funcoesDTO != null) {
            FuncoesDTO funcoesDTO1 = funcoesService.register(funcoesDTO);
            responseEntity = new ResponseEntity<>(funcoesDTO1, HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping("/findAll")
    @Operation(
            summary = "Localiza todas funções",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Funções localizadas com sucesso"
                    )
            }
    )
    public List<FuncoesDTO> findAll() {
        return funcoesService.findAll();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deleta função específico",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Função deletada com sucesso!"
                    )
            }
    )
    public String delete(@PathVariable int id){
        return funcoesService.delete(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Edita função específica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Função editada com sucesso!"
                    )
            }
    )
    public FuncoesDTO edit(@RequestBody FuncoesDTO funcoesDTO,@PathVariable int id){
        return funcoesService.edit(funcoesDTO, id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Localiza função específica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Função localizada com sucesso!"
                    )
            }
    )
    public ResponseEntity<FuncoesDTO> findById(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(funcoesService.findById(id), HttpStatus.OK);
    }

}
