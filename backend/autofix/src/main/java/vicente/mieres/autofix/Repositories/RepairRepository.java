package vicente.mieres.autofix.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.RepairEntity;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long>{

    @Query(value = "SELECT cr.cost_record_id, " +
                   "b.brand_name AS brandName, " +
                   "v.model AS vehicleModel, " +
                   "v.registration AS registration, " +
                   "cr.repair_cost AS repairCost, " +
                   "cr.kilometer_charge AS kilometerCharge, " +
                   "cr.age_charge AS ageCharge, " +
                   "cr.late_charge AS lateCharge, " +
                   "cr.repairs_discount AS repairsDiscount, " +
                   "cr.attention_day_discount AS attentionDayDiscount, " +
                   "cr.bonus_discount AS bonusDiscount, " +
                   "cr.repair_costog AS repairCostOG " +
                   "FROM cost_record cr " +
                   "JOIN repair r ON cr.cost_record_id = r.cost_record_id " +
                   "JOIN vehicle_repair vr ON r.repair_id = vr.repair_id " +
                   "JOIN vehicle v ON vr.vehicle_id = v.vehicle_id " +
                   "JOIN brand b ON v.brand_id = b.brand_id", nativeQuery = true)
    List<Object[]> getCostRecords();

    @Query(value = "SELECT RTC.repair_type AS RepairType, " +
                   "SUM(CASE WHEN V.vehicle_type = 'SEDAN' THEN 1 ELSE 0 END) AS Sedans, " +
                   "SUM(CASE WHEN V.vehicle_type = 'HATCHBACK' THEN 1 ELSE 0 END) AS Hatchbacks, " +
                   "SUM(CASE WHEN V.vehicle_type = 'SUV' THEN 1 ELSE 0 END) AS Suvs, " +
                   "SUM(CASE WHEN V.vehicle_type = 'PICKUP' THEN 1 ELSE 0 END) AS Pickups, " +
                   "SUM(CASE WHEN V.vehicle_type = 'VAN' THEN 1 ELSE 0 END) AS Vans, " +
                   "COALESCE(SUM(R.total_cost), 0) AS TotalCost " +
                   "FROM repair_type_cost RTC " +
                   "LEFT JOIN repair R ON R.repair_type_cost_id = RTC.repair_type_cost_id " +
                   "LEFT JOIN vehicle_repair VR ON R.repair_id = VR.repair_id " +
                   "LEFT JOIN vehicle V ON VR.vehicle_id = V.vehicle_id " +
                   "GROUP BY RTC.repair_type " +
                   "ORDER BY TotalCost DESC", nativeQuery = true)
List<Object[]> getRepairTypeCost();

@Query(value = "SELECT b.brand_name AS BrandName, " +
"AVG(EXTRACT(EPOCH FROM (r.check_out_date_time - r.check_in_date_time))/3600) AS AverageRepairTime " +
"FROM repair r " +
"JOIN vehicle_repair vr ON r.repair_id = vr.repair_id " +
"JOIN vehicle v ON vr.vehicle_id = v.vehicle_id " +
"JOIN brand b ON v.brand_id = b.brand_id " +
"GROUP BY b.brand_name " +
"ORDER BY AverageRepairTime ASC", nativeQuery = true)
List<Object[]> getAverageRepairTime();

@Query(value = "SELECT RTC.repair_type AS RepairType, " +
"SUM(CASE WHEN V.motor_type = 'GASOLINE' THEN 1 ELSE 0 END) AS Gasoline, " +
"SUM(CASE WHEN V.motor_type = 'DIESEL' THEN 1 ELSE 0 END) AS Diesel, " +
"SUM(CASE WHEN V.motor_type = 'HYBRID' THEN 1 ELSE 0 END) AS Hybrid, " +
"SUM(CASE WHEN V.motor_type = 'ELECTRIC' THEN 1 ELSE 0 END) AS Electric, " +
"COALESCE(SUM(R.total_cost), 0) AS TotalCost " +
"FROM repair_type_cost RTC " +
"LEFT JOIN repair R ON R.repair_type_cost_id = RTC.repair_type_cost_id " +
"LEFT JOIN vehicle_repair VR ON VR.repair_id = R.repair_id " +
"LEFT JOIN vehicle V ON VR.vehicle_id = V.vehicle_id " +
"GROUP BY RTC.repair_type " +
"ORDER BY TotalCost DESC", nativeQuery = true)
List<Object[]> getRepairMotorCost();


}
