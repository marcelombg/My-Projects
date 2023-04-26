package Grupo_10.SuaViagem.com.service.impl;

import Grupo_10.SuaViagem.com.model.entity.DTO.UserDTO;
import Grupo_10.SuaViagem.com.model.entity.FuncoesEntity;
import Grupo_10.SuaViagem.com.model.entity.UserEntity;
import Grupo_10.SuaViagem.com.repository.IFuncoesRepository;
import Grupo_10.SuaViagem.com.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IFuncoesRepository iFuncoesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public Boolean create(UserDTO userDTO){
        UserEntity userEntity = new UserEntity(userDTO);
        String senha = bCryptPasswordEncoder.encode(userEntity.getSenha());
        userEntity.setSenha(senha);

        Optional<FuncoesEntity> optionalFuncoesEntity = iFuncoesRepository.findById(userDTO.getFuncoesEntity().getId_funcoes());

        if (optionalFuncoesEntity.isPresent()) {
            userEntity.setFuncoesEntity(optionalFuncoesEntity.get());
        }

        try {
            userRepository.save(userEntity);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
