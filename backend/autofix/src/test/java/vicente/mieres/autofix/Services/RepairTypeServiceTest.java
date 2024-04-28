package vicente.mieres.autofix.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import jakarta.transaction.Transactional;
import vicente.mieres.autofix.Entities.RepairTypeCostEntity;
import vicente.mieres.autofix.Repositories.RepairTypeCostRepository;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RepairTypeServiceTest {

    @Autowired
    private RepairTypeCostService repairTypeCostService;
    @MockBean
    private RepairTypeCostRepository repairTypeCostRepository;
    
    @Test 
    public void whenGetById_thenIsCorrect(){
        RepairTypeCostEntity rt = new RepairTypeCostEntity();
        rt.setRepairTypeCostId(1L);
        when(repairTypeCostRepository.findById(1L)).thenReturn(Optional.of(rt));

        RepairTypeCostEntity ex = repairTypeCostService.getRepairTypeCost(1L);
        assertThat(rt).isEqualTo(ex);
    }

    @Test
    public void whenGetAll_ThenReturnAll(){
        RepairTypeCostEntity rt = new RepairTypeCostEntity();
        rt.setRepairTypeCostId(1L);
        RepairTypeCostEntity rt2 = new RepairTypeCostEntity();
        rt2.setRepairTypeCostId(2L);

        List<RepairTypeCostEntity> list =  new ArrayList<>();

        when(repairTypeCostRepository.findAll()).thenReturn(list);
        List<RepairTypeCostEntity> newlist = repairTypeCostService.getRepairTypeCosts();
        assertThat(list).isEqualTo(newlist);
    }

}
