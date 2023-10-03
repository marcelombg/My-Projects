package Grupo_10.SuaViagem.com.controller;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.DTO.ReservasDTO;
import Grupo_10.SuaViagem.com.service.impl.ReservasServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reservas")
@RestController
public class ReservasController {

    @Autowired
    private ReservasServiceImpl reservasService;

    @PostMapping("/register")
    @Operation(
            summary = "Registra uma reserva",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Função registrada com sucesso"
                    )
            }
    )
    public ResponseEntity<ReservasDTO> register(@RequestBody ReservasDTO reservasDTO) throws Exception {
        ResponseEntity responseEntity = null;

        if(reservasDTO != null) {
            ReservasDTO reservasDTO1 = reservasService.register(reservasDTO);
            responseEntity = new ResponseEntity<>(reservasDTO1, HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping("/findAll")
    @Operation(
            summary = "Localiza todas reservas",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reservas localizadas com sucesso"
                    )
            }
    )
    public List<ReservasDTO> findAll() {
        return reservasService.findAll();
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
        return reservasService.delete(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Edita reserva específica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reserva editada com sucesso!"
                    )
            }
    )
    public ReservasDTO edit(@RequestBody ReservasDTO reservasDTO,@PathVariable int id){
        return reservasService.edit(reservasDTO, id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Localiza reserva específica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reserva localizada com sucesso!"
                    )
            }
    )
    public ResponseEntity<ReservasDTO> findById(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(reservasService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findByIdUser/{idUser}")
    @Operation(
            summary = "Localiza reserva por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reserva por ID localizada com sucesso!"
                    )
            }
    )
    public List<ReservasDTO> findByIdUser(@PathVariable int idUser) throws NotFoundException {
        return reservasService.findByIdUser(idUser);

    }
}
