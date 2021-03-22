package curso.micro.demo01.controller;

import curso.micro.demo01.entity.Greeting;
import curso.micro.demo01.entity.Producto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/Saludo") //esta es la ruta del EndPoint ..donde se encuentra el servicio
public class greetingController {

    private final AtomicLong counter = new AtomicLong(); //este objeto genera un contador automaticamente

    private static final String template = "Hola %s";

    @GetMapping
    public Greeting greeting(@RequestParam(value = "name",defaultValue = "World")  String name){
        
        return new Greeting(counter.incrementAndGet(), String.format(template,name));
    }
}
