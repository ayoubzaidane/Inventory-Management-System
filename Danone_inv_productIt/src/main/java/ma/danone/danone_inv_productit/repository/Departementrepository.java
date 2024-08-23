package ma.danone.danone_inv_productit.repository;

import ma.danone.danone_inv_productit.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Departementrepository extends JpaRepository<Departement,Long>
{
    Optional<Departement> findByName(String name);
}
