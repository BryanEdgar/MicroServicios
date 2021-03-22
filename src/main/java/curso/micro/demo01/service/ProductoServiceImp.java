package curso.micro.demo01.service;

import curso.micro.demo01.entity.Categoria;
import curso.micro.demo01.entity.Producto;
import curso.micro.demo01.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImp implements ProductoService{

    //@Autowired
    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> listAllProduct() {

        return productoRepository.findAll();
    }

    @Override
    public Producto getProduct(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto createProduct(Producto product) {
        product.setStatus("creador");
        product.setCreate_at(new Date());
        return productoRepository.save(product);
    }

    @Override
    public Producto updateProduct(Producto product) {
        Producto productoDB = getProduct(product.getId());
        if(null == productoDB){ //verifica que el producto exista
            return null;
        }
        productoDB.setName(product.getName());
        productoDB.setCategoria(product.getCategoria());
        productoDB.setStatus(product.getStatus());
        productoDB.setPrice(product.getPrice());

        return productoRepository.save(productoDB);
    }

    @Override
    public Producto deleteProduct(Long id) {
        Producto productoDB = getProduct(id);
        if(null == productoDB){ //verifica que el producto exista
            return null;
        }
        productoDB.setStatus("Eliminado"); //aqui se hace una eliminacion logica
        return productoRepository.save(productoDB);
    }

    @Override
    public List<Producto> findByCategory(Categoria category) {
        return productoRepository.findByCategoria(category);
    }

    @Override
    public Producto updateStock(Long id, Double quantity) {
        Producto productoDB = getProduct(id);
        if(null == productoDB){ //verifica que el producto exista
            return null;
        }
        Double stock = productoDB.getStock() + quantity;

        productoDB.setStock(stock);

        return productoRepository.save(productoDB);
    }
}
