package org.intro.examen.repository;

import org.intro.examen.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class that handles the requests to the store database
 */
@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    Item findBy_id(String _id);
    Item findByTitle(String title);
    Boolean existsBy_id(String _id);
    Boolean existsByTitle(String title);
    @Query("{ 'category' : ?0 }")
    List<Item> findByCategory(String category);
    @Query(value = "{}", count = true)
    long countById();
    @Query("{rate : {$gt : ?0}}")
    List<Item> findByRateGreaterThan(double rate);
}
