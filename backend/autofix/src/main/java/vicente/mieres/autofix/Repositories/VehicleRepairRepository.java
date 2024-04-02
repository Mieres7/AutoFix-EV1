package vicente.mieres.autofix.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.VehicleRepairEntity;

@Repository
public interface VehicleRepairRepository extends CrudRepository<VehicleRepairEntity, Long>{
    
}
