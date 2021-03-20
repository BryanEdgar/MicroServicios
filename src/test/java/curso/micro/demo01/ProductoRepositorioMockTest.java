package curso.micro.demo01;

import curso.micro.demo01.entity.Categoria;
import curso.micro.demo01.entity.Producto;
import curso.micro.demo01.repository.ProductoRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductoRepositorioMockTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void whenFindCategoria_then_Return_lista_Producto(){
        Producto producto01 = Producto.builder()
                .name("Computadora")
                .categoria(Categoria.builder().id(1L).build() )
                .description("Prueba")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("12.5"))
                .status("Activo")
                .create_at(new Date()).build();
        productoRepository.save(producto01);

        List<Producto> founds = productoRepository.findByCategoria(producto01.getCategoria());

        Assertions.assertThat(founds.size()).isEqualTo(1);


    }

}
