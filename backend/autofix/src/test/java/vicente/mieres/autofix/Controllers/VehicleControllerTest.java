package vicente.mieres.autofix.Controllers;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import vicente.mieres.autofix.DTO.CreateVehicle;
import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Services.VehicleService;


@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VehicleService vehicleService;

    @Test
    public void getVehicle_shouldReturnVehicle() throws Exception{

        VehicleEntity vehicle = new VehicleEntity(1L, "ABCD12", "Corolla", "SEDAN", "2024", "ELECTRIC", 4, 5000, 0, 1l);

        given(vehicleService.getVehicle(1L)).willReturn(vehicle);
        mockMvc.perform(get("/vehicle/{vehicleId}", 1L))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.registration", is("ABCD12")));

    }

    @Test
    public void getVehicles_shouldReturnAll() throws Exception{
        VehicleEntity v = new VehicleEntity();
        VehicleEntity v2 = new VehicleEntity();
        v.setVehicleId(1L);
        v2.setVehicleId(2L);
        List<VehicleEntity> list =  new ArrayList<>();
        list.add(v);
        list.add(v2);

        given(vehicleService.getVehicles()).willReturn((ArrayList<VehicleEntity>)list);
        mockMvc.perform(get("/vehicle/")).andExpect(status().isOk());
    }
    
    @Test 
    public void saveVehicle_ShouldSave() throws Exception{
        VehicleEntity v = new VehicleEntity();
        v.setVehicleId(1L);

        CreateVehicle cr = new CreateVehicle();
        cr.setBrandId(1L);

        ObjectMapper om = new ObjectMapper();
        String crString = om.writeValueAsString(cr);

        given(vehicleService.saveVehicle(cr)).willReturn(v);
        mockMvc.perform(post("/vehicle/").contentType(MediaType.APPLICATION_JSON).content(crString)).andExpect(status().isOk());

    }

}