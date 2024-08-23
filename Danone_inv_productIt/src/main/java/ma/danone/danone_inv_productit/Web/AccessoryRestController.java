package ma.danone.danone_inv_productit.Web;
import lombok.AllArgsConstructor;
import ma.danone.danone_inv_productit.Entities.*;
import ma.danone.danone_inv_productit.Service.AccessoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@RestController @AllArgsConstructor
@RequestMapping("/accessories")
public class AccessoryRestController
{
    private AccessoryService accessoryService;
    @GetMapping
     public List<Accessory> getAllaccessories(){
         return accessoryService.getAllAccessories();

     }

    @GetMapping("/type/{type}")
    public List<Accessory> getAccessoriesByType(@PathVariable String type) {
        return accessoryService.getAccessoriesByType(type);
    }

    @GetMapping("/department/{name}")
    public List<Accessory> getAccessoriesByDepartmentName(@PathVariable String name) {
        return accessoryService.getAccessoriesByDepartmentName(name);
    }

    @GetMapping("/site/{sitename}")
    public List<Accessory> getAccessoriesBySiteName(@PathVariable String sitename) {
        return accessoryService.getAccessoriesBySiteName(sitename);
    }

    @GetMapping("/region/{regionname}")
    public List<Accessory> getAccessoriesByRegionName(@PathVariable String regionname) {
        return accessoryService.getAccessoriesByRegionName(regionname);
    }

    @GetMapping("/supplier/{emailadresse}")
    public List<Accessory> getAccessoriesBySupplierEmail(@PathVariable String emailadresse) {
        return accessoryService.getAccessoriesBySupplierEmail(emailadresse);
    }

    @PostMapping(value = "/saveAccessory", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Accessory saveAccessory(@RequestParam MultipartFile purchaseOrderFile,
                                   @RequestParam MultipartFile deliveryNoteFile,
                                   @RequestParam String type,
                                   @RequestParam int warranty,
                                   @RequestParam State state,
                                   @RequestParam Date purchaseDate,
                                   @RequestParam List<String> empMatricules,
                                   @RequestParam String supplierEmail,
                                   @RequestParam String departmentName,
                                   @RequestParam String siteName,
                                   @RequestParam String regionName,
                                   @RequestParam String purchaseOrderNumber,
                                   @RequestParam String deliveryNoteNumber,
                                   @RequestParam LocalDate assignmentStartDate) throws IOException
    {
        return accessoryService.saveAccessory(purchaseOrderFile,deliveryNoteFile,type,
                warranty,state,purchaseDate,empMatricules,supplierEmail,departmentName,
                siteName,regionName,purchaseOrderNumber,deliveryNoteNumber,assignmentStartDate);

    }
    @GetMapping(path = "/{id}/purshaseOrderFile", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPurshaseOrderFile(@PathVariable Long id) throws IOException {
        return accessoryService.getPurshaseOrderFile(id);
    }

    @GetMapping(path = "/{id}/deliveryNoteFile", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getDeliveryNoteFile(@PathVariable Long id) throws IOException {
        return accessoryService.getDeliveryNoteFile(id);
    }

    @GetMapping("/count")
    public long getTotalAccessories() {
        return accessoryService.getTotalAccessories();
    }

    @GetMapping("/count/region/{regionName}")
    public long getAccessoriesByRegion(@PathVariable String regionName) {
        return accessoryService.getAccessoriesByRegion(regionName);
    }

    @GetMapping("/count/site/{siteName}")
    public long getAccessoriesBySite(@PathVariable String siteName) {
        return accessoryService.getAccessoriesBySite(siteName);
    }

    @GetMapping("/count/department/{departmentName}")
    public long getAccessoriesByDepartment(@PathVariable String departmentName) {
        return accessoryService.getAccessoriesByDepartment(departmentName);
    }


}
