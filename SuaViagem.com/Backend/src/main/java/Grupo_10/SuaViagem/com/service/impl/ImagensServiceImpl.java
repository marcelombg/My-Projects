package Grupo_10.SuaViagem.com.service.impl;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import Grupo_10.SuaViagem.com.model.entity.DTO.ImagensDTO;
import Grupo_10.SuaViagem.com.model.entity.ImagensEntity;
import Grupo_10.SuaViagem.com.repository.IImagensRespository;
import Grupo_10.SuaViagem.com.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImagensServiceImpl implements IService<ImagensDTO> {

    @Autowired
    private IImagensRespository iImagensRespository;

    @Override
    public ImagensDTO register(ImagensDTO imagensDTO){
        ImagensEntity imagensEntity = mapperDTOToEntity(imagensDTO);
        imagensEntity = iImagensRespository.save(imagensEntity);
        ImagensDTO imagensDTO1 = new ImagensDTO(imagensEntity);
        return imagensDTO1;
    }

    @Override
    public List<ImagensDTO> findAll() {
        List<ImagensEntity> imagensEntities = iImagensRespository.findAll();
        List<ImagensDTO> imagensDTOS = new ArrayList<>();

        for (ImagensEntity imagensEntity : imagensEntities) {
            ImagensDTO imagensDTO = mapperEntityToDTO(imagensEntity);
            imagensDTOS.add(imagensDTO);
        }
        return imagensDTOS;
    }

    @Override
    public String delete(int id) {
        iImagensRespository.deleteById(id);
        return "Imagem removida!";
    }

    @Override
    public ImagensDTO edit(ImagensDTO imagensDTO, int id) {
        ImagensEntity imagensEntity = mapperDTOToEntity(imagensDTO);

        if(iImagensRespository.findById(id).isPresent()) {
            imagensEntity.setId_imagens(id);
            iImagensRespository.save(imagensEntity);
            return imagensDTO;
        } else {
            iImagensRespository.save(imagensEntity);
            return imagensDTO;
        }
    }

    @Override
    public ImagensDTO findById(int id) throws NotFoundException {
        ImagensEntity imagensEntity = iImagensRespository.findById(id).orElseThrow(()-> new NotFoundException("Imagem não encontrada com o id: " + id));
        ImagensDTO imagensDTO = mapperEntityToDTO(imagensEntity);
        return imagensDTO;
    }

    private ImagensEntity mapperDTOToEntity(ImagensDTO imagensDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        ImagensEntity imagensEntity = objectMapper.convertValue(imagensDTO, ImagensEntity.class);
        return imagensEntity;
    }

    private ImagensDTO mapperEntityToDTO(ImagensEntity imagensEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        ImagensDTO imagensDTO = objectMapper.convertValue(imagensEntity, ImagensDTO.class);
        return imagensDTO;
    }
}
