package Grupo_10.SuaViagem.com.repository;

import Grupo_10.SuaViagem.com.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
//@Transactional
public interface IUserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByEmail(String username);

}
