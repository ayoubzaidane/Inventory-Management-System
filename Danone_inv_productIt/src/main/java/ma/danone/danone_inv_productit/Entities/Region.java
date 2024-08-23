package ma.danone.danone_inv_productit.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Region
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "region",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Site> sites = new ArrayList<>();
}
