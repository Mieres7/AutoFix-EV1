package vicente.mieres.autofix.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateRepair {
    
    private Long vehicleId;
    private boolean bonus;
    private int repairType;
}
