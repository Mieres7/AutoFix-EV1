package vicente.mieres.autofix.Services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import jakarta.transaction.Transactional;
import vicente.mieres.autofix.DTO.CreateVehicle;
import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Repositories.VehicleRepository;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;
    @MockBean
    private VehicleRepository vehicleRepository;

    @Test
    public void whenSaveVehicle_ThenVehicleSaveIsCorrect() throws Exception{

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setVehicleId(1L);
        vehicle.setRegistration("ABCD12");

        CreateVehicle vehicleData = new CreateVehicle("ABCD12", "Corolla", "SEDAN", "2024", "GASOLINE", 5, 5000, 1L);
        when(vehicleRepository.save(any(VehicleEntity.class))).thenReturn(vehicle);

        VehicleEntity savedVehicle = vehicleService.saveVehicle(vehicleData);
        
        assertThat(savedVehicle.getRegistration()).isEqualTo("ABCD12");
        verify(vehicleRepository, times(1)).save(any(VehicleEntity.class));
    }

    @Test
    public void whenGetVehicles_thenVehiclesIsCorrect() throws Exception{
        
        VehicleEntity vehicle1 = new VehicleEntity();
        vehicle1.setVehicleId(1l);

        VehicleEntity vehicle2 = new VehicleEntity();
        vehicle2.setVehicleId(2l);

        ArrayList<VehicleEntity> expectedVehicles = new ArrayList<>();
        expectedVehicles.add(vehicle1);
        expectedVehicles.add(vehicle2);

        when(vehicleRepository.findAll()).thenReturn(expectedVehicles);

        ArrayList<VehicleEntity> vehicles = vehicleService.getVehicles();
        assertThat(vehicles.size()).isEqualTo(2);

    }

    @Test
    public void whenGetVehicle_thenVehicleIsCorrect(){

        VehicleEntity vehicle1 = new VehicleEntity();
        vehicle1.setVehicleId(1l);
        vehicle1.setRegistration("ABCD12");

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle1));

        VehicleEntity vehicle = vehicleService.getVehicle(1L);
        assertThat(vehicle.getRegistration()).isEqualTo("ABCD12");

    }

    @Test
    public void whenGetVehicleByRegistration_thenVehicleIsCorrect(){

        VehicleEntity vehicle1 = new VehicleEntity();
        vehicle1.setVehicleId(1l);
        vehicle1.setRegistration("ABCD12");

        when(vehicleRepository.existsByRegistration("ABCD12")).thenReturn(true);

        boolean vehicle = vehicleService.findByRegistration("ABCD12");
        assertThat(vehicle).isEqualTo(true);

    }

    // @Test
    // public void whenDelete_VehicleIsDeleted(){
    //     VehicleEntity newVehicle = new VehicleEntity();
    //     newVehicle.setVehicleId(1L);
    //     vehicleService.deleteVehicle(1L);

    // }

    

}
