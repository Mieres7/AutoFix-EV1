package vicente.mieres.autofix.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import vicente.mieres.autofix.Entities.VehicleRepairEntity;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class VehicleRepairRepositoryTest {
    @Autowired
    private VehicleRepairRepository vehicleRepairRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void whenFindByRepairId_thenReturnVehicleRepairEntity(){

        VehicleRepairEntity vehicleRepair = new VehicleRepairEntity(1L, 1L, 1L);
        entityManager.merge(vehicleRepair);
        entityManager.flush();

        VehicleRepairEntity found = vehicleRepairRepository.findByRepairId(1L);

        assertThat(found.getRepairId()).isEqualTo(vehicleRepair.getRepairId());
    }
}
