package vicente.mieres.autofix.Services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.Entities.RepairTypeCostEntity;
import vicente.mieres.autofix.Repositories.RepairTypeCostRepository;

@Service
public class RepairTypeCostService {

    @Autowired
    RepairTypeCostRepository repairTypeCostRepository;

    public RepairTypeCostEntity getRepairTypeCost(Long id){
        return repairTypeCostRepository.findById(id).get();
    }

    public List<RepairTypeCostEntity> getRepairTypeCosts(){
        return (ArrayList<RepairTypeCostEntity>) repairTypeCostRepository.findAll();
    }

}
