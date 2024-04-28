package vicente.mieres.autofix.Controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import vicente.mieres.autofix.Entities.BrandEntity;
import vicente.mieres.autofix.Services.BrandService;

@WebMvcTest(BrandController.class)
public class BrandControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BrandService brandService;

    @Test
    public void getBrands_ShouldReturnAllBrands() throws Exception{
        BrandEntity b1 = new BrandEntity();
        b1.setBrandId(1L);

        BrandEntity b2 = new BrandEntity();
        b2.setBrandId(2L);

        List<BrandEntity> blist = new ArrayList<>();
        blist.add(b1);
        blist.add(b2);

        given(brandService.getBrands()).willReturn(blist);
        mockMvc.perform(get("/brand/")).andExpect(status().isOk());
    }

    @Test 
    public void updateBrand_shouldUpdateCorrect() throws Exception{
        BrandEntity b = new BrandEntity();
        b.setBrandId(1L);
        b.setBrandName("TOYOTA");
        b.setDiscount(20000);

        given(brandService.updateBrand(Mockito.any(BrandEntity.class))).willReturn(b);

        BrandEntity b2 = new BrandEntity();
        b2.setBrandId(1L);
        b2.setBrandName("FORD");
        b2.setDiscount(20000);

        
        ObjectMapper objectMapper = new ObjectMapper();
        String createRepairJson = objectMapper.writeValueAsString(b2);

        mockMvc.perform(put("/brand/").contentType(MediaType.APPLICATION_JSON).content(createRepairJson)).andExpect(status().isOk());

    }

}
