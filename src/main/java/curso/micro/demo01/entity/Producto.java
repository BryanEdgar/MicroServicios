package curso.micro.demo01.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_products")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double stock;

    private Double price;

    private String status;

    @Column(name = "create_at") //cuando el nombre de la columna en la DB es diferente
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Categoria categoria;

}
