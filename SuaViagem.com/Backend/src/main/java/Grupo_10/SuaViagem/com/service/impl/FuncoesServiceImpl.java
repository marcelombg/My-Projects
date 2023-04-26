package Grupo_10.SuaViagem.com.service.impl;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.DTO.FuncoesDTO;
import Grupo_10.SuaViagem.com.model.entity.FuncoesEntity;
import Grupo_10.SuaViagem.com.repository.IFuncoesRepository;
import Grupo_10.SuaViagem.com.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncoesServiceImpl implements IService<FuncoesDTO> {

    @Autowired
    private IFuncoesRepository iFuncoesRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public FuncoesDTO register(FuncoesDTO funcoesDTO) {
        FuncoesEntity funcoesEntity = mapperDTOToEntity(funcoesDTO);
        funcoesEntity = iFuncoesRepository.save(funcoesEntity);
        FuncoesDTO funcoesDTO1 = new FuncoesDTO(funcoesEntity);
        return funcoesDTO1;
    }

    @Override
    public List<FuncoesDTO> findAll() {
        List<FuncoesEntity> funcoesEntities = iFuncoesRepository.findAll();
        List<FuncoesDTO> funcoesDTOS = new ArrayList<>();

        for (FuncoesEntity funcoesEntity : funcoesEntities) {
            FuncoesDTO funcoesDTO = mapperEntityToDTO(funcoesEntity);
            funcoesDTOS.add(funcoesDTO);
        }
        return funcoesDTOS;
    }

    @Override
    public String delete(int id) {
        iFuncoesRepository.deleteById(id);
        return "Função removida!";
    }

    @Override
    public FuncoesDTO edit(FuncoesDTO funcoesDTO, int id) {
        FuncoesEntity funcoesEntity = mapperDTOToEntity(funcoesDTO);

        if(iFuncoesRepository.findById(id).isPresent()) {
            funcoesEntity.setId_funcoes(id);
            iFuncoesRepository.save(funcoesEntity);
            return funcoesDTO;
        } else {
            iFuncoesRepository.save(funcoesEntity);
        }
        return funcoesDTO;
    }

    @Override
    public FuncoesDTO findById(int id) throws NotFoundException {
        FuncoesEntity funcoesEntity = iFuncoesRepository.findById(id).orElseThrow(()-> new org.webjars.NotFoundException("Função não encontrada com o id: " + id));
        FuncoesDTO funcoesDTO = mapperEntityToDTO(funcoesEntity);
        return funcoesDTO;
    }

    private FuncoesEntity mapperDTOToEntity(FuncoesDTO funcoesDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        FuncoesEntity funcoesEntity = objectMapper.convertValue(funcoesDTO, FuncoesEntity.class);
        return funcoesEntity;
    }

    private FuncoesDTO mapperEntityToDTO(FuncoesEntity funcoesEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        FuncoesDTO funcoesDTO = objectMapper.convertValue(funcoesEntity, FuncoesDTO.class);
        return funcoesDTO;
    }
}
