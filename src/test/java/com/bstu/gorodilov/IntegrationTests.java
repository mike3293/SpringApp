package com.bstu.gorodilov;

import com.bstu.gorodilov.model.Role;
import com.bstu.gorodilov.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
public class IntegrationTests {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private MockMvc mockMvc;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }



    @Test
    void testSetJwtTokenProviderSTUDENT() throws Exception {
        setUp();
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_STUDENT"));
        String token = jwtTokenProvider.createToken("student1234", roles);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students/getUserMarks?username=student1234&subject=Java").header("Authorization", "Bearer_" + token))
                .andExpect(status().isOk());
    }

    @Test
    void testSetJwtTokenProviderTEACHER() throws Exception {
        setUp();
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_TEACHER"));
        String token = jwtTokenProvider.createToken("teacher1234", roles);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/teachers/getStudents?faculty=FIT&subjectName=Java&userCourse=1&userGroup=1&userName=teacher1234").header("Authorization", "Bearer_" + token))
                .andExpect(status().isOk());
    }
}
