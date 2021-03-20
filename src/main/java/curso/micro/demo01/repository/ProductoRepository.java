package curso.micro.demo01.repository;

import curso.micro.demo01.entity.Categoria;
import curso.micro.demo01.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    public List<Producto> findByCategoria(Categoria categoria);

}
