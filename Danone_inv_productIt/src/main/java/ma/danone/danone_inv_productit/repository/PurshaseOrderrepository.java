package ma.danone.danone_inv_productit.repository;

import ma.danone.danone_inv_productit.Entities.PurshaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurshaseOrderrepository extends JpaRepository<PurshaseOrder,Long>
{
    Optional<PurshaseOrder> findByOrderNumber(String orderNumber);
}
