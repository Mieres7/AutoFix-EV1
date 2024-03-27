package vicente.mieres.autofix.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vicente.mieres.autofix.Entities.VehicleEntity;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{
    
}
