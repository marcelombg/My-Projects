package Grupo_10.SuaViagem.com.model.entity.DTO;

import Grupo_10.SuaViagem.com.model.entity.ClientesEntity;
import Grupo_10.SuaViagem.com.model.entity.ReservasEntity;
import Grupo_10.SuaViagem.com.model.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientesDTO {

    @Column(nullable=false)
    private UserEntity userEntity;

    public ClientesDTO() {
    }

    public ClientesDTO(ClientesEntity clientesEntity) {
        this.userEntity = clientesEntity.getUserEntity();
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

}
