package vicente.mieres.autofix.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.Entities.AgeChargeEntity;
import vicente.mieres.autofix.Repositories.AgeChargeRepository;

@Service
public class AgeChargeService {

    @Autowired
    AgeChargeRepository ageChargeRepository;

    public AgeChargeEntity getAgeCharge(Long id){
        return ageChargeRepository.findById(id).get();
    }
    
}
