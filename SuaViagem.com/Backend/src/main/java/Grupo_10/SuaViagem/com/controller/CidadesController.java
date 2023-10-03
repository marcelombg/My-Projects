package Grupo_10.SuaViagem.com.controller;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.DTO.CidadesDTO;
import Grupo_10.SuaViagem.com.service.impl.CidadesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/cities")
@RestController
public class CidadesController {

    @Autowired
    private CidadesServiceImpl cidadesService;

    @PostMapping("/register")
    @Operation(
            summary = "Registra uma categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoria registrada com sucesso"
                    )
            }
    )
    public ResponseEntity<CidadesDTO> register(@RequestBody CidadesDTO cidadesDTO) {
        ResponseEntity responseEntity = null;

        if(cidadesDTO != null) {
            CidadesDTO cidadesDTO1 = cidadesService.register(cidadesDTO);
            responseEntity = new ResponseEntity<>(cidadesDTO1, HttpStatus.OK);
        }
        return responseEntity;
    }
    @GetMapping("/findAll")
    @Operation(
            summary = "Localiza todos registros da categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categorias localizadas com sucesso"
                    )
            }
    )
    public List<CidadesDTO> findAll() {
        return cidadesService.findAll();
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
        return cidadesService.delete(id);
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
    public CidadesDTO edit(@RequestBody CidadesDTO cidadesDTO,@PathVariable int id){
        return cidadesService.edit(cidadesDTO, id);
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
    public ResponseEntity<CidadesDTO> findById(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(cidadesService.findById(id), HttpStatus.OK);
    }
}