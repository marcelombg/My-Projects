package Grupo_10.SuaViagem.com.controller;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.DTO.ProdutosDTO;
import Grupo_10.SuaViagem.com.repository.ICategoriasRepository;
import Grupo_10.SuaViagem.com.repository.IProdutosRepository;
import Grupo_10.SuaViagem.com.service.impl.ProdutosServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProdutosController {

    @Autowired
    private ProdutosServiceImpl produtosService;

    @Autowired
    private ICategoriasRepository categoryRepository;

    @Autowired
    private IProdutosRepository produtosRepository;

    @PostMapping("/register")
    @Operation(
            summary = "Registra um produto",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produto registrado com sucesso!"
                    )
            }
    )
    public ResponseEntity<ProdutosDTO> register(@RequestBody ProdutosDTO produtosDTO) throws NotFoundException {
        ResponseEntity responseEntity = null;

        if(produtosDTO != null) {
            ProdutosDTO produtosDTO1 = produtosService.register(produtosDTO);
            responseEntity = new ResponseEntity<>(produtosDTO1, HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping("/findAll")
    @Operation(
            summary = "Localiza todos registros do produto",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produtos localizados com sucesso!"
                    )
            }
    )
    public List<ProdutosDTO> findAll() {
        return produtosService.findAll();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deleta registro específico do produto",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produto deletado com sucesso!"
                    )
            }
    )
    public String delete(@PathVariable int id){
        return produtosService.delete(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Edita registro específico do produto",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produto editado com sucesso!"
                    )
            }
    )
    public ProdutosDTO edit(@RequestBody ProdutosDTO produtosDTO,@PathVariable int id) throws NotFoundException {
        return produtosService.edit(produtosDTO, id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Localiza registro específico do produto",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produto localizado com sucesso!"
                    )
            }
    )
    public ResponseEntity<ProdutosDTO> findById(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(produtosService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findByCategory/{name}")
    @Operation(
            summary = "Localiza produtos por categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produtos por categoria localizados com sucesso!"
                    )
            }
    )
    public List<ProdutosDTO> findByCategoryEntityDescricao(@PathVariable String name) {
        return produtosService.findByCategoriasEntityDescricao(name);
    }

    @GetMapping("/findByCidades/{name}")
    @Operation(
            summary = "Localiza produtos por cidade",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produtos por cidade localizados com sucesso!"
                    )
            }
    )
    public List<ProdutosDTO> findByCidadesEntityNome(@PathVariable String name) {
        return produtosService.findByCidadesEntityNome(name);
    }

    @GetMapping("/findByCidadeAndDatas")
    @Operation(
            summary = "Localiza produtos por cidade e data",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produtos por cidade e data localizados com sucesso!"
                    )
            }
    )
    public List<ProdutosDTO> findByCidadeAndDatas(
            @RequestParam("cidade") String cidade,
            @RequestParam("dataInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFinal) {
        return produtosService.findByCidadeAndDatas(cidade, dataInicial, dataFinal);

    }
}