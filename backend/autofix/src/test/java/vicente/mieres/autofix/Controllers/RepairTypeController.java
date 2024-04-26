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

import vicente.mieres.autofix.Entities.RepairTypeCostEntity;
import vicente.mieres.autofix.Services.RepairTypeCostService;

@WebMvcTest(RepairTypeCostController.class)
public class RepairTypeController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RepairTypeCostService repairTypeCostService;

    @Test
    public void getRepairTypes_shouldReturnAll() throws Exception{
        RepairTypeCostEntity tc = new RepairTypeCostEntity();
        RepairTypeCostEntity tc2 =  new RepairTypeCostEntity();
        tc.setRepairTypeCostId(1L);
        tc2.setRepairTypeCostId(2L);

        List<RepairTypeCostEntity> list = new ArrayList<>();
        list.add(tc);
        list.add(tc2);

        given(repairTypeCostService.getRepairTypeCosts()).willReturn(list);
        mockMvc.perform(get("/repair/type/")).andExpect(status().isOk());

        }



}
