package ma.danone.danone_inv_productit.repository;

import ma.danone.danone_inv_productit.Entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Supplierrepository extends JpaRepository<Supplier,Long>
{
    Optional<Supplier> findByEmailAdress(String emailAdress);

}
