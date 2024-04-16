package vicente.mieres.autofix.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CostRecordDto {
        private Long costRecordId;
        private Double repairCostOg;
        private Double repairCost;
        private String brandName;
        private String vehicleModel;
        private String registration;
        private Double kilometerCharge;
        private Double ageCharge;
        private Double lateCharge;
        private Double repairsDiscount;
        private Double attentioinDayDiscount;
}
