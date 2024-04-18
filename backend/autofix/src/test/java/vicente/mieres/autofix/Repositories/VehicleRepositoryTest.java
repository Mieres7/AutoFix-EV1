package vicente.mieres.autofix.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.EntityManager;
import vicente.mieres.autofix.Entities.VehicleEntity;

@DataJpaTest
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void whenExistByRegistration_thenVehicleExists(){

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setRegistration("ABCD12");

        entityManager.merge(vehicle);
        entityManager.flush();

        boolean isExists = vehicleRepository.existsByRegistration("ABCD12");

        assertThat(isExists).isTrue();

    }


}
