package Grupo_10.SuaViagem.com.service.impl;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.CidadesEntity;
import Grupo_10.SuaViagem.com.model.entity.DTO.CidadesDTO;
import Grupo_10.SuaViagem.com.repository.ICidadesRepository;
import Grupo_10.SuaViagem.com.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CidadesServiceImpl implements IService<CidadesDTO> {

    @Autowired
    private ICidadesRepository iCidadesRepository;

    @Override
    public CidadesDTO register(CidadesDTO cidadesDTO) {
        CidadesEntity cidadesEntity = mapperDTOToEntity(cidadesDTO);
        cidadesEntity = iCidadesRepository.save(cidadesEntity);
        CidadesDTO cidadesDTO1 = new CidadesDTO(cidadesEntity);
        return cidadesDTO1;
    }

    @Override
    public List<CidadesDTO> findAll() {
        List<CidadesEntity> cidadesEntities = iCidadesRepository.findAll();
        List<CidadesDTO> cidadesDTOS = new ArrayList<>();

        for (CidadesEntity cidadesEntity : cidadesEntities) {
            CidadesDTO cidadesDTO = mapperEntityToDTO(cidadesEntity);
            cidadesDTOS.add(cidadesDTO);
        }
        return cidadesDTOS;
    }

    @Override
    public String delete(int id) {
        iCidadesRepository.deleteById(id);
        return "Cidade removida!";
    }

    @Override
    public CidadesDTO edit(CidadesDTO cidadesDTO, int id) {
        CidadesEntity cidadesEntity = mapperDTOToEntity(cidadesDTO);

        if(iCidadesRepository.findById(id).isPresent()) {
            cidadesEntity.setId_cidades(id);
            iCidadesRepository.save(cidadesEntity);
            return cidadesDTO;
        } else {
            iCidadesRepository.save(cidadesEntity);
            return cidadesDTO;
        }
    }

    @Override
    public CidadesDTO findById(int id) throws NotFoundException {
        CidadesEntity cidadesEntity = iCidadesRepository.findById(id).orElseThrow(()-> new NotFoundException("Cidade não encontrada com o id: " + id));
        CidadesDTO cidadesDTO = mapperEntityToDTO(cidadesEntity);
        return cidadesDTO;
    }

    private CidadesEntity mapperDTOToEntity(CidadesDTO cidadesDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        CidadesEntity cidadesEntity = objectMapper.convertValue(cidadesDTO, CidadesEntity.class);
        return cidadesEntity;
    }

    private CidadesDTO mapperEntityToDTO(CidadesEntity cidadesEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        CidadesDTO cidadesDTO = objectMapper.convertValue(cidadesEntity, CidadesDTO.class);
        return cidadesDTO;
    }
}
