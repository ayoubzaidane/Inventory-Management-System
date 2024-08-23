package ma.danone.danone_inv_productit.repository;

import ma.danone.danone_inv_productit.Entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Regionrepository extends JpaRepository<Region,Long>
{
    Optional<Region> findByName(String regionName);
}
