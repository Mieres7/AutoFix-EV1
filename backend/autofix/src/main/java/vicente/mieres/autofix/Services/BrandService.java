package vicente.mieres.autofix.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Repositories.BrandRepository;

@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    public BrandEntity getBrand(Long brandId){
        return brandRepository.findById(brandId).get();
    }
}
