package cl.ipss.apis70.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.ipss.apis70.models.Videojuego;

public interface VideojuegoRepository extends MongoRepository<Videojuego, String>{

}
