package Grupo_10.SuaViagem.com.model.entity.DTO;

import Grupo_10.SuaViagem.com.model.entity.CategoriasEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriasDTO {

    @Column(nullable=false)
    private int id_categorias;

    @Column(nullable=false)
    private String descricao;

    @Column(nullable=false)
    private String url_imagem;

    public CategoriasDTO() {
    }
    public CategoriasDTO(CategoriasEntity categoriasEntity) {
        this.id_categorias = categoriasEntity.getId_categorias();
        this.descricao = categoriasEntity.getDescricao();
        this.url_imagem = categoriasEntity.getUrl_imagem();
    }

    public int getId_categorias() {
        return id_categorias;
    }

    public void setId_categorias(int id_categorias) {
        this.id_categorias = id_categorias;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }
}