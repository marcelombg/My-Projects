package Grupo_10.SuaViagem.com.repository;

import Grupo_10.SuaViagem.com.model.entity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IProdutosRepository extends JpaRepository <ProdutosEntity, Integer> {

    List<ProdutosEntity> findByCategoriasEntityDescricao(String category);

    List<ProdutosEntity> findByCidadesEntityNome(String cidade);

    @Query("SELECT p, COUNT(r) FROM ProdutosEntity p JOIN p.cidadesEntity c LEFT JOIN p.reservasEntity r WHERE c.nome = :cidade AND (r is null OR r.dataInicial > :dataFinal OR r.dataFinal < :dataInicial) AND NOT EXISTS (SELECT 1 FROM p.reservasEntity r2 WHERE r2.dataInicial BETWEEN :dataInicial AND :dataFinal OR r2.dataFinal BETWEEN :dataInicial AND :dataFinal) GROUP BY p.id")
    List<ProdutosEntity> findByCidadeAndDatas(@Param("cidade") String cidade,
                                              @Param("dataInicial") Date dataInicial,
                                              @Param("dataFinal") Date dataFinal);

}