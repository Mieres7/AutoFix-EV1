package vicente.mieres.autofix.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.RepairDiscountEntity;

@Repository
public interface RepairDiscountRepository extends CrudRepository<RepairDiscountEntity, Long>{
    
}
