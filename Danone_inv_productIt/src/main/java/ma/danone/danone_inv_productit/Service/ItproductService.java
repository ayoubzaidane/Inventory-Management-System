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
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional @AllArgsConstructor
public class ItproductService
{
    private Itproductrepository itproductrepository;
    private  Supplierrepository supplierrepository;
    private  DeliveryNoterepository deliveryNoterepository;
    private  Emprepository emprepository;
    private  PurshaseOrderrepository purshaseOrderrepository;
    private  Departementrepository departementrepository;
    private  Regionrepository regionrepository;
    private  Siterepository siterepository;
    private  Assignemerepository assignemerepository;

    public List<Itproduct> getAllProducts()
    {
        return itproductrepository.findAll();
    }
    public Itproduct getProductById(Long id) {
        return itproductrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Product with this ID " + id + " doesn't exist"));
    }
    public List<Itproduct> getProductsByType(String type) {
        return itproductrepository.findByType(type);
    }

    public Optional<Itproduct> getProductBySerienumber(String serienumber) {
        return itproductrepository.findBySerienumber(serienumber);
    }

    public List<Itproduct> getProductsByModel(String model) {
        return itproductrepository.findByModel(model);
    }

    public List<Itproduct> getProductsByDepartmentName(String name) {
        return itproductrepository.findByDepartement_Name(name);
    }

    public List<Itproduct> getProductsBySiteName(String sitename) {
        return itproductrepository.findByDepartement_Site_Name(sitename);
    }

    public List<Itproduct> getProductsByRegionName(String regionname) {
        return itproductrepository.findByDepartement_Site_Region_Name(regionname);
    }

    public List<Itproduct> getProductsBySupplierEmail(String emailadresse) {
        return itproductrepository.findBySupplier_EmailAdress(emailadresse);
    }

    public Itproduct updateItStateproduct(State state, Long id) {
        Itproduct itproduct = itproductrepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("This product does not exist"));
        itproduct.setState(state); // Update State
        return itproductrepository.save(itproduct);
    }
    public Itproduct saveitProduct(MultipartFile purchaseOrderFile, MultipartFile deliveryNoteFile, String type,
                                   String characteristics, String serienumber, int quantitystock, int garantie,
                                   Date purchaseDate, String model, State state, List<String> empMatricules,
                                   String supplierEmail, String departmentName, String siteName, String regionName,
                                   String purchaseOrderNumber, String deliveryNoteNumber, LocalDate assignmentStartDate)
            throws IOException, IOException {

        Path folderPath = Paths.get(System.getProperty("user.home"), "Danone_inventory_data", "itproducts_danone");
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        // Enregistrer les fichiers
        String purchaseOrderFileName = UUID.randomUUID().toString();
        Path purchaseOrderFilePath = Paths.get(folderPath.toString(), purchaseOrderFileName + ".pdf");
        Files.copy(purchaseOrderFile.getInputStream(), purchaseOrderFilePath);

        String deliveryNoteFileName = UUID.randomUUID().toString();
        Path deliveryNoteFilePath = Paths.get(folderPath.toString(), deliveryNoteFileName + "_deliveryNote.pdf");
        Files.copy(deliveryNoteFile.getInputStream(), deliveryNoteFilePath);

        // Trouver les entités associées
        Supplier supplier = supplierrepository.findByEmailAdress(supplierEmail).orElseThrow();
        Departement department = departementrepository.findByName(departmentName).orElseThrow();
        Site site = siterepository.findByName(siteName).orElseThrow();
        Region region = regionrepository.findByName(regionName).orElseThrow();
        PurshaseOrder purchaseOrder = purshaseOrderrepository.findByOrderNumber(purchaseOrderNumber).orElseThrow();
        DeliveryNote deliveryNote = deliveryNoterepository.findByDeliveryNoteNumber(deliveryNoteNumber).orElseThrow();

        // Créer et sauvegarder le produit IT
        Itproduct itproduct = Itproduct.builder()
                .type(type)
                .characteristics(characteristics)
                .serienumber(serienumber)
                .quantityStock(quantitystock)
                .garantie(garantie)
                .purchaseDate(purchaseDate)
                .model(model)
                .state(state)
                .supplier(supplier)
                .departement(department)
                .purchaseOrder(purchaseOrder)
                .deliveryNote(deliveryNote)
                .file(purchaseOrderFilePath.toUri().toString())
                .file(deliveryNoteFilePath.toUri().toString())
                .build();
        itproduct = itproductrepository.save(itproduct);

        // Associer le produit aux employés et créer les assignations
        for (String matricule : empMatricules) {
            EMP emp = emprepository.findByMatricule(matricule).orElseThrow();
            emp.getItproducts().add(itproduct);
            emprepository.save(emp);

            Assignement assignment = Assignement.builder()
                    .startDate(assignmentStartDate)
                    .emp(emp)
                    .itproduct(itproduct)
                    .build();
            assignemerepository.save(assignment);
        }

        return itproduct;
    }

    public byte[] getDeliveryNoteFile(Long itproductId) throws IOException {
        Itproduct itproduct = itproductrepository.findById(itproductId)
                .orElseThrow(() -> new EntityNotFoundException("Produit IT non trouvé"));

        String deliveryNoteFilePath = itproduct.getDeliveryNote().getFile();
        return Files.readAllBytes(Path.of(URI.create(deliveryNoteFilePath)));
    }

    public byte[] getPurchaseOrderFile(Long itproductId) throws IOException {
        Itproduct itproduct = itproductrepository.findById(itproductId)
                .orElseThrow(() -> new EntityNotFoundException("Produit IT non trouvé"));

        String purchaseOrderFilePath = itproduct.getPurchaseOrder().getFile();
        return Files.readAllBytes(Path.of(URI.create(purchaseOrderFilePath)));
    }
    public long getTotalProducts() {
        return itproductrepository.countTotalProducts();
    }

    public long getProductsByRegion(String regionName) {
        return itproductrepository.countProductsByRegion(regionName);
    }

    public long getProductsBySite(String siteName) {
        return itproductrepository.countProductsBySite(siteName);
    }

    public long getProductsByDepartment(String departmentName) {
        return itproductrepository.countProductsByDepartment(departmentName);
    }


}
