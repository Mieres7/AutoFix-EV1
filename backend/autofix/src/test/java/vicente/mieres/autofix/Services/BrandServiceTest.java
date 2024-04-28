package vicente.mieres.autofix.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import jakarta.transaction.Transactional;
import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Repositories.BrandRepository;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class BrandServiceTest {

    @Autowired
    private BrandService brandService;
    @MockBean
    private BrandRepository brandRepository;

    @Test
    public void whenGetBrands__ThenAllBrands() {
        BrandEntity b =  new BrandEntity();
        b.setBrandId(1L);

        List<BrandEntity> list = new ArrayList<>();
        list.add(b);

        when(brandRepository.findAll()).thenReturn(list);
        List<BrandEntity> newlist = brandService.getBrands();
        assertThat(newlist.size()).isEqualTo(1);

    }

    @Test
    public void whenFindById_ThenIsCorrect(){
        BrandEntity b = new BrandEntity();
        b.setBrandId(1L);
        when(brandRepository.findById(1L)).thenReturn(Optional.of(b));
        BrandEntity bnew  = brandService.getBrand(1L);
        assertThat(b).isEqualTo(bnew);
    }

    @Test
    public void whenUpdate_thenIsCorrect(){

        BrandEntity b = new BrandEntity();
        b.setBonus(false);
        b.setBonusAmount(5);
        b.setBrandId(1L);
        b.setBrandName("TOYOTA");
        b.setDiscount(5000);
        b.setPeriod("01-2024");

        BrandEntity b2 = new BrandEntity();
        b2.setBonus(true);
        b2.setBonusAmount(5);
        b2.setBrandId(1L);
        b2.setBrandName("TOYOTA");
        b2.setDiscount(5000);
        b2.setPeriod("01-2024");

        when(brandRepository.findById(1L)).thenReturn(Optional.of(b));
        when(brandRepository.save(b2)).thenReturn(b2);

        BrandEntity b3 = brandService.updateBrand(b2);

        assertThat(b3.getBrandName()).isEqualTo(b2.getBrandName());
        

    }

    @Test
    public void whenUpdate_thenIsCorrect2(){

        BrandEntity b = new BrandEntity();
        b.setBonus(true);
        b.setBonusAmount(-5);
        b.setBrandId(1L);
        b.setBrandName("TOYOTA");
        b.setDiscount(-5000);
        b.setPeriod("01-2024");

        BrandEntity b2 = new BrandEntity();
        b2.setBonus(false);
        b2.setBonusAmount(5);
        b2.setBrandId(1L);
        b2.setBrandName("TOYOTA");
        b2.setDiscount(-5000);
        b2.setPeriod("01-2024");

        
        when(brandRepository.findById(1L)).thenReturn(Optional.of(b));
        when(brandRepository.save(b2)).thenReturn(b2);

        BrandEntity b3 = brandService.updateBrand(b2);

        assertThat(b3.getBrandName()).isEqualTo("TOYOTA");
        

    }

}
