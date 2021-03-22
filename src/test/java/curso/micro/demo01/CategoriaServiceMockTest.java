package curso.micro.demo01;

import curso.micro.demo01.entity.Categoria;
import curso.micro.demo01.entity.Producto;
import curso.micro.demo01.repository.CategoriaRepository;
import curso.micro.demo01.service.CategoriaService;
import curso.micro.demo01.service.CategoriaServiceImp;
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
public class CategoriaServiceMockTest {
    @Mock //se utiliza para trabajar con datos mockeados, en lugar de datos de la DB
    private CategoriaRepository categoriaRepository;

    private CategoriaService categoriaService;

    @BeforeEach //indica que se tiene que ejecutar antes de relizar el test
    public void setUp(){ //en esta parte inicializa los datos
        MockitoAnnotations.initMocks(this);
        categoriaService = new CategoriaServiceImp(categoriaRepository);

        Categoria plastico =  Categoria.builder()
                .id(1L)
                .name("Plastico")
                .build();

        Mockito.when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(plastico));
        Mockito.when(categoriaRepository.save(plastico)).thenReturn(plastico);
    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Categoria found = categoriaService.getCategoria(1L);
        Assertions.assertThat(found.getName()).isEqualTo("Plastico");

    }
}
