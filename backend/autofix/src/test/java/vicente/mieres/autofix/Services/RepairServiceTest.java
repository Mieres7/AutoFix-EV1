package vicente.mieres.autofix.Services;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;
import vicente.mieres.autofix.DTO.CreateRepair;
import vicente.mieres.autofix.Entities.AgeChargeEntity;
import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Entities.CostRecordEntity;
import vicente.mieres.autofix.Entities.KilometerChargeEntity;
import vicente.mieres.autofix.Entities.RepairDiscountEntity;
import vicente.mieres.autofix.Entities.RepairEntity;
import vicente.mieres.autofix.Entities.RepairTypeCostEntity;
import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Entities.VehicleRepairEntity;
import vicente.mieres.autofix.Repositories.CostRecordRepository;
import vicente.mieres.autofix.Repositories.RepairRepository;
import vicente.mieres.autofix.Repositories.VehicleRepairRepository;
import vicente.mieres.autofix.Repositories.VehicleRepository;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
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
    @MockBean
    private CostRecordRepository costRecordRepository;
    @MockBean 
    private CostRecordService costRecordService;
    @MockBean
    private RepairTypeCostService repairTypeCostService;
    @MockBean
    private RepairDiscountService repairDiscountService;
    @MockBean
    private KilometerChargeService kilometerChargeService;
    @MockBean
    private AgeChargeService ageChargeService;
    @MockBean
    private BrandService brandService;
    
    
    @Test
    public void whenSaveRepair_ThenSavedRepairIsCorrectV1() {
    // Set up
    VehicleEntity vehicle = new VehicleEntity();
    vehicle.setVehicleId(1L);
    vehicle.setMileage(4000);
    vehicle.setRepairs(1);
    vehicle.setManufactureYear("2024");

    CreateRepair createRepair = new CreateRepair();
    createRepair.setBonus(true);
    createRepair.setRepairType(1);
    createRepair.setVehicleId(1L);

    RepairEntity expectedRepair = new RepairEntity();
    expectedRepair.setRepairId(1L);
    expectedRepair.setRepairTypeCostId(1L);
    expectedRepair.setBonus(true);

    CostRecordEntity expectedCostRecord = new CostRecordEntity();
    expectedCostRecord.setCostRecordId(1L);

    // Mocking
    when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
    when(repairRepository.save(any(RepairEntity.class))).thenReturn(expectedRepair);
    when(costRecordRepository.save(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);
    when(costRecordService.saveCostRecord(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);

    // Execution
    RepairEntity savedRepair = repairService.saveRepair(createRepair);

    // Verification
    assertThat(savedRepair.isBonus()).isTrue();
    verify(repairRepository, times(2)).save(any(RepairEntity.class));  // Verify that the repair was saved exactly twice
    verify(costRecordService).saveCostRecord(any(CostRecordEntity.class));  // Verify cost record was saved
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

    
    RepairEntity expectedRepair = new RepairEntity();
    expectedRepair.setRepairId(1L);
    expectedRepair.setRepairTypeCostId(1L);
    expectedRepair.setBonus(true);
    
    CostRecordEntity expectedCostRecord = new CostRecordEntity();
    expectedCostRecord.setCostRecordId(1L);
    
    when(vehicleRepository.findById(2L)).thenReturn(Optional.of(vehicle));
    when(repairRepository.save(any(RepairEntity.class))).thenReturn(expectedRepair);
    when(costRecordRepository.save(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);
    when(costRecordService.saveCostRecord(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);
    
    RepairEntity savedRepair = repairService.saveRepair(createRepair);

    assertThat(savedRepair.isBonus()).isTrue();
    verify(repairRepository, times(2)).save(any(RepairEntity.class));  // Verify that the repair was saved exactly twice
    verify(costRecordService).saveCostRecord(any(CostRecordEntity.class));
}


@Test
public void whenSaveRepair_ThenSavedRepairIsCorrectV3() {
    VehicleEntity vehicle = new VehicleEntity();
    vehicle.setVehicleId(3L);  // Use the correct ID from your input
    vehicle.setMileage(13000);
    vehicle.setRepairs(7);
    vehicle.setManufactureYear("2012");

    CreateRepair createRepair = new CreateRepair();
    createRepair.setBonus(true);
    createRepair.setRepairType(1);
    createRepair.setVehicleId(3L);  // This should be aligned with `vehicle.setVehicleId(1L);`

    
    RepairEntity expectedRepair = new RepairEntity();
    expectedRepair.setRepairId(1L);
    expectedRepair.setRepairTypeCostId(1L);
    expectedRepair.setBonus(true);
    
    
    CostRecordEntity expectedCostRecord = new CostRecordEntity();
    expectedCostRecord.setCostRecordId(1L);
    
    when(vehicleRepository.findById(3L)).thenReturn(Optional.of(vehicle));
    when(repairRepository.save(any(RepairEntity.class))).thenReturn(expectedRepair);
    when(costRecordRepository.save(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);
    when(costRecordService.saveCostRecord(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);
    
    RepairEntity savedRepair = repairService.saveRepair(createRepair);

    assertThat(savedRepair.isBonus()).isTrue();
    verify(repairRepository, times(2)).save(any(RepairEntity.class));  // Verify that the repair was saved exactly twice
    verify(costRecordService).saveCostRecord(any(CostRecordEntity.class));

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


    RepairEntity expectedRepair = new RepairEntity();
    expectedRepair.setRepairId(1L);
    expectedRepair.setRepairTypeCostId(1L);
    expectedRepair.setBonus(true);


    CostRecordEntity expectedCostRecord = new CostRecordEntity();
    expectedCostRecord.setCostRecordId(1L);

    when(vehicleRepository.findById(4L)).thenReturn(Optional.of(vehicle));
    when(repairRepository.save(any(RepairEntity.class))).thenReturn(expectedRepair);
    when(costRecordRepository.save(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);
    when(costRecordService.saveCostRecord(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);

    RepairEntity savedRepair = repairService.saveRepair(createRepair);

    assertThat(savedRepair.isBonus()).isTrue();
    verify(repairRepository, times(2)).save(any(RepairEntity.class));  // Verify that the repair was saved exactly twice
    verify(costRecordService).saveCostRecord(any(CostRecordEntity.class));

}


@Test
public void whenSaveRepair_ThenSavedRepairIsCorrectV5() {
    VehicleEntity vehicle = new VehicleEntity();
    vehicle.setVehicleId(4L);
    vehicle.setMileage(45000);
    vehicle.setRepairs(10);
    vehicle.setManufactureYear("2000");

    CreateRepair createRepair = new CreateRepair();
    createRepair.setBonus(true);
    createRepair.setRepairType(1);
    createRepair.setVehicleId(4L);


    RepairEntity expectedRepair = new RepairEntity();
    expectedRepair.setRepairId(1L);
    expectedRepair.setRepairTypeCostId(1L);
    expectedRepair.setBonus(true);


    CostRecordEntity expectedCostRecord = new CostRecordEntity();
    expectedCostRecord.setCostRecordId(1L);

    when(vehicleRepository.findById(4L)).thenReturn(Optional.of(vehicle));
    when(repairRepository.save(any(RepairEntity.class))).thenReturn(expectedRepair);
    when(costRecordRepository.save(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);
    when(costRecordService.saveCostRecord(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);

    RepairEntity savedRepair = repairService.saveRepair(createRepair);

    assertThat(savedRepair.isBonus()).isTrue();
    verify(repairRepository, times(2)).save(any(RepairEntity.class));  // Verify that the repair was saved exactly twice
    verify(costRecordService).saveCostRecord(any(CostRecordEntity.class));

}


@Test
public void whenSaveRepair_ThenSavedRepairIsCorrectV6() {
    VehicleEntity vehicle = new VehicleEntity();
    vehicle.setVehicleId(4L);
    vehicle.setMileage(-10);
    vehicle.setRepairs(-4);
    vehicle.setManufactureYear("2030");

    CreateRepair createRepair = new CreateRepair();
    createRepair.setBonus(true);
    createRepair.setRepairType(1);
    createRepair.setVehicleId(4L);


    RepairEntity expectedRepair = new RepairEntity();
    expectedRepair.setRepairId(1L);
    expectedRepair.setRepairTypeCostId(1L);
    expectedRepair.setBonus(true);

    CostRecordEntity expectedCostRecord = new CostRecordEntity();
    expectedCostRecord.setCostRecordId(1L);

    when(vehicleRepository.findById(4L)).thenReturn(Optional.of(vehicle));
    when(repairRepository.save(any(RepairEntity.class))).thenReturn(expectedRepair);
    when(costRecordRepository.save(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);
    when(costRecordService.saveCostRecord(any(CostRecordEntity.class))).thenReturn(expectedCostRecord);

    RepairEntity savedRepair = repairService.saveRepair(createRepair);

    assertThat(savedRepair.isBonus()).isTrue();
    verify(repairRepository, times(2)).save(any(RepairEntity.class));  // Verify that the repair was saved exactly twice
    verify(costRecordService).saveCostRecord(any(CostRecordEntity.class));

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
        existingRepair.setTotalCost(5000.00f);

        // Set DateTime attributes with specified hour, minute, and second
        existingRepair.setCheckInDateTime(LocalDateTime.of(2024, 4, 15, 14, 30, 45));
        existingRepair.setCheckOutDateTime(LocalDateTime.of(2024, 4, 17, 14, 30, 45)); // 2 days later
        existingRepair.setCostumerDateTime(LocalDateTime.of(2024, 4, 19, 14, 30, 45)); // 2 days later from checkOut

        // Set boolean flag
        existingRepair.setBonus(true);

        // Set related IDs
        existingRepair.setRepairTypeCostId(1L);
        existingRepair.setKilometerChargeId(1L);
        existingRepair.setAgeChargeId(1L);
        existingRepair.setRepairDiscount(1L);

        // Set other IDs
        existingRepair.setCostRecordId(1L);

        RepairEntity updatedRepair = new RepairEntity();
        updatedRepair.setRepairId(1L);
        updatedRepair.setTotalCost(-5000.00f);

        // Set DateTime attributes with specified hour, minute, and second
        updatedRepair.setCheckInDateTime(LocalDateTime.of(2024, 4, 15, 14, 30, 45));
        updatedRepair.setCheckOutDateTime(LocalDateTime.of(2024, 4, 17, 14, 30, 45)); // 2 days later
        updatedRepair.setCostumerDateTime(LocalDateTime.of(2024, 4, 19, 14, 30, 45)); // 2 days later from checkOut

        // Set boolean flag
        updatedRepair.setBonus(false);

        // Set related IDs
        updatedRepair.setRepairTypeCostId(1L);
        updatedRepair.setKilometerChargeId(1L);
        updatedRepair.setAgeChargeId(1L);
        updatedRepair.setRepairDiscount(1L);

        // Set other IDs
        updatedRepair.setCostRecordId(1L);

        when(repairRepository.findById(1L)).thenReturn(Optional.of(existingRepair));
        when(repairRepository.save(any(RepairEntity.class))).thenReturn(updatedRepair);

        RepairEntity result = repairService.updateRepair(1L,updatedRepair);

        assertThat(result.getRepairId()).isEqualTo(1L);
        assertThat(result.isBonus()).isEqualTo(updatedRepair.isBonus());
    }

    @Test 
public void whenGetTotalCost_thenCostIsCorrect() {
    RepairEntity repair = new RepairEntity();
    repair.setRepairId(1L);
    repair.setAgeChargeId(1L);
    repair.setRepairDiscount(1L);
    repair.setCostRecordId(1L);
    repair.setKilometerChargeId(1L);
    repair.setRepairTypeCostId(1L);
    repair.setBonus(true);

    LocalDateTime checkIn = LocalDateTime.of(2024, 4, 15, 10, 0);
    LocalDateTime checkOut = LocalDateTime.of(2024, 4, 16, 10, 0);
    LocalDateTime customerTime = LocalDateTime.of(2024, 4, 19, 10, 0);
    repair.setCheckInDateTime(checkIn);
    repair.setCheckOutDateTime(checkOut);
    repair.setCostumerDateTime(customerTime);

    VehicleRepairEntity vr = new VehicleRepairEntity();
    vr.setRepairId(1L);
    vr.setVehicleId(1L);
    vr.setVehicleRepairId(1L);

    VehicleEntity vehicle = new VehicleEntity();
    vehicle.setVehicleId(1L);
    vehicle.setMotorType("GASOLINE");
    vehicle.setVehicleType("SEDAN");
    vehicle.setBrand_id(1L);

    CostRecordEntity cr = new CostRecordEntity();
    cr.setCostRecordId(1L);

    RepairTypeCostEntity repairTypeCost = new RepairTypeCostEntity();
    repairTypeCost.setRepairTypeCostId(1L);
    repairTypeCost.setGasolineCost(120000);  // Asumiendo que este es el costo base

    RepairDiscountEntity repairDiscount = new RepairDiscountEntity();
    repairDiscount.setRepairDiscountId(1L);
    repairDiscount.setGasolineCost(0.0f);

    KilometerChargeEntity kilometerCharge = new KilometerChargeEntity();
    kilometerCharge.setKilometerChargeId(1L);
    kilometerCharge.setSedanCharge(0.0f);

    AgeChargeEntity ageCharge = new AgeChargeEntity();
    ageCharge.setAgeChargeId(1L);
    ageCharge.setSedanCharge(0.0f);

    BrandEntity brandEntity = new BrandEntity();
    brandEntity.setBrandId(1L);
    brandEntity.setBonusAmount(50000);

    when(brandService.getBrand(1L)).thenReturn(brandEntity);
    when(ageChargeService.getAgeCharge(1L)).thenReturn(ageCharge);
    when(kilometerChargeService.getKilometerCharge(1L)).thenReturn(kilometerCharge);
    when(repairDiscountService.getRepairDiscount(1L)).thenReturn(repairDiscount);
    when(repairRepository.findById(1L)).thenReturn(Optional.of(repair));
    when(costRecordService.getCostRecord(1L)).thenReturn(cr);
    when(vehicleRepairService.getByRepairId(1L)).thenReturn(vr);
    when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
    when(repairTypeCostService.getRepairTypeCost(1L)).thenReturn(repairTypeCost);

    float totalCost = repairService.getTotalCost(1L);

    assertThat(totalCost).isEqualTo(149940.0f);
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

    LocalDateTime checkIn = LocalDateTime.of(2024, 4, 15, 10, 0);
    LocalDateTime checkOut = LocalDateTime.of(2024, 4, 16, 10, 0);
    LocalDateTime customerTime = LocalDateTime.of(2024, 4, 19, 10, 0);
    repair.setCheckInDateTime(checkIn);
    repair.setCheckOutDateTime(checkOut);
    repair.setCostumerDateTime(customerTime);

    VehicleRepairEntity vr = new VehicleRepairEntity();
    vr.setRepairId(1L);
    vr.setVehicleId(1L);
    vr.setVehicleRepairId(1L);

    VehicleEntity vehicle = new VehicleEntity();
    vehicle.setVehicleId(1L);
    vehicle.setMotorType("GASOLINE");
    vehicle.setVehicleType("SEDAN");
    vehicle.setBrand_id(1L);

    CostRecordEntity cr = new CostRecordEntity();
    cr.setCostRecordId(1L);

    RepairTypeCostEntity repairTypeCost = new RepairTypeCostEntity();
    repairTypeCost.setRepairTypeCostId(1L);
    repairTypeCost.setGasolineCost(120000);  // Asumiendo que este es el costo base

    RepairDiscountEntity repairDiscount = new RepairDiscountEntity();
    repairDiscount.setRepairDiscountId(1L);
    repairDiscount.setGasolineCost(0.0f);

    KilometerChargeEntity kilometerCharge = new KilometerChargeEntity();
    kilometerCharge.setKilometerChargeId(1L);
    kilometerCharge.setSedanCharge(0.0f);

    AgeChargeEntity ageCharge = new AgeChargeEntity();
    ageCharge.setAgeChargeId(1L);
    ageCharge.setSedanCharge(0.0f);

    BrandEntity brandEntity = new BrandEntity();
    brandEntity.setBrandId(1L);
    brandEntity.setBonusAmount(50000);

    when(brandService.getBrand(1L)).thenReturn(brandEntity);
    when(ageChargeService.getAgeCharge(1L)).thenReturn(ageCharge);
    when(kilometerChargeService.getKilometerCharge(1L)).thenReturn(kilometerCharge);
    when(repairDiscountService.getRepairDiscount(1L)).thenReturn(repairDiscount);
    when(repairRepository.findById(1L)).thenReturn(Optional.of(repair));
    when(costRecordService.getCostRecord(1L)).thenReturn(cr);
    when(vehicleRepairService.getByRepairId(1L)).thenReturn(vr);
    when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
    when(repairTypeCostService.getRepairTypeCost(1L)).thenReturn(repairTypeCost);

    float totalCost = repairService.getTotalCost(1L);

    assertThat(totalCost).isEqualTo(149940.0f);

    }

    @Test 
    public void whenGetCostRecords_thenCostRIsCorrect(){

        List<Object[]> mockData = new ArrayList<>();
        Object[] recordData = new Object[12];
        recordData[0] = 1L; // CostRecordId
        recordData[1] = "Toyota"; // BrandName
        recordData[2] = "Corolla"; // VehicleModel
        recordData[3] = "XYZ123"; // Registration
        recordData[4] = 1500.0f; // RepairCost
        recordData[5] = 100.0f; // KilometerCharge
        recordData[6] = 50.0f; // AgeCharge
        recordData[7] = 20.0f; // LateCharge
        recordData[8] = 5.0f; // RepairsDiscount
        recordData[9] = 10.0f; // AttentionDayDiscount
        recordData[10] = 5.0f; // BonusDiscount
        recordData[11] = 1600.0f; // RepairCostOG
        mockData.add(recordData);

        when(repairService.getCostRecords()).thenReturn(mockData);

        List<Object[]> result = repairService.getCostRecords();
        assertThat(result).isNotEmpty();
    }

    @Test
    public void whenGetRepairTypeCost_thenDataIsCorrect() {
        // Preparar los datos mock
        List<Object[]> mockData = new ArrayList<>();
        Object[] typeCostData = new Object[7];
        typeCostData[0] = "Brake Replacement"; // RepairType
        typeCostData[1] = 10L; // Sedans
        typeCostData[2] = 5L; // Hatchbacks
        typeCostData[3] = 15L; // SUVs
        typeCostData[4] = 3L; // Pickups
        typeCostData[5] = 2L; // Vans
        typeCostData[6] = 2500.0f; // TotalCost
        mockData.add(typeCostData);

        when(repairService.getRepairTypeCost()).thenReturn(mockData);

        // Ejecución del método bajo prueba
        List<Object[]> result = repairService.getRepairTypeCost();

        // Verificaciones
        assertThat(result).isNotEmpty();
    }   
    @Test
    public void whenGetRepairMotor_ThenDataIsCorrect() {
        // Preparar los datos mock
        List<Object[]> mockData = new ArrayList<>();
        Object[] motorCostData = new Object[6];
        motorCostData[0] = "Engine Repair"; // RepairType
        motorCostData[1] = 20L; // Gasoline
        motorCostData[2] = 10L; // Diesel
        motorCostData[3] = 5L; // Hybrid
        motorCostData[4] = 3L; // Electric
        motorCostData[5] = 3000.0f; // TotalCost
        mockData.add(motorCostData);

        when(repairService.getRepairMotorCost()).thenReturn(mockData);

        // Ejecución del método bajo prueba
        List<Object[]> result = repairService.getRepairMotorCost();

        // Verificaciones
        assertThat(result).isNotEmpty();
        
    }


    @Test
    public void averageTimeModifier_ShouldConvertRawDataToDTOs() {
        // Preparar los datos mock
        List<Object[]> mockData = new ArrayList<>();
        Object[] averageTimeData = new Object[2];
        averageTimeData[0] = "Toyota"; // BrandName
        averageTimeData[1] = new BigDecimal("12.5"); // AverageRepairTime
        mockData.add(averageTimeData);

        when(repairService.getAverageRepairTime()).thenReturn(mockData);

        // Ejecución del método bajo prueba
        List<Object[]> result = repairService.getAverageRepairTime();

        // Verificaciones
        assertThat(result).isNotEmpty();
    }

    @Test
    public void getAllRepairs_shouldReturnAll(){
        RepairEntity r = new RepairEntity();
        r.setRepairId(1L);
        List<RepairEntity> list = new ArrayList<>();
        list.add(r);

        when(repairRepository.findAll()).thenReturn(list);
        List<RepairEntity> newList = repairService.getRepairs();
        assertThat(list).isEqualTo(newList);
    }

}
