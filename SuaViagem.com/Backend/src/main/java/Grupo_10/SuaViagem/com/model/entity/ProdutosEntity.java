package Grupo_10.SuaViagem.com.model.entity;

import Grupo_10.SuaViagem.com.model.entity.DTO.ProdutosDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name  =  "produtos")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produtos", nullable = false)
    private int id_produtos;

    @Column(nullable=false)
    private String nome;

    @Column(nullable=false, length=1000)
    private String descricao;

    @Column(nullable=false)
    private String pontuacao;

    @Column(nullable=false)
    private String facilidades;

    @Column(nullable=false, length=1000)
    private String localMapa;

    @Column(nullable=false)
    private String distancia;

    @Column(nullable=false, length=1000)
    private String linkMapa;

    @Column(nullable=false, length=1000)
    private String comentarios;

    @Column(nullable=false, length=1000)
    private String verMais;

    @ManyToMany
    @JoinTable(name="produtos_categorias", joinColumns=
            {@JoinColumn(name="produtos_id")}, inverseJoinColumns=
            {@JoinColumn(name="categorias_id")})
    private List<CaracteristicasEntity> caracteristicasEntityList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name="produtos_id")
    private List<ImagensEntity> imagensEntityList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_entity_id")
    private CategoriasEntity categoriasEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidades_entity_id")
    private CidadesEntity cidadesEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="reservas_entity_id")
    private List<ReservasEntity> reservasEntity;

    @Column(nullable=false, length=1000)
    private String politicasCancelamento;

    @Column(nullable=false, length=1000)
    private String saudeSegurança;

    @Column(nullable=false, length=1000)
    private String normasCasa;

    public ProdutosEntity() {
    }

    public ProdutosEntity(ProdutosDTO produtosDTO) {
        this.id_produtos = produtosDTO.getId_produtos();
        this.nome = produtosDTO.getNome();
        this.descricao = produtosDTO.getDescricao();
        this.caracteristicasEntityList = produtosDTO.getCaracteristicasEntityList();
        this.imagensEntityList = produtosDTO.getImagensEntityList();
        this.categoriasEntity = new CategoriasEntity(produtosDTO.getCategoriasEntity());
        this.cidadesEntity = produtosDTO.getCidadesEntity();
        this.pontuacao = produtosDTO.getPontuacao();
        this.facilidades = produtosDTO.getFacilidades();
        this.localMapa = produtosDTO.getLocalMapa();
        this.distancia = produtosDTO.getDistancia();
        this.linkMapa = produtosDTO.getLinkMapa();
        this.comentarios = produtosDTO.getComentarios();
        this.verMais = produtosDTO.getVerMais();
        this.politicasCancelamento = produtosDTO.getPoliticasCancelamento();
        this.saudeSegurança = produtosDTO.getSaudeSegurança();
        this.normasCasa = produtosDTO.getNormasCasa();
    }

    public List<ReservasEntity> getReservasEntity() {
        return reservasEntity;
    }

    public void setReservasEntity(List<ReservasEntity> reservasEntity) {
        this.reservasEntity = reservasEntity;
    }

    public String getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(String pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getFacilidades() {
        return facilidades;
    }

    public void setFacilidades(String facilidades) {
        this.facilidades = facilidades;
    }

    public String getLocalMapa() {
        return localMapa;
    }

    public void setLocalMapa(String localMapa) {
        this.localMapa = localMapa;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getLinkMapa() {
        return linkMapa;
    }

    public void setLinkMapa(String linkMapa) {
        this.linkMapa = linkMapa;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getVerMais() {
        return verMais;
    }

    public void setVerMais(String verMais) {
        this.verMais = verMais;
    }

    public CategoriasEntity getCategoriasEntity() {
        return categoriasEntity;
    }

    public int getId_produtos() {
        return id_produtos;
    }

    public void setId_produtos(int id_produtos) {
        this.id_produtos = id_produtos;
    }

    public void setCategoriasEntity(CategoriasEntity categoriasEntity) {
        this.categoriasEntity = categoriasEntity;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<CaracteristicasEntity> getCaracteristicasEntityList() {
        return caracteristicasEntityList;
    }

    public void setCaracteristicasEntityList(List<CaracteristicasEntity> caracteristicasEntityList) {
        this.caracteristicasEntityList = caracteristicasEntityList;
    }

    public List<ImagensEntity> getImagensEntityList() {
        return imagensEntityList;
    }

    public void setImagensEntityList(List<ImagensEntity> imagensEntityList) {
        this.imagensEntityList = imagensEntityList;
    }

    public CategoriasEntity getCategoryEntity() {
        return categoriasEntity;
    }

    public void setCategoryEntity(CategoriasEntity categoriasEntity) {
        this.categoriasEntity = categoriasEntity;
    }

    public CidadesEntity getCidadesEntity() {
        return cidadesEntity;
    }

    public void setCidadesEntity(CidadesEntity cidadesEntity) {
        this.cidadesEntity = cidadesEntity;
    }

    public String getPoliticasCancelamento() {
        return politicasCancelamento;
    }

    public void setPoliticasCancelamento(String politicasCancelamento) {
        this.politicasCancelamento = politicasCancelamento;
    }

    public String getSaudeSegurança() {
        return saudeSegurança;
    }

    public void setSaudeSegurança(String saudeSegurança) {
        this.saudeSegurança = saudeSegurança;
    }

    public String getNormasCasa() {
        return normasCasa;
    }

    public void setNormasCasa(String normasCasa) {
        this.normasCasa = normasCasa;
    }
}