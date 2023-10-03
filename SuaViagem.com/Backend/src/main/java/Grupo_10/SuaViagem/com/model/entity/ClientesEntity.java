package Grupo_10.SuaViagem.com.model.entity;

import Grupo_10.SuaViagem.com.model.entity.DTO.ClientesDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clientes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClientesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clientes", nullable = false)
    private int id_clientes;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    public ClientesEntity() {
    }

    public ClientesEntity(ClientesDTO clientesDTO) {
        this.userEntity = clientesDTO.getUserEntity();
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

}
