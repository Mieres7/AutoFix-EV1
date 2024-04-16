package vicente.mieres.autofix.Services;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;



import jakarta.transaction.Transactional;
import vicente.mieres.autofix.DTO.CreateRepair;
import vicente.mieres.autofix.Entities.RepairEntity;
import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Entities.VehicleRepairEntity;
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
        vehicle.setRepairs(1);
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
        vehicle.setRepairs(1);
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
        vehicle.setRepairs(1);
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





}
