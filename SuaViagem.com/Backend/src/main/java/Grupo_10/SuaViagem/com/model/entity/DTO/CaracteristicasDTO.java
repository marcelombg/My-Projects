package Grupo_10.SuaViagem.com.model.entity.DTO;

import Grupo_10.SuaViagem.com.model.entity.CaracteristicasEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaracteristicasDTO {

    @Column(nullable=false)
    private Integer id_caracteristicas;

    @Column(nullable=false)
    private String nome;

    public CaracteristicasDTO() {
    }

    public CaracteristicasDTO(CaracteristicasEntity caracteristicasEntity) {
        this.id_caracteristicas = caracteristicasEntity.getId_caracteristicas();
        this.nome = caracteristicasEntity.getNome();
    }

    public Integer getId_caracteristicas() {
        return id_caracteristicas;
    }

    public void setId_caracteristicas(Integer id_caracteristicas) {
        this.id_caracteristicas = id_caracteristicas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}