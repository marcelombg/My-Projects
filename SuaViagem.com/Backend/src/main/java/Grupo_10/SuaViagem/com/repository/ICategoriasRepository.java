package Grupo_10.SuaViagem.com.repository;

import Grupo_10.SuaViagem.com.model.entity.CategoriasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriasRepository extends JpaRepository <CategoriasEntity, Integer> {
}