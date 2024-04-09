package com.example.ClimaAPI.repository;
import com.example.ClimaAPI.model.ClimaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClimaRepository extends MongoRepository<ClimaEntity, String>{


}
