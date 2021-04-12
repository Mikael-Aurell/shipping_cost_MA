package se.lexicon.shipping_cost.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.shipping_cost.entity.Box;

@Repository
public interface BoxRepository extends CrudRepository<Box, String> {


}
