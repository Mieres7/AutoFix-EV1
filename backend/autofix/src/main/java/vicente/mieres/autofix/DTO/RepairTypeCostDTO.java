package vicente.mieres.autofix.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairTypeCostDTO {
    private String repairType;
    private Long sedans;
    private Long hatchbacks;
    private Long suvs;
    private Long pickups;
    private Long vans;
    private float totalCost;
}
