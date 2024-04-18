package vicente.mieres.autofix.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostRecordDTO {

    private Long costRecordId;
    private String brandName;
    private String vehicleModel;
    private String registration;
    private float repairCost;
    private float kilometerCharge;
    private float ageCharge;
    private float lateCharge;
    private float repairDiscount;
    private float attentionDayDiscount;
    private float bonusDiscount;
    private float repairCostOG;

    
}
