package vicente.mieres.autofix.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.RepairEntity;

@Repository
public interface RepairRepository extends CrudRepository<RepairEntity, Long>{
    
}
