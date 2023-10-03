package Grupo_10.SuaViagem.com.model.entity;

import Grupo_10.SuaViagem.com.model.entity.DTO.ReservasDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name  =  "reservas")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIgnoreProperties({"produtosEntity"})
public class ReservasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservas", nullable = false)
    private int id_reservas;
    @Column(nullable=false)
    private Time horaInicial;

    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    private java.util.Date dataInicial;

    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    private java.util.Date dataFinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="produtos_id")
    private ProdutosEntity produtosEntity;

    @Column(nullable=false)
    private int idUser;

    public ReservasEntity() {
    }

    public ReservasEntity(ReservasDTO reservasDTO, int idUser) {
        this.id_reservas = reservasDTO.getId_reservas();
        this.horaInicial = reservasDTO.getHoraInicial();
        this.dataInicial = reservasDTO.getDataInicial();
        this.dataFinal = reservasDTO.getDataFinal();
        this.produtosEntity = reservasDTO.getProdutosEntity();
        this.idUser = reservasDTO.getIdUser();
    }

    public int getId_reservas() {
        return id_reservas;
    }

    public void setId_reservas(int id_reservas) {
        this.id_reservas = id_reservas;
    }

    public Time getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Time hora_inicio_reserva) {
        this.horaInicial = hora_inicio_reserva;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public ProdutosEntity getProdutosEntity() {
        return produtosEntity;
    }

    public void setProdutosEntity(ProdutosEntity produtosEntityList) {
        this.produtosEntity = produtosEntityList;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
