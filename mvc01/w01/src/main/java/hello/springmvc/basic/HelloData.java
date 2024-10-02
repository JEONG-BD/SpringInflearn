package hello.springmvc.basic;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class HelloData {
    private String username;
    private int age;
}
