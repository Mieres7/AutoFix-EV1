package vicente.mieres.autofix.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import vicente.mieres.autofix.Entities.VehicleEntity;

@SpringBootTest
@Transactional
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void whenExistByRegistration_thenVehicleExists(){

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setVehicleId(null);
        vehicle.setRegistration("ZXCV12");

        entityManager.merge(vehicle);
        entityManager.flush();

        boolean isExists = vehicleRepository.existsByRegistration("ZXCV12");

        assertThat(isExists).isTrue();

    }


}
