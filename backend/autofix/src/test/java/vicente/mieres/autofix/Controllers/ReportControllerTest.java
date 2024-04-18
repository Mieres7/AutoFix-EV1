package vicente.mieres.autofix.Controllers;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import vicente.mieres.autofix.DTO.AverageTimeDTO;
import vicente.mieres.autofix.DTO.CostRecordDTO;
import vicente.mieres.autofix.DTO.RepairMotorCostDTO;
import vicente.mieres.autofix.DTO.RepairTypeCostDTO;
import vicente.mieres.autofix.Repositories.BrandRepository;
import vicente.mieres.autofix.Repositories.RepairRepository;
import vicente.mieres.autofix.Repositories.VehicleRepairRepository;
import vicente.mieres.autofix.Repositories.VehicleRepository;
import vicente.mieres.autofix.Services.ReportService;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReportService reportService;
    @MockBean
    private RepairRepository repairRepository;
    @MockBean
    private VehicleRepairRepository vehicleRepairRepository;
    @MockBean
    private VehicleRepository vehicleRepository;
    @MockBean
    private BrandRepository brandRepository;

    @Test
    public void getCostRecords_ShouldReturnAllCostRecords() throws Exception {
        
        
        List<CostRecordDTO> costRecordDTOs = new ArrayList<>();
        CostRecordDTO cr1 = new CostRecordDTO();
        CostRecordDTO cr2 = new CostRecordDTO();
        cr1.setBrandName("TOYOTA");
        cr1.setRepairCostOG(120000.0f);
        cr2.setBrandName("FORD");
        cr2.setRepairCostOG(130000.0f);
        costRecordDTOs.add(cr1);
        costRecordDTOs.add(cr2);
        
        // Configurando el mock para retornar los datos preparados
        given(reportService.costRecordModifier()).willReturn(costRecordDTOs);
    
        // Realizando la solicitud GET y verificando la respuesta
        mockMvc.perform(get("/report/cost/record"))  // Asegúrate de que la URL es correcta
            .andExpect(status().isOk());
    }

    @Test
    public void getRepairTypeCost_ShouldReturnCorrectData() throws Exception {

        List<RepairTypeCostDTO> rTypeCostDTOs = new ArrayList<>();
        RepairTypeCostDTO rt1 = new RepairTypeCostDTO();
        RepairTypeCostDTO rt2 = new RepairTypeCostDTO();
        rt1.setRepairType("Brake System Repairs");
        rt2.setRepairType("Cooling System Service");
        rTypeCostDTOs.add(rt1);
        rTypeCostDTOs.add(rt2);

        given(reportService.repairTypeCostModifier()).willReturn(rTypeCostDTOs);
        mockMvc.perform(get("/report/summary/type"))
            .andExpect(status().isOk());

    }

    @Test
    public void getAverageRepairCost_ShouldReturnCorrectData() throws Exception{
        
        List<AverageTimeDTO> averageTimeDTOs = new ArrayList<>();
        AverageTimeDTO at1 = new AverageTimeDTO();
        AverageTimeDTO at2 = new AverageTimeDTO();
        at1.setBrandName("TOYOTA");
        at1.setAverageRepairTime(50.0);
        at2.setBrandName("FORD");
        at2.setAverageRepairTime(45.0);
        averageTimeDTOs.add(at1);
        averageTimeDTOs.add(at2);
        
        given(reportService.averageTimeModifier()).willReturn(averageTimeDTOs);

        mockMvc.perform(get("/report/average"))
            .andExpect(status().isOk());
    }

    @Test
    public void getRepairMotorCost_ShouldReturnCorrectData() throws Exception {

        List<RepairMotorCostDTO> repairMotorCostDTOs = new ArrayList<>();
        RepairMotorCostDTO mc1 = new RepairMotorCostDTO();
        RepairMotorCostDTO mc2 = new RepairMotorCostDTO();
        mc1.setRepairType("Brake System Repairs");
        mc1.setDiesel(1L);
        mc2.setRepairType("Cooling System Service");
        mc2.setDiesel(1L);
        repairMotorCostDTOs.add(mc1);
        repairMotorCostDTOs.add(mc2);

        given(reportService.repairMotorCostModifier()).willReturn(repairMotorCostDTOs);

        mockMvc.perform(get("/report/summary/motor"))
            .andExpect(status().isOk());

    }

}
