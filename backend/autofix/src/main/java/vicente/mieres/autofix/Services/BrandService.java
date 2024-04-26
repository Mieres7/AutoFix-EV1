package vicente.mieres.autofix.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Repositories.BrandRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    public BrandEntity getBrand(Long brandId){
        System.out.println(brandId);
        return brandRepository.findById(brandId).get();
    }

    public List<BrandEntity> getBrands(){
        return  (ArrayList<BrandEntity>) brandRepository.findAll();
    }

    public BrandEntity updateBrand(BrandEntity brand){

        Long brandId = brand.getBrandId();
        BrandEntity brandFound = this.getBrand(brandId);

        if (brand.getBrandId() != null) {
            brandFound.setBrandId(brand.getBrandId());
        }
        if (brand.isBonus()) {
            
            
            brandFound.setBonus(brand.isBonus());
        }
        if(!brand.isBonus()){

            brandFound.setBonus(brand.isBonus());
        }
        if (brand.getBonusAmount()  > 0) {
            brandFound.setBonusAmount(brand.getBonusAmount());
        }
        if (brand.getBrandName() != null) {
            brandFound.setBrandName(brand.getBrandName());
        }
        if (brand.getDiscount() > 0) {
            brandFound.setDiscount(brand.getDiscount());
        }
        if (brand.getPeriod() != null) {
            brandFound.setPeriod(brand.getPeriod());
        }

        return brandRepository.save(brandFound);
        
    }

}
