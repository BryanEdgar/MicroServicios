package curso.micro.demo01.service;

import curso.micro.demo01.entity.Categoria;
import curso.micro.demo01.entity.Producto;

import java.util.List;

public interface CategoriaService {
    public List<Categoria> listAllCategoria();
    public Categoria getCategoria(Long id);
    public Categoria createProduct(Categoria categoria);
    public Categoria updateProduct(Categoria categoria);
    public  Categoria deleteProduct(Long id);
}
