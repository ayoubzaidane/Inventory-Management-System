package ma.danone.danone_inv_productit.Service;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ma.danone.danone_inv_productit.Entities.*;
import ma.danone.danone_inv_productit.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
@Transactional @AllArgsConstructor
public class AccessoryService
{
    private AccessoryRepository accessoryRepository;
    private Supplierrepository supplierrepository;
    private Departementrepository departementrepository;
    private Siterepository siterepository;
    private PurshaseOrderrepository purshaseOrderrepository;
    private  DeliveryNoterepository deliveryNoterepository;
    private  Regionrepository regionrepository;
    private  Emprepository emprepository;
    private  Assignemerepository assignemerepository;

    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }

    public List<Accessory> getAccessoriesByType(String type) {
        return accessoryRepository.findByType(type);
    }

    public List<Accessory> getAccessoriesByDepartmentName(String name) {
        return accessoryRepository.findByDepartement_Name(name);
    }

    public List<Accessory> getAccessoriesBySiteName(String sitename) {
        return accessoryRepository.findByDepartement_Site_Name(sitename);
    }

    public List<Accessory> getAccessoriesByRegionName(String regionname) {
        return accessoryRepository.findByDepartement_Site_Region_Name(regionname);
    }

    public List<Accessory> getAccessoriesBySupplierEmail(String emailadresse) {
        return accessoryRepository.findBySupplier_EmailAdress(emailadresse);
    }

    public Accessory saveAccessory(MultipartFile purchaseOrderFile,
                                   MultipartFile deliveryNoteFile,
                                   String type,
                                   int warranty,
                                   State state,
                                   Date purchaseDate,
                                   List<String> empMatricules,
                                   String supplierEmail,
                                   String departmentName,
                                   String siteName,
                                   String regionName,
                                   String purchaseOrderNumber,
                                   String deliveryNoteNumber,
                                   LocalDate assignmentStartDate) throws IOException, IOException {

        // Définir les chemins où les fichiers seront stockés
        Path purchaseOrderFolderPath = Paths.get(System.getProperty("user.home"), "Danone_inventory_data", "purchase_orders");
        Path deliveryNoteFolderPath = Paths.get(System.getProperty("user.home"), "Danone_inventory_data", "delivery_notes");

        if (!Files.exists(purchaseOrderFolderPath)) {
            Files.createDirectories(purchaseOrderFolderPath);
        }
        if (!Files.exists(deliveryNoteFolderPath)) {
            Files.createDirectories(deliveryNoteFolderPath);
        }

        // Sauvegarder les fichiers uploadés
        String purchaseOrderFileName = UUID.randomUUID().toString();
        String deliveryNoteFileName = UUID.randomUUID().toString();

        Path purchaseOrderFilePath = Paths.get(purchaseOrderFolderPath.toString(), purchaseOrderFileName + ".pdf");
        Path deliveryNoteFilePath = Paths.get(deliveryNoteFolderPath.toString(), deliveryNoteFileName + ".pdf");

        Files.copy(purchaseOrderFile.getInputStream(), purchaseOrderFilePath);
        Files.copy(deliveryNoteFile.getInputStream(), deliveryNoteFilePath);

        // Récupérer les entités liées via leurs identifiants
        Supplier supplier = supplierrepository.findByEmailAdress(supplierEmail).orElseThrow();
        Departement department = departementrepository.findByName(departmentName).orElseThrow();
        Site site = siterepository.findByName(siteName).orElseThrow();
        Region region = regionrepository.findByName(regionName).orElseThrow();
        PurshaseOrder purchaseOrder = purshaseOrderrepository.findByOrderNumber(purchaseOrderNumber).orElseThrow();
        DeliveryNote deliveryNote = deliveryNoterepository.findByDeliveryNoteNumber(deliveryNoteNumber).orElseThrow();

        // Associer les fichiers uploadés aux entités correspondantes
        purchaseOrder.setFile(purchaseOrderFilePath.toUri().toString());
        deliveryNote.setFile(deliveryNoteFilePath.toUri().toString());

        purshaseOrderrepository.save(purchaseOrder);
        deliveryNoterepository.save(deliveryNote);

        // Créer l'accessoire
        Accessory accessory = Accessory.builder()
                .type(type)
                .warranty(warranty)
                .state(state)
                .purshaseDate(purchaseDate)
                .supplier(supplier)
                .departement(department)
                .purchaseOrder(purchaseOrder)
                .deliveryNote(deliveryNote)
                .build();

        accessory = accessoryRepository.save(accessory);

        // Associer l'accessoire à chaque employé
        for (String empMatricule : empMatricules) {
            EMP emp = emprepository.findByMatricule(empMatricule).orElseThrow();
            emp.getAccessory().add(accessory);
            emprepository.save(emp); // Sauvegarder les modifications de l'employé

            // Créer et sauvegarder l'assignation pour chaque employé
            Assignement assignment = Assignement.builder()
                    .startDate(assignmentStartDate)
                    .emp(emp)
                    .accessory(accessory)
                    .build();
            assignemerepository.save(assignment);
        }

        return accessory;
    }

    public byte[] getPurshaseOrderFile(Long id) throws IOException {
        Accessory accessory = accessoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Accessory not found")
        );

        String purshaseOrderFilePath = accessory.getPurchaseOrder().getFile();
        if (purshaseOrderFilePath == null) {
            throw new IOException("Purchase order file not found for this accessory");
        }

        return Files.readAllBytes(Path.of(URI.create(purshaseOrderFilePath)));
    }

    public byte[] getDeliveryNoteFile(Long id) throws IOException {
        Accessory accessory = accessoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Accessory not found")
        );

        String deliveryNoteFilePath = accessory.getDeliveryNote().getFile();
        if (deliveryNoteFilePath == null) {
            throw new IOException("Delivery note file not found for this accessory");
        }

        return Files.readAllBytes(Path.of(URI.create(deliveryNoteFilePath)));
    }
    public long getTotalAccessories() {
        return accessoryRepository.countTotalAccessories();
    }

    public long getAccessoriesByRegion(String regionName) {
        return accessoryRepository.countAccessoriesByRegion(regionName);
    }

    public long getAccessoriesBySite(String siteName) {
        return accessoryRepository.countAccessoriesBySite(siteName);
    }

    public long getAccessoriesByDepartment(String departmentName) {
        return accessoryRepository.countAccessoriesByDepartment(departmentName);
    }


}
