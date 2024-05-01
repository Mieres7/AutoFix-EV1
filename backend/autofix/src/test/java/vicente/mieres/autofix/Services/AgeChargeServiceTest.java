package vicente.mieres.autofix.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import jakarta.transaction.Transactional;
import vicente.mieres.autofix.Entities.AgeChargeEntity;
import vicente.mieres.autofix.Repositories.AgeChargeRepository;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AgeChargeServiceTest {

    @Autowired
    private AgeChargeService ageChargeService;
    @MockBean
    private AgeChargeRepository ageChargeRepository;

    @Test
    public void getById_shouldReturnId(){
        AgeChargeEntity a = new AgeChargeEntity();
        a.setAgeChargeId(1L);
        when(ageChargeRepository.findById(1L)).thenReturn(Optional.of(a));
        AgeChargeEntity anew = ageChargeService.getAgeCharge(1L);
        assertThat(a).isEqualTo(anew);
    }

    
}
