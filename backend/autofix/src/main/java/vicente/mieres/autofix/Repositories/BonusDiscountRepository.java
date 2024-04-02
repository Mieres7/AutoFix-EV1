package vicente.mieres.autofix.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.BonusDiscountEntity;

@Repository
public interface BonusDiscountRepository extends CrudRepository<BonusDiscountEntity, Long>{
    
}
