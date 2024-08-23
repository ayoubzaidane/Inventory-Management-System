package ma.danone.danone_inv_productit.repository;

import ma.danone.danone_inv_productit.Entities.Itproduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Itproductrepository extends JpaRepository<Itproduct,Long>
{
    List<Itproduct> findByType(String type);
    Optional<Itproduct> findBySerienumber(String Serienumber);
    List<Itproduct> findByModel(String model);
    List<Itproduct> findByDepartement_Name(String name);
    List<Itproduct> findByDepartement_Site_Name(String sitename);
    List<Itproduct> findByDepartement_Site_Region_Name(String regionname);
    List<Itproduct> findBySupplier_EmailAdress(String Emailadresse);

    @Query("SELECT COUNT(p) FROM Itproduct p")
    long countTotalProducts();

    @Query("SELECT COUNT(p) FROM Itproduct p WHERE p.departement.site.region.name = :regionName")
    long countProductsByRegion(@Param("regionName") String regionName);

    @Query("SELECT COUNT(p) FROM Itproduct p WHERE p.departement.site.name = :siteName")
    long countProductsBySite(@Param("siteName") String siteName);

    @Query("SELECT COUNT(p) FROM Itproduct p WHERE p.departement.name = :departmentName")
    long countProductsByDepartment(@Param("departmentName") String departmentName);

}
