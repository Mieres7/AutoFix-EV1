package vicente.mieres.autofix.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.Entities.KilometerChargeEntity;
import vicente.mieres.autofix.Repositories.KilometerChargeRepository;

@Service
public class KilometerChargeService {

    @Autowired
    KilometerChargeRepository kilometerChargeRepository;

    public KilometerChargeEntity getKilometerCharge(Long id){
        return kilometerChargeRepository.findById(id).get();
    }
    
}
