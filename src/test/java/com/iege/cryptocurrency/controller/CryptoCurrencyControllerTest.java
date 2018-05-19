package com.iege.cryptocurrency.controller;

import com.iege.cryptocurrency.entity.SecUserDetails;
import com.iege.cryptocurrency.entity.User;
import com.iege.cryptocurrency.repository.CryptoCurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CryptoCurrencyControllerTest {

    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;
    @InjectMocks
    private CryptoCurrencyController cryptoCurrencyController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cryptoCurrencyController).build();
        Authentication auth = new UsernamePasswordAuthenticationToken(new SecUserDetails(new User("1", "test", "test", "", "", true)), null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/cryptocurrency"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

}
