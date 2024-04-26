package vicente.mieres.autofix.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Services.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/brand")
@CrossOrigin("*")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("/")
    public ResponseEntity<List<BrandEntity>> getBrandResponseEntity() {
        List<BrandEntity> brands = brandService.getBrands();
        return ResponseEntity.ok(brands);
    }
    
    @PutMapping("/")
    public ResponseEntity<BrandEntity> updateBrand(@RequestBody BrandEntity brand){
        BrandEntity brandUpdated = brandService.updateBrand(brand);
        return ResponseEntity.ok(brandUpdated);
    }


}
