package ma.danone.danone_inv_productit.repository;

import ma.danone.danone_inv_productit.Entities.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccessoryRepository extends JpaRepository<Accessory,Long>
{
    List<Accessory> findByType(String type);
    List<Accessory> findByDepartement_Name(String name);

    List<Accessory> findByDepartement_Site_Name(String sitename);

    List<Accessory> findByDepartement_Site_Region_Name(String regionname);

    List<Accessory> findBySupplier_EmailAdress(String emailadresse);

    @Query("SELECT COUNT(a) FROM Accessory a")
    long countTotalAccessories();

    @Query("SELECT COUNT(a) FROM Accessory a WHERE a.departement.site.region.name = :regionName")
    long countAccessoriesByRegion(@Param("regionName") String regionName);

    @Query("SELECT COUNT(a) FROM Accessory a WHERE a.departement.site.name = :siteName")
    long countAccessoriesBySite(@Param("siteName") String siteName);

    @Query("SELECT COUNT(a) FROM Accessory a WHERE a.departement.name = :departmentName")
    long countAccessoriesByDepartment(@Param("departmentName") String departmentName);

}
