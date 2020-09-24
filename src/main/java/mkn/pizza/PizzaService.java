package mkn.pizza;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PizzaService {
  private PizzaRepository repository;
  
  public List<Pizza> list(PizzaListRequest request) {
    if (request.hasName() && request.hasType()) {
      return repository.findByTypeAndNameContaining(request.getType(), request.getType());
    } else if (request.hasName()) {
      return repository.findByNameContaining(request.getName());
    } else if (request.hasType()) {
      return repository.findByType(request.getType());
    } else {
      return repository.findAll();
    }
  }
  
  public Pizza update(PizzaUpdateRequest request) {
    return repository.save(
      repository.findById(request.getId())
        .orElseThrow(PizzaNotFoundException::new)
        .setType(request.getType())
    );
  }
}
