package ma.danone.danone_inv_productit.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.sql.ast.tree.update.Assignment;

import java.util.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class Itproduct
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String characteristics ;
    private String serienumber;
    private int quantityStock;
    private  int garantie;
    private Date purchaseDate;
    private String model;
    private String file;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurshaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "deliveryNote_id")
    private DeliveryNote deliveryNote;

    @ManyToMany(mappedBy = "itproducts",fetch = FetchType.EAGER)
    private List<EMP> employees = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Departement departement;

    @OneToMany(mappedBy = "itproduct", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Assignement> assignments = new ArrayList<>();




}
