package com.bonacamp.ecasystem;

import com.bonacamp.ecasystem.batch.controller.BatchController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = BatchController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class BatchControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHeath() throws Exception {
        String message = "OK";

        mvc.perform(get("/api/v1/batch/health"))
                .andExpect(status().isOk())
                .andExpect(content().string(message));
    }
}
