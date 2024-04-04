package com.example.BD_RestAPI.repository;

import com.example.BD_RestAPI.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    //Metodos de CRUD já estão disponiveis
    //findAll, findBy, save, deleteById

    //Consultas personalizadas
    List<UserEntity> findByNome(String nome);
    List<UserEntity> findByEmail(String email);

    // Utilizando consultas personalizadas
    List<UserEntity> findByNomeIgnoreCase(String nome);
    List<UserEntity> findByEmailIgnoreCase(String email);

    // Consulta personalizada para encontrar usuários pelo nome e email
    List<UserEntity> findByNomeAndEmailAllIgnoreCase(String nome, String email);

    // Consulta personalizada para encontrar usuários pelo nome que começa com um determinado prefixo
    List<UserEntity> findByNomeStartingWithIgnoreCase(String prefix);

    // Consulta personalizada para encontrar usuários pelo nome que contenham uma determinada string
    List<UserEntity> findByNomeContainingIgnoreCase(String substring);

}
