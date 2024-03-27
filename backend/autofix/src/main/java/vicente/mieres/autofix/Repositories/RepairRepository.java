package vicente.mieres.autofix.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vicente.mieres.autofix.Entities.RepairEntity;

public interface RepairRepository extends JpaRepository<RepairEntity, Long>{
    
}
