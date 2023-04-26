package Grupo_10.SuaViagem.com.model.entity.DTO;

import Grupo_10.SuaViagem.com.model.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutosDTO {

    @Column(nullable=false)
    private int id_produtos;

    @Column(nullable=false)
    private String nome;

    @Column(nullable=false)
    private String descricao;

    @Column(nullable=false)
    private String pontuacao;

    @Column(nullable=false)
    private String facilidades;

    @Column(nullable=false)
    private String localMapa;

    @Column(nullable=false)
    private String distancia;

    @Column(nullable=false)
    private String linkMapa;

    @Column(nullable=false)
    private String comentarios;

    @Column(nullable=false)
    private String verMais;

    private List<CaracteristicasEntity> caracteristicasEntityList = new ArrayList<>();

    private List<ImagensEntity> imagensEntityList = new ArrayList<>();

    private CategoriasEntity categoriasEntity;

    private CidadesEntity cidadesEntity;

    private List<ReservasEntity> reservasEntity;

    @Column(nullable=false)
    private String politicasCancelamento;

    @Column(nullable=false)
    private String saudeSegurança;

    @Column(nullable=false)
    private String normasCasa;

    public ProdutosDTO() {
    }

    public ProdutosDTO(ProdutosEntity produtosEntity) {
        this.id_produtos = produtosEntity.getId_produtos();
        this.nome = produtosEntity.getNome();
        this.descricao = produtosEntity.getDescricao();
        this.caracteristicasEntityList = produtosEntity.getCaracteristicasEntityList();
        this.imagensEntityList = produtosEntity.getImagensEntityList();
        this.categoriasEntity = produtosEntity.getCategoryEntity();
        this.cidadesEntity = produtosEntity.getCidadesEntity();
        this.pontuacao = produtosEntity.getPontuacao();
        this.facilidades = produtosEntity.getFacilidades();
        this.localMapa = produtosEntity.getLocalMapa();
        this.distancia = produtosEntity.getDistancia();
        this.linkMapa = produtosEntity.getLinkMapa();
        this.comentarios = produtosEntity.getComentarios();
        this.verMais = produtosEntity.getVerMais();
        this.politicasCancelamento = produtosEntity.getPoliticasCancelamento();
        this.saudeSegurança = produtosEntity.getSaudeSegurança();
        this.normasCasa = produtosEntity.getNormasCasa();
    }

    public List<ReservasEntity> getReservasEntity() {
        return reservasEntity;
    }

    public void setReservasEntity(List<ReservasEntity> reservasEntity) {
        this.reservasEntity = reservasEntity;
    }

    public int getId_produtos() {
        return id_produtos;
    }

    public void setId_produtos(int id_produtos) {
        this.id_produtos = id_produtos;
    }

    public CategoriasEntity getCategoriasEntity() {
        return categoriasEntity;
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

    public void setCategoryEntity(CategoriasEntity categoriasEntity) {
        this.categoriasEntity = categoriasEntity;
    }

    public CidadesEntity getCidadesEntity() {
        return cidadesEntity;
    }

    public void setCidadesEntity(CidadesEntity cidadesEntity) {
        this.cidadesEntity = cidadesEntity;
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