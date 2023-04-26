package Grupo_10.SuaViagem.com.model.entity.DTO;

import Grupo_10.SuaViagem.com.model.entity.ImagensEntity;
import Grupo_10.SuaViagem.com.model.entity.ProdutosEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagensDTO {

    @Column(nullable=false)
    private Integer id_imagens;

    @Column(nullable=false)
    private String url;

    private ProdutosEntity produtosEntity;

    public ImagensDTO() {
    }

    public ImagensDTO(ImagensEntity imagensEntity) {
        this.id_imagens = imagensEntity.getId_imagens();
        this.url = imagensEntity.getUrl();
        this.produtosEntity = imagensEntity.getProdutosEntity();
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