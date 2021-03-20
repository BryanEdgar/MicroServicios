package curso.micro.demo01.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Greeting {

    private Long id;

    private String message;

    public Greeting() {
    }

    public Greeting(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}
