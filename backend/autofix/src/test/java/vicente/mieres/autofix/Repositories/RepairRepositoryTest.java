package vicente.mieres.autofix.Repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Entities.CostRecordEntity;
import vicente.mieres.autofix.Entities.RepairEntity;
import vicente.mieres.autofix.Entities.RepairTypeCostEntity;
import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Entities.VehicleRepairEntity;


import java.time.LocalDateTime;

@DataJpaTest
@Transactional
public class RepairRepositoryTest {
    
    @Autowired
    private EntityManager entityManager;
    // @Autowired
    // private RepairRepository repairRepository;
    // @Autowired
    // private RepairService repairService;
    // @Autowired
    // private CostRecordRepository costRecordRepository;

    @Test
    public void whenGetCostRecords_thenCostRecordsIsCorrect() {
    
        BrandEntity brand = new BrandEntity();  
        brand.setBrandName("TOYOTA");
        brand.setBrandId(1L);
        entityManager.merge(brand);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setBrand_id(1L);
        vehicle.setModel("Corolla");
        vehicle.setRegistration("ABCD12");
        entityManager.merge(vehicle);

        RepairEntity repair = new RepairEntity(); 
        repair.setCostRecordId(1L);
        repair.setRepairId(1L);
        entityManager.merge(repair);

        VehicleRepairEntity vehicleRepair = new VehicleRepairEntity();
        vehicleRepair.setRepairId(1L);
        vehicleRepair.setVehicleId(1L);
        vehicleRepair.setVehicleRepairId(1L);
        entityManager.merge(vehicleRepair);

        CostRecordEntity costRecord = new CostRecordEntity();  
        costRecord.setCostRecordId(1L);
        costRecord.setAgeCharge(10000);
        costRecord.setAttentionDayDiscount(5000);
        costRecord.setKilometerCharge(3600);
        costRecord.setLateCharge(2000);
        costRecord.setRepairCost(125000);
        costRecord.setRepairCostOG(120000);
        costRecord.setRepairsDiscount(4500);
        costRecord.setVehicleId(1L);
        costRecord.setBonusDiscount(12000);
        entityManager.merge(costRecord);

        entityManager.flush();

        // List<CostRecordProyection> results = repairRepository.getCostRecords();
        // System.out.println(results);

        // assertThat(results.get(0).getBrandName()).isEqualTo("TOYOTA");
        // assertThat(results.get(0).getVehicleModel()).isEqualTo("Corolla");
        // assertThat(results.get(0).getRegistration()).isEqualTo("ABCD12");
    }

    @Test
    public void whenGetRepairTypeCosts_ThenDataIsCorrect(){

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setBrand_id(1L);
        vehicle.setModel("Corolla");
        vehicle.setRegistration("ABCD12");
        vehicle.setMotorType("GASOLINE");
        entityManager.merge(vehicle);

        RepairEntity repair = new RepairEntity(); 
        repair.setTotalCost(156000.0f);
        repair.setRepairId(1L);
    
        entityManager.merge(repair);

        VehicleRepairEntity vehicleRepair = new VehicleRepairEntity();
        vehicleRepair.setRepairId(1L);
        vehicleRepair.setVehicleId(1L);
        vehicleRepair.setVehicleRepairId(1L);
        entityManager.merge(vehicleRepair);

        RepairTypeCostEntity rCostEntity = new RepairTypeCostEntity();
        rCostEntity.setRepairTypeCostId(1L);
        rCostEntity.setRepairType("Breaks Repair");
        entityManager.merge(rCostEntity);

        entityManager.flush();

        // List<RepairVehicleTypeProyection> repairVehicleTypeProyections = repairRepository.getRepairTypeCost();

        // assertThat(repairVehicleTypeProyections).isNotEmpty();
        // assertThat(repairVehicleTypeProyections.get(0).getRepairType()).isEqualTo("Breaks Repair");
    }

    @Test
    public void whenGetAverageTime_thenAverageTimeIsCorrect(){

        LocalDateTime checkIn = LocalDateTime.of(2024, 4, 12, 12, 0, 0);
        LocalDateTime checkOut = LocalDateTime.of(2024, 4, 19, 12, 0, 0);
        
        RepairEntity repair = new RepairEntity(); 
        repair.setRepairId(1L);
        repair.setCheckInDateTime(checkIn);
        repair.setCheckOutDateTime(checkOut);
        entityManager.merge(repair);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setBrand_id(1L);
        vehicle.setModel("Corolla");
        vehicle.setRegistration("ABCD12");
        entityManager.merge(vehicle);

        BrandEntity brand = new BrandEntity();  
        brand.setBrandName("TOYOTA");
        brand.setBrandId(1L);
        entityManager.merge(brand);

        VehicleRepairEntity vehicleRepair = new VehicleRepairEntity();
        vehicleRepair.setRepairId(1L);
        vehicleRepair.setVehicleId(1L);
        vehicleRepair.setVehicleRepairId(1L);
        entityManager.merge(vehicleRepair);

        entityManager.flush();

        // List<AverageTimeProyection> averageTimeProyections = repairRepository.getAverageRepairTime();

        // assertThat(averageTimeProyections).isNotEmpty();
        // assertThat(averageTimeProyections.get(0).getAverageRepairTime()).isEqualTo(168.0); 
        // assertThat(averageTimeProyections.get(0).getBrandName()).isEqualTo("TOYOTA");
    }   

    @Test
    public void whenGetRepairMotorCost_thenMotorCostIsCorrect(){

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setBrand_id(1L);
        vehicle.setModel("Corolla");
        vehicle.setRegistration("ABCD12");
        vehicle.setMotorType("GASOLINE");
        entityManager.merge(vehicle);

        RepairEntity repair = new RepairEntity(); 
        repair.setTotalCost(156000.0f);
        repair.setRepairId(1L);
    
        entityManager.merge(repair);

        VehicleRepairEntity vehicleRepair = new VehicleRepairEntity();
        vehicleRepair.setRepairId(1L);
        vehicleRepair.setVehicleId(1L);
        vehicleRepair.setVehicleRepairId(1L);
        entityManager.merge(vehicleRepair);

        RepairTypeCostEntity rCostEntity = new RepairTypeCostEntity();
        rCostEntity.setRepairTypeCostId(1L);
        rCostEntity.setRepairType("Breaks Repair");
        entityManager.merge(rCostEntity);

        entityManager.flush();

        // List<RepairVehicleMotorProyection> repairVehicleMotorProyections = repairRepository.getRepairMotorCost();

        // assertThat(repairVehicleMotorProyections).isNotEmpty();
        // assertThat(repairVehicleMotorProyections.get(0).getRepairType()).isEqualTo("Breaks Repair");
    }

}
