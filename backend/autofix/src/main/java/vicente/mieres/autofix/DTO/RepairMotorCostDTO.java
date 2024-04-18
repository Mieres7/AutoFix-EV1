package vicente.mieres.autofix.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairMotorCostDTO {
    
    private String repairType;
    private Long gasoline;
    private Long diesel;
    private Long hybrid;
    private Long electric;
    private float totalCost;

}
