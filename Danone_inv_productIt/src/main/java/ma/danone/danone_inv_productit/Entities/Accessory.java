package ma.danone.danone_inv_productit.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Accessory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int warranty;
    @Enumerated(EnumType.STRING)
    private State state;
    private Date purshaseDate;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurshaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "deliveryNote_id")
    private DeliveryNote deliveryNote;

    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;

    @ManyToMany(mappedBy = "accessory",fetch = FetchType.EAGER)
    private List<EMP> employees = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Departement departement;

    @OneToMany(mappedBy = "accessory", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Assignement> assignments = new ArrayList<>();
}
