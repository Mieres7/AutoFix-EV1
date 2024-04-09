package vicente.mieres.autofix.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.RepairTypeCostEntity;

@Repository
public interface RepairTypeCostRepository extends CrudRepository<RepairTypeCostEntity, Long>{
    

}
