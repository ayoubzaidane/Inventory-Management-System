package ma.danone.danone_inv_productit.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class PurshaseOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String orderNumber;
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    private float totalPrice;
    private String notes;
    private String file;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @JsonIgnoreProperties({"id","firstname"})
    private Supplier supplier;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Itproduct> itproducts = new ArrayList<>();

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Accessory> accessories = new ArrayList<>();
}
