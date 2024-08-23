package ma.danone.danone_inv_productit.repository;

import ma.danone.danone_inv_productit.Entities.EMP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Emprepository extends JpaRepository<EMP,Long>
{
    Optional<EMP> findByMatricule(String matricule);

}
