package com.example.TabelaFipe.repository;

import com.example.TabelaFipe.model.FipeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FipeRepository extends MongoRepository<FipeEntity, String> {

}
