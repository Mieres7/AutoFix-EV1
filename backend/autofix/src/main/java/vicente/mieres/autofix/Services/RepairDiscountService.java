package vicente.mieres.autofix.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.Entities.RepairDiscountEntity;
import vicente.mieres.autofix.Repositories.RepairDiscountRepository;

@Service
public class RepairDiscountService {
    
    @Autowired
    RepairDiscountRepository repairDiscountRepository;

    public RepairDiscountEntity getRepairDiscount(Long id){
        return repairDiscountRepository.findById(id).get();
    }
}
