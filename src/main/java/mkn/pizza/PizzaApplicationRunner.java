package mkn.pizza;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PizzaApplicationRunner implements ApplicationRunner {
  private PizzaRepository pizzaRepository;
  
  @Override
  public void run(ApplicationArguments args) throws Exception {
    pizzaRepository.save(new Pizza().setId("1").setType("pepperoni").setName("Marks-Pepperoni"));
    pizzaRepository.save(new Pizza().setType("supreme").setName("Mark's Supreme"));
    pizzaRepository.save(new Pizza().setType("pepperoni").setName("Bob's Pepperoni"));
    pizzaRepository.findAll().forEach(System.out::println);
    PageRequest pr = PageRequest.of(10, 1); 
    Page<Pizza> findAll = pizzaRepository.findAll(pr);
    System.out.println(findAll);
  }

}
