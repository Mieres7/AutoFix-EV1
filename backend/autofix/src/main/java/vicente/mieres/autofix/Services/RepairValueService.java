package vicente.mieres.autofix.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.Entities.AgeChargeEntity;
import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Entities.KilometerChargeEntity;
import vicente.mieres.autofix.Entities.RepairDiscountEntity;
import vicente.mieres.autofix.Entities.RepairTypeCostEntity;

@Service
public class RepairValueService {

    @Autowired
    RepairTypeCostService repairTypeCostService;
    @Autowired
    RepairDiscountService repairDiscountService;
    @Autowired
    KilometerChargeService kilometerChargeService;
    @Autowired
    AgeChargeService ageChargeService;
    @Autowired
    BrandService brandService;
    @Autowired
    CostRecordService costRecordService;

    // aqui van todos los metodos para calcular el valor de la reparacion

    public int getTypeCostValue(Long typeCostId, String motorType){

        RepairTypeCostEntity typeCost = repairTypeCostService.getRepairTypeCost(typeCostId);
        int typeCostValue = 0;

        switch (motorType) {
            case "GASOLINE":
                typeCostValue = typeCost.getGasolineCost();    
                break;
            case "DIESEL":
                typeCostValue = typeCost.getDieselCost();
                break;
            case "HYBRID":
                typeCostValue = typeCost.getHybridCost();
                break;
            case "ELECTRIC":
                typeCostValue = typeCost.getElectricCost();
                break;
        }
        return typeCostValue;
    }

    public float getRepairDiscount(Long repairDiscountId, String motorType){

        RepairDiscountEntity repairDiscount = repairDiscountService.getRepairDiscount(repairDiscountId);
        float repairDiscountValue = 0;

        switch (motorType) {
            case "GASOLINE":
                repairDiscountValue = repairDiscount.getGasolineCost();    
                break;
            case "DIESEL":
                repairDiscountValue = repairDiscount.getDieselCost();
                break;
            case "HYBRID":
                repairDiscountValue = repairDiscount.getHybridCost();
                break;
            case "ELECTRIC":
                repairDiscountValue = repairDiscount.getElectricCost();
                break;
        }

        return repairDiscountValue;
    }

    public ArrayList<Float> getKilometerAgeCharge(Long kilometerChargeId, Long ageChargeId,String vehicleType){

        KilometerChargeEntity kilometerCharge = kilometerChargeService.getKilometerCharge(kilometerChargeId);
        AgeChargeEntity ageCharge = ageChargeService.getAgeCharge(ageChargeId);

        ArrayList<Float> kilometerAgeCharge = new ArrayList<>();

        switch (vehicleType) {
            case "SEDAN":
                kilometerAgeCharge.add(kilometerCharge.getSedanCharge());
                kilometerAgeCharge.add(ageCharge.getSedanCharge());
                break;
            case "HATCHBACK":
                kilometerAgeCharge.add(kilometerCharge.getHatchbackCharge());
                kilometerAgeCharge.add(ageCharge.getHatchbackCharge());
                break;
            case "SUV":
                kilometerAgeCharge.add(kilometerCharge.getSuvCharge());
                kilometerAgeCharge.add(ageCharge.getSuvCharge());
                break;
            case "PICKUP":
                kilometerAgeCharge.add(kilometerCharge.getPickupCharge());
                kilometerAgeCharge.add(ageCharge.getPickupCharge());
                break;
            case "VAN":
                kilometerAgeCharge.add(kilometerCharge.getVanCharge());
                kilometerAgeCharge.add(ageCharge.getVanCharge());
                break;
            
        }
        return kilometerAgeCharge;
    }

    public int getBonusValue(Long brandId){
        
        String [] brands = {"TOYOTA", "FORD", "HYUNDAI", "HONDA"};
        List<String> brandList = Arrays.asList(brands);
        BrandEntity brand = brandService.getBrand(brandId);
        System.out.println(brand.getBrandName());
        if (brandList.contains(brand.getBrandName())) {
            if(brand.getBonusAmount() > 0){
                brand.setBonusAmount(brand.getBonusAmount() -1);
                brandService.updateBrand(brand);
                return brand.getDiscount();
            }
        }
        return 0;
    }

    public float getRepairValue(Long costRecordId, int typeCost, float repairDiscount, List<Float> kilometerAgeCharge, int bonusDiscount, boolean attentionDayDiscount, Long daysBetween) {

        float repairValue = 0f;
        
        float repair = (float) typeCost;
        float kmCharge = kilometerAgeCharge.get(0);
        float ageCharge = kilometerAgeCharge.get(1);

        // Charges 
        float kilometerChargeValue = repair * kmCharge;
        float ageChargeValue = repair * ageCharge;

        if (daysBetween < 0) {
            daysBetween = -daysBetween;
        }
        
        float daysBetweenValue = daysBetween * typeCost * 0.05f;
        
       float charges = kilometerChargeValue + ageChargeValue + daysBetweenValue;

        // Discounts
        float repairDiscountValue = repair * repairDiscount;
        float attentionDayDiscountValue = 0f;
        if (attentionDayDiscount) 
            attentionDayDiscountValue = repair * 0.1f;
        
        float discounts = repairDiscountValue + attentionDayDiscountValue + (float) bonusDiscount;

        repairValue = (repair + charges - discounts) * 1.19f;

        costRecordService.setCostRecord(costRecordId, (float)typeCost,repairValue, kilometerChargeValue, ageChargeValue, daysBetweenValue, repairDiscountValue, attentionDayDiscountValue, bonusDiscount);

        repairValue = Math.round(repairValue);

        return repairValue; 
    }


}


