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
import vicente.mieres.autofix.Entities.KilometerChargeEntity;
import vicente.mieres.autofix.Repositories.KilometerChargeRepository;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class KilometerChargeServiceTest {

    @Autowired
    private KilometerChargeService kilometerChargeService;
    @MockBean
    private KilometerChargeRepository kilometerChargeRepository;

    @Test
    public void getById_shouldReturnId(){
        KilometerChargeEntity a = new KilometerChargeEntity();
        a.setKilometerChargeId(1L);
        when(kilometerChargeRepository.findById(1L)).thenReturn(Optional.of(a));
        KilometerChargeEntity anew = kilometerChargeService.getKilometerCharge(1L);
        assertThat(a).isEqualTo(anew);
    }

}
