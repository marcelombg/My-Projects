package Grupo_10.SuaViagem.com.service.impl;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.CaracteristicasEntity;
import Grupo_10.SuaViagem.com.model.entity.DTO.CaracteristicasDTO;
import Grupo_10.SuaViagem.com.repository.ICaracteristicasRepository;
import Grupo_10.SuaViagem.com.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaracteristicasServiceImpl implements IService<CaracteristicasDTO> {

    @Autowired
    private ICaracteristicasRepository iCaracteristicasRepository;

    @Override
    public CaracteristicasDTO register(CaracteristicasDTO caracteristicasDTO) {
        CaracteristicasEntity caracteristicasEntity = mapperDTOToEntity(caracteristicasDTO);
        caracteristicasEntity = iCaracteristicasRepository.save(caracteristicasEntity);
        CaracteristicasDTO caracteristicasDTO1 = new CaracteristicasDTO(caracteristicasEntity);
        return caracteristicasDTO1;    }

    @Override
    public List<CaracteristicasDTO> findAll() {
        List<CaracteristicasEntity> caracteristicasEntities = iCaracteristicasRepository.findAll();
        List<CaracteristicasDTO> caracteristicasDTOS = new ArrayList<>();

        for (CaracteristicasEntity caracteristicasEntity : caracteristicasEntities) {
            CaracteristicasDTO caracteristicasDTO = mapperEntityToDTO(caracteristicasEntity);
            caracteristicasDTOS.add(caracteristicasDTO);
        }
        return caracteristicasDTOS;    }

    @Override
    public String delete(int id) {
        iCaracteristicasRepository.deleteById(id);
        return "Característica removida!";    }

    @Override
    public CaracteristicasDTO edit(CaracteristicasDTO caracteristicasDTO, int id) {
        CaracteristicasEntity caracteristicasEntity = mapperDTOToEntity(caracteristicasDTO);

        if(iCaracteristicasRepository.findById(id).isPresent()) {
            caracteristicasEntity.setId_caracteristicas(id);
            iCaracteristicasRepository.save(caracteristicasEntity);
            return caracteristicasDTO;
        } else {
            iCaracteristicasRepository.save(caracteristicasEntity);
            return caracteristicasDTO;
        }    }

    @Override
    public CaracteristicasDTO findById(int id) throws NotFoundException {
        CaracteristicasEntity caracteristicasEntity = iCaracteristicasRepository.findById(id).orElseThrow(()-> new NotFoundException("Caracteristica não encontrada com o id: " + id));
        CaracteristicasDTO caracteristicasDTO = mapperEntityToDTO(caracteristicasEntity);
        return caracteristicasDTO;    }

    private CaracteristicasEntity mapperDTOToEntity(CaracteristicasDTO caracteristicasDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        CaracteristicasEntity caracteristicasEntity = objectMapper.convertValue(caracteristicasDTO, CaracteristicasEntity.class);
        return caracteristicasEntity;
    }

    private CaracteristicasDTO mapperEntityToDTO(CaracteristicasEntity caracteristicasEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        CaracteristicasDTO caracteristicasDTO = objectMapper.convertValue(caracteristicasEntity, CaracteristicasDTO.class);
        return caracteristicasDTO;
    }
}
