package vicente.mieres.autofix.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.VehicleRepairEntity;

@Repository
public interface VehicleRepairRepository extends JpaRepository<VehicleRepairEntity, Long>{ 

    public VehicleRepairEntity findByRepairId(Long repairId);
    
}
