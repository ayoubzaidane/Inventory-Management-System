package ma.danone.danone_inv_productit.Service;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ma.danone.danone_inv_productit.Entities.Itproduct;
import ma.danone.danone_inv_productit.Entities.PurshaseOrder;
import ma.danone.danone_inv_productit.Entities.Supplier;
import ma.danone.danone_inv_productit.repository.Itproductrepository;
import ma.danone.danone_inv_productit.repository.PurshaseOrderrepository;
import ma.danone.danone_inv_productit.repository.Supplierrepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
@Transactional @AllArgsConstructor
public class PurchaseOrderService
{
    private Supplierrepository supplierrepository;
    private Itproductrepository itproductrepository;
    private PurshaseOrderrepository purshaseOrderrepository;
    public PurshaseOrder createPurchaseOrder(
             String orderNumber,
             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date orderDate,
             float totalPrice,
             String serienumber,
             String supplierEmail,
             String notes
    ) {

        // Rechercher le fournisseur par son email
        Supplier supplier = supplierrepository.findByEmailAdress(supplierEmail)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));

        Itproduct product_serie = itproductrepository.findBySerienumber(serienumber)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec ce numéro de série"));

        // Créer une nouvelle instance de PurchaseOrder
        PurshaseOrder purchaseOrder = PurshaseOrder.builder()
                .orderNumber(orderNumber)
                .orderDate(orderDate)
                .totalPrice(totalPrice)
                .supplier(supplier)
                .notes(notes)
                .build();


        // Sauvegarder le PurchaseOrder dans la base de données
        purchaseOrder = purshaseOrderrepository.save(purchaseOrder);
        // Associer le produit au PurshaseOrder
        product_serie.setPurchaseOrder(purchaseOrder);
        itproductrepository.save(product_serie);

        return purchaseOrder;

    }
}

