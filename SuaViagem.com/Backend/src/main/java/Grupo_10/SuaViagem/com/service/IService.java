package Grupo_10.SuaViagem.com.service;

import Grupo_10.SuaViagem.com.exception.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IService<T> {

    T register(T t) throws NotFoundException;
    List<T> findAll();
    String delete(int id);
    T edit(T t, int id);
    T findById(int id) throws NotFoundException;
}