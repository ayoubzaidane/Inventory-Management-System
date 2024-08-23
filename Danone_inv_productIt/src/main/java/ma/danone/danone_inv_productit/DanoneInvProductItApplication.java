package ma.danone.danone_inv_productit;

import ma.danone.danone_inv_productit.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ma.danone.danone_inv_productit.Entities.*;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class DanoneInvProductItApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanoneInvProductItApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(Emprepository emprepository
    , Itproductrepository itproductrepository, Departementrepository departementrepository
    , Siterepository siterepository, Regionrepository regionrepository
    ,AccessoryRepository accessoryRepository,Supplierrepository supplierrepository
    ,PurshaseOrderrepository purshaseOrderrepository , DeliveryNoterepository deliveryNoterepository,
    Assignemerepository assignemerepository) {
        return args ->
        {
            Region region = Region.builder().name("kakakaka").build();
            region = regionrepository.save(region);

            Supplier supplier = Supplier.builder()
                    .firstname("Tech Supplies Co.")
                    .Phonenumber("123-456-7890")
                    .emailAdress("contact@techsupplies.com")
                    .build();

            supplier = supplierrepository.save(supplier);

            PurshaseOrder purshaseOrder = PurshaseOrder.builder()
                    .orderNumber("A123231")
                    .orderDate(new Date())
                    .totalPrice(1234)
                    .supplier(supplier)
                    .build();
            purshaseOrder = purshaseOrderrepository.save(purshaseOrder);

            DeliveryNote deliveryNote = DeliveryNote.builder()
                    .deliveryNoteNumber("1WEFW12")
                    .deliveryDate(new Date())
                    .supplier(supplier)
                    .build();

            deliveryNote = deliveryNoterepository.save(deliveryNote);


            Site site = Site.builder().name("aaaaa").region(region).build();
            site = siterepository.save(site);
            // Create a department with an address
            Departement department = Departement.builder()
                    .name("IT Department")
                    .adress("123 Tech Lane, Silicon Valley, CA")
                    .site(site)
                    .build();
            department = departementrepository.save(department);

            EMP emp = EMP.builder()
                    .firstname("John")
                    .lastname("Doe")
                    .email("johndoe@example.com")
                    .phone("1234567890")
                    .matricule("JD123")
                    .departement(department)
                    .build();


            Accessory accessory1 = Accessory.builder()
                    .type("Mouse")
                    .warranty(34)
                    .state(State.DON)
                    .purshaseDate(new Date())
                    .departement(department)
                    .supplier(supplier)
                    .purchaseOrder(purshaseOrder)
                    .deliveryNote(deliveryNote)
                    .build();
            Accessory accessory2 = Accessory.builder()
                    .type("keybord")
                    .warranty(34)
                    .state(State.DON)
                    .purshaseDate(new Date())
                    .departement(department)
                    .supplier(supplier)
                    .purchaseOrder(purshaseOrder)
                    .deliveryNote(deliveryNote)
                    .build();

            accessory1 = accessoryRepository.save(accessory1);
            accessory2 = accessoryRepository.save(accessory2);

            List<Accessory> accessories = new ArrayList<>();
            accessories.add(accessory1);
            accessories.add(accessory2);
            //associer les accessoire aux emps
            emp.setAccessory(accessories);

            accessory1.setDepartement(department);
            accessory2.setDepartement(department);


// Create ITProducts
            Itproduct product1 = Itproduct.builder()
                    .type("PC")
                    .characteristics("SSD")
                    .serienumber("SN10001")
                    .quantityStock(10)
                    .garantie(24)
                    .purchaseDate(new Date())
                    .model("ModelX")
                    .state(State.IN_USE)
                    .departement(department)
                    .supplier(supplier)
                    .purchaseOrder(purshaseOrder)
                    .deliveryNote(deliveryNote)
                    .build();

            Itproduct product2 = Itproduct.builder()
                    .type("Laptop")
                    .characteristics("HDD")
                    .serienumber("SN10002")
                    .quantityStock(5)
                    .garantie(12)
                    .purchaseDate(new Date())
                    .model("ModelY")
                    .state(State.IN_MAINTENANCE)
                    .departement(department)
                    .supplier(supplier)
                    .purchaseOrder(purshaseOrder)
                    .deliveryNote(deliveryNote)
                    .build();

// Save products
            product1 = itproductrepository.save(product1);
            product2 = itproductrepository.save(product2);

// Associate products with employee
            List<Itproduct> products = new ArrayList<>();
            products.add(product1);
            products.add(product2);
            emp.setItproducts(products);

            // Associate department with products and employee

            emp.setDepartement(department);
            product1.setDepartement(department);
            product2.setDepartement(department);

// Save employee
            emp = emprepository.save(emp);

// Update products to reflect the relationship
            if (product1.getEmployees() != null) {
                product1.getEmployees().add(emp);
            }// Assuming the getter initializes the list if null
            if (product2.getEmployees() != null) {
                product2.getEmployees().add(emp);
            }// Assuming the same
            itproductrepository.save(product1);
            itproductrepository.save(product2);

            if (accessory1.getEmployees() != null) {
                accessory1.getEmployees().add(emp);
            }

            if (accessory2.getEmployees() != null) {
                accessory2.getEmployees().add(emp);
            }
            accessoryRepository.save(accessory1);
            accessoryRepository.save(accessory2);

            // Créer et sauvegarder les assignements pour les produits IT et les accessoires
            Assignement assignment1 = Assignement.builder()
                    .startDate(LocalDate.now())
                    .endDate(LocalDate.now().plusYears(1))
                    .emp(emp)
                    .itproduct(product1)
                    .build();

            Assignement assignment2 = Assignement.builder()
                    .startDate(LocalDate.now())
                    .endDate(LocalDate.now().plusYears(1))
                    .emp(emp)
                    .itproduct(product2)
                    .build();

            Assignement assignment3 = Assignement.builder()
                    .startDate(LocalDate.now())
                    .endDate(LocalDate.now().plusYears(1))
                    .emp(emp)
                    .accessory(accessory1)
                    .build();

            Assignement assignment4 = Assignement.builder()
                    .startDate(LocalDate.now())
                    .endDate(LocalDate.now().plusYears(1))
                    .emp(emp)
                    .accessory(accessory2)
                    .build();

            // Sauvegarder les assignements
            assignemerepository.save(assignment1);
            assignemerepository.save(assignment2);
            assignemerepository.save(assignment3);
            assignemerepository.save(assignment4);
            // Sauvegarder les accessoires et les produits IT après avoir ajouté les employés
            accessoryRepository.save(accessory1);
            accessoryRepository.save(accessory2);
            itproductrepository.save(product1);
            itproductrepository.save(product2);


        };

    }}
