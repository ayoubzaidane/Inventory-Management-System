package ma.danone.danone_inv_productit.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class EMP
{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String matricule;

    private String email;

    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Itproduct> itproducts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Departement departement;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Accessory> accessory = new ArrayList<>();

    @OneToMany(mappedBy = "emp", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"accessory","itproduct","emp"})
    private List<Assignement> assignments = new ArrayList<>();




}
