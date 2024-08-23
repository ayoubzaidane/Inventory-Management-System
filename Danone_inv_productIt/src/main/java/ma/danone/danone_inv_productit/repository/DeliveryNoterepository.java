package ma.danone.danone_inv_productit.repository;

import ma.danone.danone_inv_productit.Entities.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryNoterepository extends JpaRepository<DeliveryNote,Long>
{
    Optional<DeliveryNote> findByDeliveryNoteNumber(String deliveryNoteNumber);
}
