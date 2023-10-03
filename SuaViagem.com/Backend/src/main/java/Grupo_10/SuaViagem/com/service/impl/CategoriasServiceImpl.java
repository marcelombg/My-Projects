package Grupo_10.SuaViagem.com.service.impl;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.CategoriasEntity;
import Grupo_10.SuaViagem.com.model.entity.DTO.CategoriasDTO;
import Grupo_10.SuaViagem.com.repository.ICategoriasRepository;
import Grupo_10.SuaViagem.com.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriasServiceImpl implements IService<CategoriasDTO> {

    @Autowired
    private ICategoriasRepository iCategoriasRepository;

    @Override
    public CategoriasDTO register(CategoriasDTO categoriasDTO) {
        CategoriasEntity categoriasEntity = mapperDTOToEntity(categoriasDTO);
        categoriasEntity = iCategoriasRepository.save(categoriasEntity);
        CategoriasDTO categoriasDTO1 = new CategoriasDTO(categoriasEntity);
        return categoriasDTO1;
    }

    @Override
    public List<CategoriasDTO> findAll() {
        List<CategoriasEntity> categoryEntities = iCategoriasRepository.findAll();
        List<CategoriasDTO> categoriasDTOS = new ArrayList<>();

        for (CategoriasEntity categoriasEntity : categoryEntities) {
            CategoriasDTO categoriasDTO = mapperEntityToDTO(categoriasEntity);
            categoriasDTOS.add(categoriasDTO);
        }
        return categoriasDTOS;
    }

    @Override
    public String delete(int id) {
        iCategoriasRepository.deleteById(id);
        return "Categoria removida!";
    }

    @Override
    public CategoriasDTO edit(CategoriasDTO categoriasDTO, int id) {
        CategoriasEntity categoriasEntity = mapperDTOToEntity(categoriasDTO);

        if(iCategoriasRepository.findById(id).isPresent()) {
            categoriasEntity.setId_categorias(id);
            iCategoriasRepository.save(categoriasEntity);
            return categoriasDTO;
        } else {
            iCategoriasRepository.save(categoriasEntity);
            return categoriasDTO;
        }
    }

    @Override
    public CategoriasDTO findById(int id) throws NotFoundException {
        CategoriasEntity categoriasEntity = iCategoriasRepository.findById(id).orElseThrow(()-> new NotFoundException("Categoria não encontrada com o id: " + id));
        CategoriasDTO categoriasDTO = mapperEntityToDTO(categoriasEntity);
        return categoriasDTO;
    }

    private CategoriasEntity mapperDTOToEntity(CategoriasDTO categoriasDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        CategoriasEntity categoriasEntity = objectMapper.convertValue(categoriasDTO, CategoriasEntity.class);
        return categoriasEntity;
    }

    private CategoriasDTO mapperEntityToDTO(CategoriasEntity categoriasEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        CategoriasDTO categoriasDTO = objectMapper.convertValue(categoriasEntity, CategoriasDTO.class);
        return categoriasDTO;
    }
}
