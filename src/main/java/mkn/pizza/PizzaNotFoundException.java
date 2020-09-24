package mkn.pizza;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "pizza             s not found")
public class PizzaNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 3717801431144507492L;

}
