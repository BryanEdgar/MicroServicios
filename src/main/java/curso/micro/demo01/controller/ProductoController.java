package curso.micro.demo01.controller;

import curso.micro.demo01.entity.Categoria;
import curso.micro.demo01.entity.Producto;
import curso.micro.demo01.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity <List <Producto>> listaProductos(@RequestParam(name = "categoriaId",
            required = false) Long categoriaId){

        List<Producto> productos = new ArrayList<>();
        if (null == categoriaId){
            productos = productoService.listAllProduct();
            if (productos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            productos = productoService.findByCategory(Categoria.builder().id(categoriaId).build());
            if (productos.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping(value = "/{id}") //Busca productos por el ID
    public  ResponseEntity<Producto> getProducto(@PathVariable("id") Long id){
        Producto producto = productoService.getProduct(id);
        if(null == producto){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto (@RequestBody Producto producto){
       Producto productonuevo = productoService.createProduct(producto);

       return ResponseEntity.status(HttpStatus.CREATED).body(productonuevo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable("id") Long id,@RequestBody Producto producto){
        producto.setId(id);

        Producto productoUpdate = productoService.updateProduct(producto);

        if(productoUpdate == null){
            return ResponseEntity.notFound().build();
        }
    return ResponseEntity.ok(productoUpdate);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Producto> deleteProducto (@PathVariable("id") Long id){

        Producto productoDelete = productoService.deleteProduct(id);

        if(productoDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoDelete);
    }

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Producto> updateStock(@PathVariable("id") Long id, @RequestParam(name = "stock",required = true) Double stock){
        Producto producto = productoService.updateStock(id,stock);

        if(producto == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto);
    }
}
