package Grupo_10.SuaViagem.com.model.entity;

import Grupo_10.SuaViagem.com.model.entity.DTO.CategoriasDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name  =  "categorias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategoriasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorias", nullable = false)
    private int id_categorias;

    @Column(nullable=false)
    private String descricao;

    @Column(nullable=false)
    private String url_imagem;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="categorias_produtos",
            joinColumns={@JoinColumn(name = "categorias_id")},
            inverseJoinColumns={@JoinColumn(name = "produtos_id")})
    private List<ProdutosEntity> produtosEntityList = new ArrayList<>();

    public CategoriasEntity() {
    }

    public CategoriasEntity(CategoriasDTO categoriasDTO) {
        this.id_categorias = categoriasDTO.getId_categorias();
        this.descricao = categoriasDTO.getDescricao();
        this.url_imagem = categoriasDTO.getUrl_imagem();
    }

    public CategoriasEntity(CategoriasEntity categoriasEntity) {
    }

    public int getId_categorias() {
        return id_categorias;
    }

    public void setId_categorias(int id_categorias) {
        this.id_categorias = id_categorias;
    }

    public List<ProdutosEntity> getProdutosEntityList() {
        return produtosEntityList;
    }

    public void setProdutosEntityList(List<ProdutosEntity> produtosEntityList) {
        this.produtosEntityList = produtosEntityList;
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