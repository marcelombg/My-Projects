package Grupo_10.SuaViagem.com.controller;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.DTO.CaracteristicasDTO;
import Grupo_10.SuaViagem.com.model.entity.DTO.ImagensDTO;
import Grupo_10.SuaViagem.com.service.impl.ImagensServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/imagens")
@RestController
public class ImagensController {

    @Autowired
    private ImagensServiceImpl imagensService;

    @PostMapping("/register")
    @Operation(
            summary = "Registra uma imagem",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Imagem registrada com sucesso!"
                    )
            }
    )
    public ResponseEntity<CaracteristicasDTO> register(@RequestBody ImagensDTO imagensDTO) {
        ResponseEntity responseEntity = null;

        if(imagensDTO != null) {
            ImagensDTO imagensDTO1 = imagensService.register(imagensDTO);
            responseEntity = new ResponseEntity<>(imagensDTO1, HttpStatus.OK);
        }
        return responseEntity;
    }
    @GetMapping("/findAll")
    @Operation(
            summary = "Localiza todas imagens",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Imagens localizadas com sucesso!"
                    )
            }
    )
    public List<ImagensDTO> findAll() {
        return imagensService.findAll();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deleta imagem específica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Imagem deletada com sucesso!"
                    )
            }
    )
    public String delete(@PathVariable int id){
        return imagensService.delete(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Edita imagem específica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Imagem editada com sucesso!"
                    )
            }
    )
    public ImagensDTO edit(@RequestBody ImagensDTO imagensDTO, @PathVariable int id){
        return imagensService.edit(imagensDTO, id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Localiza imagem específica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Imagem localizada com sucesso!"
                    )
            }
    )
    public ResponseEntity<ImagensDTO> findById(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(imagensService.findById(id), HttpStatus.OK);
    }
}
