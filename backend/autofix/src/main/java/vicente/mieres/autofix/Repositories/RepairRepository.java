package vicente.mieres.autofix.Repositories;

import java.util.List;

import vicente.mieres.autofix.Proyections.AverageTimeProyection;
import vicente.mieres.autofix.Proyections.RepairVehicleTypeProyection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.RepairEntity;

@Repository
public interface RepairRepository extends CrudRepository<RepairEntity, Long>{

    @Query(value = "SELECT RTC.repair_type AS RepairType, COUNT(DISTINCT V.vehicle_type) AS VehicleAmount, SUM(R.total_cost) AS TotalCost " +
            "FROM repair_type_cost RTC " +
            "LEFT JOIN repair R ON RTC.repair_type_cost_id = R.repair_type_cost_id " +
            "LEFT JOIN vehicle_repair VR ON R.repair_id = VR.repair_id " +
            "LEFT JOIN vehicle V ON VR.vehicle_id = V.vehicle_id "+
            "GROUP BY RTC.repair_type "+
            "ORDER BY TotalCost DESC", nativeQuery = true)
    List<RepairVehicleTypeProyection> getRepairVehicleCost();


    @Query(value = "SELECT b.brand_name AS BrandName, " +
                   "AVG(EXTRACT(EPOCH FROM (r.check_out_date_time - r.check_in_date_time))/3600) AS AverageRepairTime " +
                   "FROM repair r " +
                   "JOIN vehicle_repair vr ON r.repair_id = vr.repair_id " +
                   "JOIN vehicle v ON vr.vehicle_id = v.vehicle_id " +
                   "JOIN brand b ON v.brand_id = b.brand_id " +
                   "GROUP BY b.brand_name " +
                   "ORDER BY AverageRepairTime ASC ", nativeQuery = true)
    List<AverageTimeProyection> getAverageRepairTime();
}
