package vicente.mieres.autofix.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vicente.mieres.autofix.Entities.VehicleEntity;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{
    
    public boolean existsByRegistration(String registration);

}
