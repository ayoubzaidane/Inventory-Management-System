package ma.danone.danone_inv_productit.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class Supplier
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String Phonenumber;
    private String emailAdress;

    @OneToMany(mappedBy = "supplier",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Accessory> accessories = new ArrayList<>();

    @OneToMany(mappedBy = "supplier",fetch=FetchType.EAGER)
    @JsonIgnore
    private List<Itproduct> itproducts = new ArrayList<>();

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<PurshaseOrder> purchaseOrders;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<DeliveryNote> deliveryNotes;
}
