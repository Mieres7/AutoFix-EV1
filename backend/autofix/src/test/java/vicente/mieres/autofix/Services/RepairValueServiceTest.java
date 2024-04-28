package vicente.mieres.autofix.Services;

import org.assertj.core.util.ArrayWrapperList;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import vicente.mieres.autofix.Entities.AgeChargeEntity;
import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Entities.CostRecordEntity;
import vicente.mieres.autofix.Entities.KilometerChargeEntity;
import vicente.mieres.autofix.Entities.RepairDiscountEntity;
import vicente.mieres.autofix.Entities.RepairTypeCostEntity;
import vicente.mieres.autofix.Repositories.AgeChargeRepository;
import vicente.mieres.autofix.Repositories.CostRecordRepository;
import vicente.mieres.autofix.Repositories.KilometerChargeRepository;
import vicente.mieres.autofix.Repositories.RepairDiscountRepository;
import vicente.mieres.autofix.Repositories.RepairTypeCostRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.util.*;




@SpringBootTest
@Transactional
@ActiveProfiles("test")
class RepairValueServiceTest {

    @Autowired
    private RepairValueService repairValueService;
    @Autowired
    private EntityManager entityManager;
    @MockBean
    private CostRecordRepository costRecordRepository;
    @MockBean 
    private KilometerChargeService kilometerChargeService;
    @MockBean 
    private AgeChargeService ageChargeService;
    @MockBean 
    private RepairTypeCostRepository repairTypeCostRepository;
    @MockBean
    private RepairDiscountRepository repairDiscountRepository;
    @MockBean
    private KilometerChargeRepository kilometerChargeRepository;
    @MockBean
    private AgeChargeRepository ageChargeRepository;
    @MockBean
    private BrandService brandService;

    
    
    @Test
    public void whenGetTypeCostAndMotorGasoline_thenTypeCostIs120000(){
        RepairTypeCostEntity rtc = new RepairTypeCostEntity();
        rtc.setRepairTypeCostId(1L);
        rtc.setGasolineCost(120000);
        when(repairTypeCostRepository.findById(1L)).thenReturn(Optional.of(rtc));
        int typeCost = repairValueService.getTypeCostValue(1L, "GASOLINE");
        assertThat(typeCost).isEqualTo(120000);
    }

    @Test
    public void whenGetTypeCostAndMotorDiesel_thenTypeCostIs120000(){
        RepairTypeCostEntity rtc = new RepairTypeCostEntity();
        rtc.setRepairTypeCostId(1L);
        rtc.setDieselCost(120000);
        when(repairTypeCostRepository.findById(1L)).thenReturn(Optional.of(rtc));
        int typeCost = repairValueService.getTypeCostValue(1L, "DIESEL");
        assertThat(typeCost).isEqualTo(120000);
    }

    @Test 
    public void whenGetTypeCostAndMotorHybrid_thenTypeCostIs180000(){
        RepairTypeCostEntity rtc = new RepairTypeCostEntity();
        rtc.setRepairTypeCostId(1L);
        rtc.setHybridCost(180000);
        when(repairTypeCostRepository.findById(1L)).thenReturn(Optional.of(rtc));
        int typeCost = repairValueService.getTypeCostValue(1L, "HYBRID");
        assertThat(typeCost).isEqualTo(180000);
    }

    @Test
    public void whenGetTypeCostAndMotorElectric_thenTypeCostIs220000(){
        RepairTypeCostEntity rtc = new RepairTypeCostEntity();
        rtc.setRepairTypeCostId(1L);
        rtc.setElectricCost(220000);
        when(repairTypeCostRepository.findById(1L)).thenReturn(Optional.of(rtc));
        int typeCost = repairValueService.getTypeCostValue(1L, "ELECTRIC");
        assertThat(typeCost).isEqualTo(220000);
    }

    @Test
    public void whenGetRepairDiscountAndMotorGasoline_thenRepairDiscountIs05(){

        RepairDiscountEntity rd = new RepairDiscountEntity();
        rd.setRepairDiscountId(1L);
        rd.setGasolineCost(0.05f);
        when(repairDiscountRepository.findById(1L)).thenReturn(Optional.of(rd));
        float repairDiscount = repairValueService.getRepairDiscount(1L, "GASOLINE");
        assertThat(repairDiscount).isEqualTo(0.05f);
    }

    @Test
    public void whenGetRepairDiscountAndMotorDiesel_thenRepairDiscountIs07(){
        RepairDiscountEntity rd = new RepairDiscountEntity();
        rd.setRepairDiscountId(1L);
        rd.setDieselCost(0.07f);
        when(repairDiscountRepository.findById(1L)).thenReturn(Optional.of(rd));

        float repairDiscount = repairValueService.getRepairDiscount(1L, "DIESEL");
        assertThat(repairDiscount).isEqualTo(0.07f);
    }

    @Test
    public void whenGetRepairDiscountAndMotorHybrid_thenRepairDiscountIs10(){
        RepairDiscountEntity rd = new RepairDiscountEntity();
        rd.setRepairDiscountId(1L);
        rd.setHybridCost(0.1f);
        when(repairDiscountRepository.findById(1L)).thenReturn(Optional.of(rd));
        float repairDiscount = repairValueService.getRepairDiscount(1L, "HYBRID");
        assertThat(repairDiscount).isEqualTo(0.1f);
    }

    @Test
    public void whenGetRepairDiscountAndMotorElectric_thenRepairDiscountIs08(){
        RepairDiscountEntity rd = new RepairDiscountEntity();
        rd.setRepairDiscountId(1L);
        rd.setElectricCost(0.08f);
        when(repairDiscountRepository.findById(1L)).thenReturn(Optional.of(rd));
        float repairDiscount = repairValueService.getRepairDiscount(1L, "ELECTRIC");
        assertThat(repairDiscount).isEqualTo(0.08f);
    }

    @Test 
    void whenGetKmAgeChargeAndTypeSedan_thenKmAgeChargeIsCorrect(){
        KilometerChargeEntity km = new KilometerChargeEntity();
        AgeChargeEntity ac = new AgeChargeEntity();
        km.setKilometerChargeId(2L);
        ac.setAgeChargeId(2L);
        km.setSedanCharge(0.03f);
        ac.setSedanCharge(0.05f);
    
        // Mockear servicios para retornar las entidades creadas
        when(kilometerChargeService.getKilometerCharge(2L)).thenReturn(km);
        when(ageChargeService.getAgeCharge(2L)).thenReturn(ac);
    
        // Llamar al método bajo prueba
        ArrayList<Float> kmAgeCharges = repairValueService.getKilometerAgeCharge(2L, 2L, "SEDAN");
    
        // Verificar los resultados
        assertThat(kmAgeCharges).containsExactly(0.03f, 0.05f);
    }

    @Test 
    void whenGetKmAgeChargeAndTypeHatchBack_thenKmAgeChargeIsCorrect(){
        KilometerChargeEntity km = new KilometerChargeEntity();
        AgeChargeEntity ac  = new AgeChargeEntity();
        km.setKilometerChargeId(3L);
        ac.setAgeChargeId(3L);
        km.setHatchbackCharge(0.03f);
        ac.setHatchbackCharge(0.05f);
        
        when(kilometerChargeService.getKilometerCharge(3L)).thenReturn(km);        
        when(ageChargeService.getAgeCharge(3L)).thenReturn(ac);        

        ArrayList<Float> kmAgeCharges = repairValueService.getKilometerAgeCharge(3L, 3L, "HATCHBACK");

        assertThat(kmAgeCharges).containsExactly(0.03f, 0.05f);
    }

    @Test 
    void whenGetKmAgeChargeAndTypeSuv_thenKmAgeChargeIsCorrect(){
        KilometerChargeEntity km = new KilometerChargeEntity();
        AgeChargeEntity ac  = new AgeChargeEntity();
        km.setKilometerChargeId(2L);
        ac.setAgeChargeId(2L);
        km.setSuvCharge(0.05f);
        ac.setSuvCharge(0.07f);
        
        when(kilometerChargeService.getKilometerCharge(2L)).thenReturn(km);        
        when(ageChargeService.getAgeCharge(2L)).thenReturn(ac);        

        ArrayList<Float> kmAgeCharges = repairValueService.getKilometerAgeCharge(2L, 2L, "SUV");

        assertThat(kmAgeCharges).containsExactly(0.05f, 0.07f);
    }

    @Test 
    void whenGetKmAgeChargeAndTypePickUp_thenKmAgeChargeIsCorrect(){
        KilometerChargeEntity km = new KilometerChargeEntity();
        AgeChargeEntity ac  = new AgeChargeEntity();
        km.setKilometerChargeId(2L);
        ac.setAgeChargeId(2L);
        km.setPickupCharge(0.05f);
        ac.setPickupCharge(0.07f);
        
        when(kilometerChargeService.getKilometerCharge(2L)).thenReturn(km);        
        when(ageChargeService.getAgeCharge(2L)).thenReturn(ac);        

        ArrayList<Float> kmAgeCharges = repairValueService.getKilometerAgeCharge(2L, 2L, "PICKUP");

        assertThat(kmAgeCharges).containsExactly(0.05f, 0.07f);
    }

    @Test 
    void whenGetKmAgeChargeAndTypeVan_thenKmAgeChargeIsCorrect(){
        KilometerChargeEntity km = new KilometerChargeEntity();
        AgeChargeEntity ac  = new AgeChargeEntity();
        km.setKilometerChargeId(2L);
        ac.setAgeChargeId(2L);
        km.setVanCharge(0.05f);
        ac.setVanCharge(0.07f);

        when(kilometerChargeService.getKilometerCharge(2L)).thenReturn(km);        
        when(ageChargeService.getAgeCharge(2L)).thenReturn(ac);        

        ArrayList<Float> kmAgeCharges = repairValueService.getKilometerAgeCharge(2L, 2L, "VAN");

        assertThat(kmAgeCharges).containsExactly(0.05f, 0.07f);
    }

    @Test
    public void whenGetBonusValue_thenBonusValueIsCorrect() {
        BrandEntity brand = new BrandEntity();
        brand.setBrandId(1L);  
        brand.setDiscount(100);
        brand.setBrandName("TOYOTA");
        brand.setBonusAmount(5);


        
        when(brandService.getBrand(1L)).thenReturn(brand);


    
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
    
        List<Float> kilometerAgeCharge = new ArrayList<>();
        kilometerAgeCharge.add(0.05f);
        kilometerAgeCharge.add(0.03f);

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
        List<Float> kilometerAgeCharge = new ArrayList<>();
        kilometerAgeCharge.add(0.05f);
        kilometerAgeCharge.add(0.03f);
        int bonusDiscount = 7000; 
        boolean attentionDayDiscount = false;
        Long daysBetween = -10L; 

        float repairValue = repairValueService.getRepairValue(cr.getCostRecordId(), typeCost, repairDiscount, kilometerAgeCharge, bonusDiscount, attentionDayDiscount, daysBetween);

        assertThat(repairValue).isEqualTo(214438.0f);
    }
    
}