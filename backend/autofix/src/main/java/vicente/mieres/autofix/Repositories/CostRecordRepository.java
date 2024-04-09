package vicente.mieres.autofix.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.CostRecordEntity;

@Repository
public interface CostRecordRepository extends CrudRepository<CostRecordEntity, Long>{
    
}
