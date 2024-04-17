package vicente.mieres.autofix.Services;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import vicente.mieres.autofix.DTO.CreateRepair;
import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Entities.CostRecordEntity;
import vicente.mieres.autofix.Entities.RepairEntity;
import vicente.mieres.autofix.Entities.RepairTypeCostEntity;
import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Entities.VehicleRepairEntity;
import vicente.mieres.autofix.Proyections.AverageTimeProyection;
import vicente.mieres.autofix.Proyections.CostRecordProyection;
import vicente.mieres.autofix.Proyections.RepairVehicleMotorProyection;
import vicente.mieres.autofix.Proyections.RepairVehicleTypeProyection;
import vicente.mieres.autofix.Repositories.RepairRepository;
import vicente.mieres.autofix.Repositories.VehicleRepairRepository;
import vicente.mieres.autofix.Repositories.VehicleRepository;

@SpringBootTest
@Transactional
public class RepairServiceTest {

    @Autowired
    private RepairService repairService;
    @MockBean
    private RepairRepository repairRepository;
    @MockBean
    private VehicleRepository vehicleRepository;
    @MockBean
    private VehicleRepairRepository vehicleRepairRepository;
    @MockBean
    private VehicleRepairService vehicleRepairService;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void whenSaveRepair_ThenSavedRepairIsCorrectV1() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setVehicleId(1L);
        vehicle.setMileage(4000);
        vehicle.setRepairs(1);
        vehicle.setManufactureYear("2024");

        CreateRepair createRepair = new CreateRepair();
        createRepair.setBonus(true);
        createRepair.setRepairType(1);
        createRepair.setVehicleId(1L);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        RepairEntity repair = repairService.saveRepair(createRepair);
        RepairEntity savedRepair = repairRepository.save(repair);

        assertThat(savedRepair.isBonus()).isTrue();
    }
    @Test
    public void whenSaveRepair_ThenSavedRepairIsCorrectV2() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setVehicleId(2L);
        vehicle.setMileage(6000);
        vehicle.setRepairs(3);
        vehicle.setManufactureYear("2018");

        CreateRepair createRepair = new CreateRepair();
        createRepair.setBonus(true);
        createRepair.setRepairType(1);
        createRepair.setVehicleId(2L);

        when(vehicleRepository.findById(2L)).thenReturn(Optional.of(vehicle));

        RepairEntity repair = repairService.saveRepair(createRepair);
        RepairEntity savedRepair = repairRepository.save(repair);

        assertThat(savedRepair.isBonus()).isTrue();
    }

    @Test
    public void whenSaveRepair_ThenSavedRepairIsCorrectV3() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setVehicleId(1L);
        vehicle.setMileage(13000);
        vehicle.setRepairs(7);
        vehicle.setManufactureYear("2012");

        CreateRepair createRepair = new CreateRepair();
        createRepair.setBonus(true);
        createRepair.setRepairType(1);
        createRepair.setVehicleId(3L);

        when(vehicleRepository.findById(3L)).thenReturn(Optional.of(vehicle));

        RepairEntity repair = repairService.saveRepair(createRepair);
        RepairEntity savedRepair = repairRepository.save(repair);

        assertThat(savedRepair.isBonus()).isTrue();
    }
    
    @Test
    public void whenSaveRepair_ThenSavedRepairIsCorrectV4() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setVehicleId(4L);
        vehicle.setMileage(26000);
        vehicle.setRepairs(10);
        vehicle.setManufactureYear("2000");

        CreateRepair createRepair = new CreateRepair();
        createRepair.setBonus(true);
        createRepair.setRepairType(1);
        createRepair.setVehicleId(4L);

        when(vehicleRepository.findById(4L)).thenReturn(Optional.of(vehicle));

        RepairEntity repair = repairService.saveRepair(createRepair);
        RepairEntity savedRepair = repairRepository.save(repair);

        assertThat(savedRepair.isBonus()).isTrue();
    }

    @Test 
    public void whenGetRepair_ThenRepairIsCorrect(){

        RepairEntity repair = new RepairEntity();
        repair.setRepairId(1L);
        
        when(repairRepository.findById(1L)).thenReturn(Optional.of(repair));
        RepairEntity repairFoud = repairService.getRepair(1L);

        assertThat(repairFoud.getRepairId()).isEqualTo(repair.getRepairId());
    }

    @Test
    public void whenPutRepair_ThenRepairIsUpdated() {
        
        RepairEntity existingRepair = new RepairEntity();
        existingRepair.setRepairId(1L);
        existingRepair.setBonus(true);

        RepairEntity updatedRepair = new RepairEntity();
        updatedRepair.setRepairId(1L);
        updatedRepair.setBonus(false);

        when(repairRepository.findById(1L)).thenReturn(Optional.of(existingRepair));
        when(repairRepository.save(updatedRepair)).thenReturn(updatedRepair);

        RepairEntity result = repairService.updateRepair(updatedRepair);

        assertThat(result.getRepairId()).isEqualTo(updatedRepair.getRepairId());
        assertThat(result.isBonus()).isEqualTo(updatedRepair.isBonus());
    }

    @Test 
    public void whenGetTotalCost_thenCostIsCorrect(){

        RepairEntity repair = new RepairEntity();
        repair.setRepairId(1L);
        repair.setAgeChargeId(1L);
        repair.setRepairDiscount(1L);
        repair.setCostRecordId(1L);
        repair.setKilometerChargeId(1L);
        repair.setRepairTypeCostId(1L);
        repair.setBonus(true);

        LocalDateTime chekIn = LocalDateTime.of(2024, 4, 15, 10, 0, 0);
        LocalDateTime chekOut = LocalDateTime.of(2024, 4, 16, 10, 0, 0);
        LocalDateTime costumerTime = LocalDateTime.of(2024, 4, 19, 10, 0, 0);
        repair.setCheckInDateTime(chekIn);
        repair.setCheckOutDateTime(chekOut);
        repair.setCostumerDateTime(costumerTime);

        when(repairRepository.findById(1L)).thenReturn(Optional.of(repair));

        VehicleRepairEntity vr = new VehicleRepairEntity();
        vr.setRepairId(1L);
        vr.setVehicleId(1L);
        vr.setVehicleRepairId(1L);

        when(vehicleRepairService.getByRepairId(1L)).thenReturn(vr);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setVehicleId(1L);
        vehicle.setMotorType("GASOLINE");
        vehicle.setVehicleType("SEDAN");
        vehicle.setBrand_id(1L);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        float totalCost = repairService.getTotalCost(1L);

        assertThat(totalCost).isEqualTo(136850.0f);

    }

    @Test 
    public void whenGetTotalCost_thenCostIsCorrectv2(){

        RepairEntity repair = new RepairEntity();
        repair.setRepairId(1L);
        repair.setAgeChargeId(1L);
        repair.setRepairDiscount(1L);
        repair.setCostRecordId(1L);
        repair.setKilometerChargeId(1L);
        repair.setRepairTypeCostId(1L);
        repair.setBonus(false);

        LocalDateTime chekIn = LocalDateTime.of(2024, 4, 12, 10, 0, 0);
        LocalDateTime chekOut = LocalDateTime.of(2024, 4, 16, 10, 0, 0);
        LocalDateTime costumerTime = LocalDateTime.of(2024, 4, 19, 10, 0, 0);
        repair.setCheckInDateTime(chekIn);
        repair.setCheckOutDateTime(chekOut);
        repair.setCostumerDateTime(costumerTime);

        when(repairRepository.findById(1L)).thenReturn(Optional.of(repair));

        VehicleRepairEntity vr = new VehicleRepairEntity();
        vr.setRepairId(1L);
        vr.setVehicleId(1L);
        vr.setVehicleRepairId(1L);

        when(vehicleRepairService.getByRepairId(1L)).thenReturn(vr);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setVehicleId(1L);
        vehicle.setMotorType("GASOLINE");
        vehicle.setVehicleType("SEDAN");
        vehicle.setBrand_id(1L);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        float totalCost = repairService.getTotalCost(1L);

        assertThat(totalCost).isEqualTo(157080.0f);

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

        List<RepairVehicleTypeProyection> repairVehicleTypeProyections = repairService.getRepairTypeCost();

        assertThat(repairVehicleTypeProyections.get(0).getRepairType()).isEqualTo("Breaks Repair");
    }

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

        List<CostRecordProyection> results = repairService.getCostRecords();

        assertThat(results.get(0).getBrandName()).isEqualTo("TOYOTA");
        assertThat(results.get(0).getVehicleModel()).isEqualTo("Corolla");
        assertThat(results.get(0).getRegistration()).isEqualTo("ABCD12");
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

        List<AverageTimeProyection> averageTimeProyections = repairService.getAverageRepairTime();

        assertThat(averageTimeProyections).isNotEmpty();
        assertThat(averageTimeProyections.get(0).getAverageRepairTime()).isEqualTo(168.0); 
        assertThat(averageTimeProyections.get(0).getBrandName()).isEqualTo("TOYOTA");
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

        List<RepairVehicleMotorProyection> repairVehicleMotorProyections = repairService.getRepairMotorCost();

        assertThat(repairVehicleMotorProyections).isNotEmpty();
        assertThat(repairVehicleMotorProyections.get(0).getRepairType()).isEqualTo("Breaks Repair");
    }

}
