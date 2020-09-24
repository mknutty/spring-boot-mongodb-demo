package mkn.pizza;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.experimental.Accessors;

@Document
@Data
@Accessors(chain = true)
public class Pizza {
  private String id;
  
  @Indexed(name = "first_name_index", unique = true)
  @NotBlank
  private String name;
  
  @NotBlank
  private String type;
}
