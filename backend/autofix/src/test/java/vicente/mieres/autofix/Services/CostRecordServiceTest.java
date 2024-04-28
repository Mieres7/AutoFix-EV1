package vicente.mieres.autofix.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import jakarta.transaction.Transactional;
import vicente.mieres.autofix.Entities.CostRecordEntity;
import vicente.mieres.autofix.Repositories.CostRecordRepository;

@SpringBootTest
// @Transactional
@ActiveProfiles("test")
public class CostRecordServiceTest {

    @Autowired 
    private CostRecordService costRecordService;
    @MockBean
    private CostRecordRepository costRecordRepository;

    @Test 
    public void whenSaveCostRecord_ThenCostRecordIsSaved(){

        CostRecordEntity costRecord = new CostRecordEntity();
        costRecord.setCostRecordId(1L);

        when(costRecordRepository.save(costRecord)).thenReturn(costRecord);

        CostRecordEntity costRecordSaved = costRecordService.saveCostRecord(costRecord);

        assertThat(costRecordSaved.getCostRecordId()).isEqualTo(1L);
    }

    @Test 
    public void whenGetCostRecords_ThenCostRecordsIsCorrect(){
        CostRecordEntity cr1 = new CostRecordEntity();
        cr1.setCostRecordId(1L);

        CostRecordEntity cr2 = new CostRecordEntity();
        cr2.setCostRecordId(2l);

        ArrayList<CostRecordEntity> costRecords = new ArrayList<>();
        costRecords.add(cr1);
        costRecords.add(cr2);

        when(costRecordRepository.findAll()).thenReturn(costRecords);

        ArrayList<CostRecordEntity> crs = costRecordService.getCostRecords();
        assertThat(crs.size()).isEqualTo(2);

    }



    
    
}
