package ma.danone.danone_inv_productit.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    @JsonIgnore
    private EMP emp;

    @ManyToOne
    @JoinColumn(name = "itproduct_id")
    @JsonIgnoreProperties({"id","characteristics","serienumber","quantityStock","garantie","purchaseDate","model","state","supplier","purchaseOrder","deliveryNote","employees","departement","assignments"})
    private Itproduct itproduct;

    @ManyToOne
    @JoinColumn(name = "accessory_id")
    @JsonIgnoreProperties({"id","warranty","state","purshaseDate","purchaseOrder","deliveryNote","employees","departement","assignments"})
    private Accessory accessory;

    public String getType() {
        if (itproduct != null) {
            return itproduct.getType();
        } else if (accessory != null) {
            return accessory.getType();
        } else {
            return "Unknown";
        }
    }

    private LocalDate startDate;
    private LocalDate endDate;
}
