package Grupo_10.SuaViagem.com.model.entity;

import Grupo_10.SuaViagem.com.model.entity.DTO.CaracteristicasDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name  =  "caracteristicas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CaracteristicasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caracteristicas", nullable = false)
    private Integer id_caracteristicas;

    @Column(nullable=false)
    private String nome;

    @ManyToMany(mappedBy="caracteristicasEntityList")
    @JsonIgnore
    private List<ProdutosEntity> produtosEntityList = new ArrayList<>();

    public CaracteristicasEntity() {
    }

    public CaracteristicasEntity(CaracteristicasDTO caracteristicasDTO) {
        this.id_caracteristicas = caracteristicasDTO.getId_caracteristicas();
        this.nome = caracteristicasDTO.getNome();
    }

    public Integer getId_caracteristicas() {
        return id_caracteristicas;
    }

    public void setId_caracteristicas(Integer id_caracteristicas) {
        this.id_caracteristicas = id_caracteristicas;
    }

    public List<ProdutosEntity> getProdutosEntityList() {
        return produtosEntityList;
    }

    public void setProdutosEntityList(List<ProdutosEntity> produtosEntityList) {
        this.produtosEntityList = produtosEntityList;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}