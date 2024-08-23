package ma.danone.danone_inv_productit.repository;

import ma.danone.danone_inv_productit.Entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Siterepository extends JpaRepository<Site,Long>
{
    Optional<Site> findByName(String sitename);
}
