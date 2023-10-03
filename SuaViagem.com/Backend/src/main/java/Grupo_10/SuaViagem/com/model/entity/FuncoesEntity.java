package Grupo_10.SuaViagem.com.model.entity;

import Grupo_10.SuaViagem.com.model.entity.DTO.FuncoesDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name  =  "funcoes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FuncoesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcoes", nullable = false)
    private int id_funcoes;

    @Column(nullable=false)
    private String nome;

    public FuncoesEntity(FuncoesDTO funcoesDTO) {
        this.id_funcoes = funcoesDTO.getId_funcoes();
        this.nome = funcoesDTO.getNome();
    }

    public FuncoesEntity() {
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
