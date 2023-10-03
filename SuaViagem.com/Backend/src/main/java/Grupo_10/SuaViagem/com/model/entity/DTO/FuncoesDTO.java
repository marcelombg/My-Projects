package Grupo_10.SuaViagem.com.model.entity.DTO;

import Grupo_10.SuaViagem.com.model.entity.FuncoesEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FuncoesDTO {

    @Column(nullable=false)
    private int id_funcoes;

    @Column(nullable=false)
    private String nome;

    public FuncoesDTO(FuncoesEntity funcoesEntity) {
        this.id_funcoes = funcoesEntity.getId_funcoes();
        this.nome = funcoesEntity.getNome();
    }

    public FuncoesDTO() {
    }

    public int getId_funcoes() {
        return id_funcoes;
    }

    public void setId_funcoes(int id_funcoes) {
        this.id_funcoes = id_funcoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
