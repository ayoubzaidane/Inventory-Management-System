package ma.danone.danone_inv_productit.Web;
import lombok.AllArgsConstructor;
import ma.danone.danone_inv_productit.Entities.*;
import ma.danone.danone_inv_productit.Service.ItproductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController @AllArgsConstructor
@RequestMapping(path = "/products")
public class Itproductcontroller
{
    private ItproductService itproductService;

    @GetMapping
    public List<Itproduct> getAllProducts() {
        return itproductService.getAllProducts();
    }

    @GetMapping(path = "/{id}")
    public Itproduct getProductById(@PathVariable("id") Long id)
    {
        return itproductService.getProductById(id);

    }

    @GetMapping("/type/{type}")
    public List<Itproduct> getProductsByType(@PathVariable String type) {
        return itproductService.getProductsByType(type);
    }

    @GetMapping("/serienumber/{serienumber}")
    public Optional<Itproduct> getProductBySerienumber(@PathVariable String serienumber)
    {
        return itproductService.getProductBySerienumber(serienumber);
    }

    @GetMapping("/model/{model}")
    public List<Itproduct> getProductsByModel(@PathVariable String model) {
        return itproductService.getProductsByModel(model);
    }

    @GetMapping("/department/{name}")
    public List<Itproduct> getProductsByDepartmentName(@PathVariable String name) {
        return itproductService.getProductsByDepartmentName(name);
    }

    @GetMapping("/site/{sitename}")
    public List<Itproduct> getProductsBySiteName(@PathVariable String sitename) {
        return itproductService.getProductsBySiteName(sitename);
    }

    @GetMapping("/region/{regionname}")
    public List<Itproduct> getProductsByRegionName(@PathVariable String regionname) {
        return itproductService.getProductsByRegionName(regionname);
    }

    @GetMapping("/supplier/{emailadresse}")
    public List<Itproduct> getProductsBySupplierEmail(@PathVariable String emailadresse) {
        return itproductService.getProductsBySupplierEmail(emailadresse);
    }

    @PutMapping("/{id}/state")
    public Itproduct updateItproduct(@RequestParam State state, @PathVariable Long id)
    {
        return itproductService.updateItStateproduct(state,id);
    }

    @PostMapping(value = "/saveitProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Itproduct saveitProduct(@RequestParam MultipartFile purchaseOrderFile,
                                   @RequestParam MultipartFile deliveryNoteFile,
                                   @RequestParam String type ,
                                   @RequestParam String characteristics ,
                                   @RequestParam String serienumber ,
                                   @RequestParam  int quantitystock ,
                                   @RequestParam  int garantie ,
                                   @RequestParam  Date purchaseDate ,
                                   @RequestParam  String model ,
                                   @RequestParam  State state,
                                   @RequestParam  List<String> empMatricules,
                                   @RequestParam  String supplierEmail,
                                   @RequestParam  String departmentName,
                                   @RequestParam String siteName,
                                   @RequestParam  String regionName ,
                                   @RequestParam  String purchaseOrderNumber,
                                   @RequestParam String deliveryNoteNumber,
                                   @RequestParam LocalDate assignmentStartDate) throws IOException {
        return itproductService.saveitProduct(purchaseOrderFile,
                deliveryNoteFile,
                type,characteristics,serienumber,quantitystock,garantie,
                purchaseDate,model,state,empMatricules,supplierEmail,
                departmentName,siteName,regionName,purchaseOrderNumber,
                deliveryNoteNumber,assignmentStartDate);
    }

    @GetMapping(path = "/deliveryNoteFile/{itproductId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getDeliveryNoteFile(@PathVariable Long itproductId) throws IOException {
       return itproductService.getDeliveryNoteFile(itproductId);
    }

    @GetMapping(path = "/purchaseOrderFile/{itproductId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPurchaseOrderFile(@PathVariable Long itproductId) throws IOException
    {
        return itproductService.getPurchaseOrderFile(itproductId);
    }

    @GetMapping("/count")
    public long getTotalProducts() {
        return itproductService.getTotalProducts();
    }

    @GetMapping("/count/region/{regionName}")
    public long getProductsByRegion(@PathVariable String regionName) {
        return itproductService.getProductsByRegion(regionName);
    }

    @GetMapping("/count/site/{siteName}")
    public long getProductsBySite(@PathVariable String siteName) {
        return itproductService.getProductsBySite(siteName);
    }

    @GetMapping("/count/department/{departmentName}")
    public long getProductsByDepartment(@PathVariable String departmentName) {
        return itproductService.getProductsByDepartment(departmentName);
    }



}


