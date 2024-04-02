package vicente.mieres.autofix.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.KilometerChargeEntity;

@Repository
public interface KilometerChargeRepository extends CrudRepository<KilometerChargeEntity, Long>{
    
}
