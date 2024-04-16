package vicente.mieres.autofix.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import vicente.mieres.autofix.Entities.VehicleRepairEntity;
import vicente.mieres.autofix.Repositories.VehicleRepairRepository;

@SpringBootTest
public class VehicleRepairServiceTest {

    @Autowired 
    private VehicleRepairService vehicleRepairService;
    @MockBean
    private VehicleRepairRepository vehicleRepairRepository;

    @Test
    public void whenSaveVehicleRepair_ThenVehicleRepairSaveIsCorrect(){

        VehicleRepairEntity vehicleRepair = new VehicleRepairEntity();
        vehicleRepair.setRepairId(1L);
        vehicleRepair.setVehicleId(1L);
        vehicleRepair.setVehicleRepairId(1L);
        
        when(vehicleRepairRepository.save(vehicleRepair)).thenReturn(vehicleRepair);
        VehicleRepairEntity vehicleRepairSaved = vehicleRepairService.saveVehicleRepair(vehicleRepair);

        assertThat(vehicleRepairSaved.getVehicleRepairId()).isEqualTo(1L);
    }

    @Test
    public void whenGetByRepairId_ThenVehicleRepairIsCorrect(){

        VehicleRepairEntity vehicleRepair = new VehicleRepairEntity();
        vehicleRepair.setRepairId(1L);
        vehicleRepair.setVehicleId(1L);
        vehicleRepair.setVehicleRepairId(1L);

        when(vehicleRepairRepository.findByRepairId(1L)).thenReturn(vehicleRepair);
        
        VehicleRepairEntity vehicleRepairFound = vehicleRepairService.getByRepairId(1L);
        assertThat(vehicleRepairFound.getRepairId()).isEqualTo(1L);

    }

    
}
