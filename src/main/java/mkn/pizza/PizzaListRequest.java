package mkn.pizza;

import java.util.Optional;

import lombok.Data;

@Data
public class PizzaListRequest {
	private String type;
	private String name;
	
	public boolean hasName() {
	  return Optional.ofNullable(name).isPresent();
	}
	
	public boolean hasType() {
	  return Optional.ofNullable(type).isPresent();
	}
}
