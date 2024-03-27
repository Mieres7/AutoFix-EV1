package vicente.mieres.autofix.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vicente.mieres.autofix.Entities.RepairTypeEntity;

public interface RepairTypeRepository extends JpaRepository<RepairTypeEntity, Long>{
    
}
