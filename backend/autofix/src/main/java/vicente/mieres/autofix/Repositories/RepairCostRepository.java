package vicente.mieres.autofix.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vicente.mieres.autofix.Entities.RepairCostEntity;

public interface RepairCostRepository extends JpaRepository<RepairCostEntity, Long>{
    
}
