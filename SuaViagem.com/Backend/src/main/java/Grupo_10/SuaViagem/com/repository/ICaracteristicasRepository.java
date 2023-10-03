package Grupo_10.SuaViagem.com.repository;

import Grupo_10.SuaViagem.com.model.entity.CaracteristicasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICaracteristicasRepository extends JpaRepository<CaracteristicasEntity, Integer> {
}
