package Grupo_10.SuaViagem.com.model.entity;

import Grupo_10.SuaViagem.com.model.entity.DTO.ImagensDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name  =  "imagens")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImagensEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_imagens", nullable = false)
    private Integer id_imagens;
    @Column(nullable=false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imagens_entity_id")
    private ProdutosEntity produtosEntity;

    public ImagensEntity() {
    }

    public ImagensEntity(ImagensDTO imagensDTO) {
        this.id_imagens = imagensDTO.getId_imagens();
        this.url = imagensDTO.getUrl();
        this.produtosEntity = imagensDTO.getProdutosEntity();
    }

    public ProdutosEntity getProdutosEntity() {
        return produtosEntity;
    }

    public void setProdutosEntity(ProdutosEntity produtosEntity) {
        this.produtosEntity = produtosEntity;
    }

    public Integer getId_imagens() {
        return id_imagens;
    }

    public void setId_imagens(Integer id_imagens) {
        this.id_imagens = id_imagens;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}