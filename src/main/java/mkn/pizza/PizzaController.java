package mkn.pizza;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping({"pizzas", "pizza"})
public class PizzaController {
  private PizzaRepository repository;
  private PizzaService service;
  
  @GetMapping("{id}")
  public Pizza get(@PathVariable String id) {
    return repository.findById(id).orElseThrow(PizzaNotFoundException::new);
  }
  
  // name will be required because @RequestParam was used and it defaults to required.
  @GetMapping("/")
  public Pizza getByName(@RequestParam String name) {
    return repository.findByName(name).orElseThrow(PizzaNotFoundException::new);
  }
  
  // get parameters are converted to an object;
  @GetMapping
  public List<Pizza> list(PizzaListRequest request) {
    return service.list(request);
  }
  
  // pageable is not required but if not passed (parameters of page, size, and sort) it will create a default Pageable.
  @GetMapping("paged")
  public Page<Pizza> paged(Optional<String> type, Pageable pageable) {
    return type.isPresent() ? repository.findByType(type.get(), pageable) : repository.findAll(pageable);
  }
  
  @GetMapping("sliced")
  public Slice<Pizza> sliced(Optional<String> type, Pageable pageable) {
    return type.isPresent() ? repository.findSlicedByType(type.get(), pageable) : repository.findAll(pageable);
  }
  
  // PizzaTypeView is a projection
  @GetMapping("types")
  public List<PizzaTypeView> getTypes() {
    return repository.findTypesBy();
  }
  
  @PostMapping
  public Pizza add(@Validated @RequestBody Pizza pizza) {
    return repository.save(pizza);
  }
  
  @PutMapping
  public Pizza update(@Validated @RequestBody PizzaUpdateRequest request) {
    return service.update(request);
  }
  
  @DeleteMapping("{id}")
  public void delete(@PathVariable String id) {
    repository.delete(repository.findById(id).orElseThrow(PizzaNotFoundException::new));
  }
}
