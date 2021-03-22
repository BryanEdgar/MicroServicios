package curso.micro.demo01;

import curso.micro.demo01.entity.Categoria;
import curso.micro.demo01.entity.Producto;
import curso.micro.demo01.repository.ProductoRepository;
import curso.micro.demo01.service.ProductoService;
import curso.micro.demo01.service.ProductoServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductoServiceMockTest {

    @Mock //se utiliza para trabajar con datos mockeados, en lugar de datos de la DB
    private ProductoRepository productoRepository;

    private ProductoService productoService;

    @BeforeEach //indica que se tiene que ejecutar antes de relizar el test
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        productoService = new ProductoServiceImp(productoRepository);

        Producto computer =  Producto.builder()
                .id(1L)
                .name("computer")
                .categoria(Categoria.builder().id(1L).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();

        Mockito.when(productoRepository.findById(1L))
                .thenReturn(Optional.of(computer));
        Mockito.when(productoRepository.save(computer)).thenReturn(computer);
    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Producto found = productoService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");

    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Producto newStock = productoService.updateStock(1L,Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
    }
}
