package ma.danone.danone_inv_productit.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class DeliveryNote
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deliveryNoteNumber;
    private Date deliveryDate;
    private String file;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @JsonIgnoreProperties({"id","firstname"})
    private Supplier supplier;

    @OneToMany(mappedBy = "deliveryNote", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Itproduct> itProducts;

    @OneToMany(mappedBy = "deliveryNote", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Accessory> accessories;
}
