package mkn.pizza;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaRepository extends MongoRepository<Pizza, String> {

  Optional<Pizza> findByName(String name);

  List<Pizza> findByType(String type);
  
  List<Pizza> findByNameContaining(String name);
  
  List<Pizza> findByTypeAndNameContaining(String type, String name);

  Page<Pizza> findByType(String string, Pageable pageable);

  Slice<Pizza> findSlicedByType(String string, Pageable pageable);
  
  List<PizzaTypeView> findTypesBy();
}
