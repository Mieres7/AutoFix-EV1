package vicente.mieres.autofix.Repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Entities.CostRecordEntity;
import vicente.mieres.autofix.Entities.RepairEntity;
import vicente.mieres.autofix.Entities.RepairTypeCostEntity;
import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Entities.VehicleRepairEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RepairRepositoryTest {
    
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private RepairRepository repairRepository;
    // @Autowired
    // private RepairService repairService;
    @Autowired
    private CostRecordRepository costRecordRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired 
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleRepairRepository vehicleRepairRepository;
    

        @Test
        public void whenGetCostRecords_thenCostRecordsIsCorrect() {
        
            // BrandEntity brand = new BrandEntity(99, "Toyota", true, 100, 300, "111");  
            // brand.setBrandName("TOYOTA");
            // brand.setBrandId(null);
            // entityManager.persist(brand);
            // entityManager.flush();

            VehicleEntity vehicle = new VehicleEntity();
            vehicle.setBrand_id(99L);
            vehicle.setModel("Corolla");
            vehicle.setRegistration("ABCD12");
            entityManager.merge(vehicle);
            // entityManager.flush();

            RepairEntity repair = new RepairEntity(); 
            repair.setRepairId(99L);
            repair.setCostRecordId(1L);
            entityManager.merge(repair);
            // entityManager.flush();

            VehicleRepairEntity vehicleRepair = new VehicleRepairEntity();
            vehicleRepair.setRepairId(99L);
            vehicleRepair.setVehicleId(99L);
            vehicleRepair.setVehicleRepairId(99L);
            entityManager.merge(vehicleRepair);
            // entityManager.flush();

            CostRecordEntity costRecord = new CostRecordEntity();  
            costRecord.setCostRecordId(99L);
            costRecord.setVehicleId(1L);
            costRecord.setRepairCostOG(120000.0f);
            costRecord.setRepairCost(125000.0f);
            costRecord.setKilometerCharge(3600.0f);
            costRecord.setAgeCharge(10000.0f);
            costRecord.setLateCharge(2000.0f);
            costRecord.setRepairsDiscount(4500.0f);
            costRecord.setAttentionDayDiscount(5000.0f);
            costRecord.setBonusDiscount(12000.0f);
            entityManager.merge(costRecord);
            entityManager.flush();

            List<Object[]> results = repairRepository.getCostRecords();

            assertEquals(0, results.size());
            costRecordRepository.delete(costRecord);
            vehicleRepository.delete(vehicle);
            vehicleRepairRepository.delete(vehicleRepair);
        }

    @Test
    public void whenGetRepairTypeCosts_ThenDataIsCorrect(){

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setBrand_id(1L);
        vehicle.setModel("Corolla");
        vehicle.setRegistration("ABCD12");
        vehicle.setMotorType("GASOLINE");
        vehicle.setVehicleType("SEDAN");
        entityManager.merge(vehicle);
        entityManager.flush();

        RepairEntity repair = new RepairEntity(); 
        repair.setTotalCost(156000.0f);
        repair.setRepairId(1L);
    
        entityManager.merge(repair);
        entityManager.flush();

        VehicleRepairEntity vehicleRepair = new VehicleRepairEntity();
        vehicleRepair.setRepairId(1L);
        vehicleRepair.setVehicleId(1L);
        vehicleRepair.setVehicleRepairId(1L);
        entityManager.merge(vehicleRepair);
        entityManager.flush();

        RepairTypeCostEntity rCostEntity = new RepairTypeCostEntity();
        rCostEntity.setRepairTypeCostId(1L);
        rCostEntity.setRepairType("Breaks Repair");
        entityManager.merge(rCostEntity);

        entityManager.flush();
        

        List<Object[]> repairVehicleTypeProyections = repairRepository.getRepairTypeCost();

        assertEquals(11, repairVehicleTypeProyections.size());
       
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

        List<Object[]> averageTimeProyections = repairRepository.getAverageRepairTime();

            assertEquals(1, averageTimeProyections.size());
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

        List<Object[]> repairVehicleMotorProyections = repairRepository.getRepairMotorCost();

        assertEquals(11, repairVehicleMotorProyections.size());
        
    }

}
