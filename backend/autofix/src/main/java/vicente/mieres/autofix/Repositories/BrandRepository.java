package vicente.mieres.autofix.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vicente.mieres.autofix.Entities.BrandEntity;

@Repository
public interface BrandRepository extends CrudRepository<BrandEntity, Long>{
    
}
