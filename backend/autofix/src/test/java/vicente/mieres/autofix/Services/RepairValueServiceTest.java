package vicente.mieres.autofix.Services;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Entities.CostRecordEntity;
import vicente.mieres.autofix.Repositories.CostRecordRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@Transactional
class RepairValueServiceTest {

    @Autowired
    private RepairValueService repairValueService;
    @Autowired
    private EntityManager entityManager;
    @MockBean
    private CostRecordRepository costRecordRepository;
    
    
    @Test
    public void whenGetTypeCostAndMotorGasoline_thenTypeCostIs120000(){
        int typeCost = repairValueService.getTypeCostValue(1L, "GASOLINE");
        assertThat(typeCost).isEqualTo(120000);
    }

    @Test
    public void whenGetTypeCostAndMotorDiesel_thenTypeCostIs120000(){
        int typeCost = repairValueService.getTypeCostValue(1L, "DIESEL");
        assertThat(typeCost).isEqualTo(120000);
    }

    @Test 
    public void whenGetTypeCostAndMotorHybrid_thenTypeCostIs180000(){
        int typeCost = repairValueService.getTypeCostValue(1L, "HYBRID");
        assertThat(typeCost).isEqualTo(180000);
    }

    @Test
    public void whenGetTypeCostAndMotorElectric_thenTypeCostIs220000(){
        int typeCost = repairValueService.getTypeCostValue(1L, "ELECTRIC");
        assertThat(typeCost).isEqualTo(220000);
    }

    @Test
    public void whenGetRepairDiscountAndMotorGasoline_thenRepairDiscountIs05(){
        float repairDiscount = repairValueService.getRepairDiscount(1L, "GASOLINE");
        assertThat(repairDiscount).isEqualTo(0.05f);
    }

    @Test
    public void whenGetRepairDiscountAndMotorDiesel_thenRepairDiscountIs07(){
        float repairDiscount = repairValueService.getRepairDiscount(1L, "DIESEL");
        assertThat(repairDiscount).isEqualTo(0.07f);
    }

    @Test
    public void whenGetRepairDiscountAndMotorHybrid_thenRepairDiscountIs10(){
        float repairDiscount = repairValueService.getRepairDiscount(1L, "HYBRID");
        assertThat(repairDiscount).isEqualTo(0.1f);
    }

    @Test
    public void whenGetRepairDiscountAndMotorElectric_thenRepairDiscountIs08(){
        float repairDiscount = repairValueService.getRepairDiscount(1L, "ELECTRIC");
        assertThat(repairDiscount).isEqualTo(0.08f);
    }

    @Test 
    void whenGetKmAgeChargeAndTypeSedan_thenKmAgeChargeIsCorrect(){
        ArrayList<Float> kmAgeCharges = repairValueService.getKilometerAgeCharge(2L, 2L, "SEDAN");
        assertThat(kmAgeCharges).containsExactly(0.03f, 0.05f);
    }

    @Test 
    void whenGetKmAgeChargeAndTypeHatchBack_thenKmAgeChargeIsCorrect(){
        ArrayList<Float> kmAgeCharges = repairValueService.getKilometerAgeCharge(2L, 2L, "HATCHBACK");
        assertThat(kmAgeCharges).containsExactly(0.03f, 0.05f);
    }

    @Test 
    void whenGetKmAgeChargeAndTypeSuv_thenKmAgeChargeIsCorrect(){
        ArrayList<Float> kmAgeCharges = repairValueService.getKilometerAgeCharge(2L, 2L, "SUV");
        assertThat(kmAgeCharges).containsExactly(0.05f, 0.07f);
    }

    @Test 
    void whenGetKmAgeChargeAndTypePickUp_thenKmAgeChargeIsCorrect(){
        ArrayList<Float> kmAgeCharges = repairValueService.getKilometerAgeCharge(2L, 2L, "PICKUP");
        assertThat(kmAgeCharges).containsExactly(0.05f, 0.07f);
    }

    @Test 
    void whenGetKmAgeChargeAndTypeVan_thenKmAgeChargeIsCorrect(){
        ArrayList<Float> kmAgeCharges = repairValueService.getKilometerAgeCharge(2L, 2L, "VAN");
        assertThat(kmAgeCharges).containsExactly(0.05f, 0.07f);
    }

    @Test
    public void whenGetBonusValue_thenBonusValueIsCorrect() {
        BrandEntity brand = new BrandEntity();
        brand.setBrandId(1L);  
        brand.setDiscount(100);
    
        entityManager.merge(brand);  
        entityManager.flush();
    
        int discount = repairValueService.getBonusValue(1L);
        assertEquals(100, discount);
    }

    @Test
    public void whenGetRepairValue_ThenValueIsCorrect(){

        CostRecordEntity cr = new CostRecordEntity();
        cr.setCostRecordId(1L);
        when(costRecordRepository.findById(1L)).thenReturn(Optional.of(cr));

        int typeCost = 120000;
        float repairDiscount = 0.02f;
        List<Float> kilometerAgeCharge = Arrays.asList(0.05f, 0.03f); // 5% and 3%

        int bonusDiscount = 7000; 
        boolean attentionDayDiscount = true;
        Long daysBetween = -10L; 

        float repairValue = repairValueService.getRepairValue(cr.getCostRecordId(), typeCost, repairDiscount, kilometerAgeCharge, bonusDiscount, attentionDayDiscount, daysBetween);

        assertThat(repairValue).isEqualTo(200158.0f);
    }

    @Test
    public void whenGetRepairValue_ThenValueIsCorrectV2(){

        CostRecordEntity cr = new CostRecordEntity();
        cr.setCostRecordId(1L);
        when(costRecordRepository.findById(1L)).thenReturn(Optional.of(cr));
        
        int typeCost = 120000;
        float repairDiscount = 0.02f;
        List<Float> kilometerAgeCharge = Arrays.asList(0.05f, 0.03f); // 5% and 3%

        int bonusDiscount = 7000; 
        boolean attentionDayDiscount = false;
        Long daysBetween = -10L; 

        float repairValue = repairValueService.getRepairValue(cr.getCostRecordId(), typeCost, repairDiscount, kilometerAgeCharge, bonusDiscount, attentionDayDiscount, daysBetween);

        assertThat(repairValue).isEqualTo(214438.0f);
    }
    
}