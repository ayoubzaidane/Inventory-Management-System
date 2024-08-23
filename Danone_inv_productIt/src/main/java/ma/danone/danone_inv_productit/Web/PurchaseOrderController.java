package ma.danone.danone_inv_productit.Web;
import lombok.AllArgsConstructor;
import ma.danone.danone_inv_productit.Entities.PurshaseOrder;
import ma.danone.danone_inv_productit.Service.PurchaseOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController @AllArgsConstructor
@RequestMapping("/purchaseOrders")
public class PurchaseOrderController
{
    private PurchaseOrderService purchaseOrderService;
    @PostMapping("/create")
    public PurshaseOrder createPurchaseOrder(
            @RequestParam String orderNumber,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date orderDate,
            @RequestParam float totalPrice,
            @RequestParam String serienumber,
            @RequestParam String supplierEmail,
            @RequestParam String notes
           )
    {
        return purchaseOrderService.createPurchaseOrder(orderNumber,orderDate,
                totalPrice,serienumber,supplierEmail,notes);

    }
}
