package ru.stepup.online;

import org.junit.jupiter.api.Test;

public class TestModuls {

    @Test
    public void test1(){
        System.out.println(1);
    }


    /*
    @Test
    public void givenSaveBasicInfoStep1_whenCorrectInput_thenSuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/saveBasicInfoStep1")
                        .accept(MediaType.TEXT_HTML)
                        .param("name", "test123")
                        .param("password", "pass"))
                .andExpect(view().name("success"))
                .andExpect(status().isOk())
                .andDo(print());
    }*/
}
