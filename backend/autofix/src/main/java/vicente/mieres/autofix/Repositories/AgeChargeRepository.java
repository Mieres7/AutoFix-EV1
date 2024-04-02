package vicente.mieres.autofix.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.AgeChargeEntity;

@Repository
public interface AgeChargeRepository extends CrudRepository<AgeChargeEntity, Long>{
    
}
