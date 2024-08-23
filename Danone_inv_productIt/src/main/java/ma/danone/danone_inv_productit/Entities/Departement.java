package ma.danone.danone_inv_productit.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Departement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String adress;

    @OneToMany(mappedBy = "departement")
    @JsonIgnore
    private List<EMP> employedep = new ArrayList<>();

    @OneToMany(mappedBy = "departement")
    @JsonIgnore
    private List<Itproduct> productsit = new ArrayList<>();

    @OneToMany(mappedBy = "departement")
    @JsonIgnore
    private List<Accessory> accessory = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id")
    private Site site;



}
