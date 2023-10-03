package Grupo_10.SuaViagem.com.controller;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.DTO.CaracteristicasDTO;
import Grupo_10.SuaViagem.com.service.impl.CaracteristicasServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/caracteristicas")
@RestController
public class CaracteristicasController {

    @Autowired
    private CaracteristicasServiceImpl caracteristicasService;

    @PostMapping("/register")
    @Operation(
            summary = "Registra uma caracteristica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoria registrada com sucesso!"
                    )
            }
    )
    public ResponseEntity<CaracteristicasDTO> register(@RequestBody CaracteristicasDTO caracteristicasDTO) {
        ResponseEntity responseEntity = null;

        if(caracteristicasDTO != null) {
            CaracteristicasDTO caracteristicasDTO1 = caracteristicasService.register(caracteristicasDTO);
            responseEntity = new ResponseEntity<>(caracteristicasDTO1, HttpStatus.OK);
        }
        return responseEntity;
    }
    @GetMapping("/findAll")
    @Operation(
            summary = "Localiza todas caracteristicas",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Caracteristicas localizadas com sucesso!"
                    )
            }
    )
    public List<CaracteristicasDTO> findAll() {
        return caracteristicasService.findAll();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deleta caracteristica específica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Caracteristicas deletada com sucesso!"
                    )
            }
    )
    public String delete(@PathVariable int id){
        return caracteristicasService.delete(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Edita caracteristica específica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Caracteristicas editada com sucesso!"
                    )
            }
    )
    public CaracteristicasDTO edit(@RequestBody CaracteristicasDTO caracteristicasDTO, @PathVariable int id){
        return caracteristicasService.edit(caracteristicasDTO, id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Localiza caracteristica específica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Caracteristicas localizada com sucesso!"
                    )
            }
    )
    public ResponseEntity<CaracteristicasDTO> findById(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(caracteristicasService.findById(id), HttpStatus.OK);
    }
}
