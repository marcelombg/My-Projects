package Grupo_10.SuaViagem.com.repository;

import Grupo_10.SuaViagem.com.model.entity.FuncoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFuncoesRepository extends JpaRepository<FuncoesEntity, Integer> {

    Optional<FuncoesEntity> findById(int id);

}
