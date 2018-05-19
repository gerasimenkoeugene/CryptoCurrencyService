package com.iege.cryptocurrency.controller;

import com.iege.cryptocurrency.entity.CryptoCurrency;
import com.iege.cryptocurrency.entity.Monitoring;
import com.iege.cryptocurrency.entity.SecUserDetails;
import com.iege.cryptocurrency.entity.User;
import com.iege.cryptocurrency.enums.MonitoringCondition;
import com.iege.cryptocurrency.repository.MonitoringRepository;
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

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MonitoringControllerTest {

    private final String MONITORING_ID = "1";
    private final String USER_ID = "5ac8840f8728bc16a4f7149f";
    @Mock
    private MonitoringRepository monitoringRepository;
    @InjectMocks
    private MonitoringController monitoringController;
    private MockMvc mockMvc;
    private Monitoring monitoring;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(monitoringController).build();
        Authentication auth = new UsernamePasswordAuthenticationToken(new SecUserDetails(new User("1", "test", "test", "", "", true)), null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        CryptoCurrency btc = new CryptoCurrency("bitcoin", "Bitcoin", "BTC", "1", "9920.26", "1", 1525518572L, 0.6, 0.6, 0.6);
        monitoring = new Monitoring(MONITORING_ID, btc, "1", "test@mail.ru", MonitoringCondition.MORE_THEN_USD, 100.0, true);
        when(monitoringRepository.findById(MONITORING_ID)).thenReturn(monitoring);
        when(monitoringRepository.findListByIdUser(USER_ID)).thenReturn(Collections.singletonList(monitoring));
    }

    @Test
    public void testGetAllUserMonitoring() throws Exception {
        mockMvc.perform(get("/monitorings/user?idUser=" + USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/monitorings?monitoringId=" + MONITORING_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testDeleteMonitoring() throws Exception {
        mockMvc.perform(delete("/monitorings?monitoringId=" + MONITORING_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeactivateMonitoring() throws Exception {
        mockMvc.perform(delete("/monitorings/deactivate?monitoringId=" + MONITORING_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeactivateUserMonitoring() throws Exception {
        mockMvc.perform(delete("/monitorings/user/deactivate?idUser=" + USER_ID))
                .andExpect(status().isOk());
    }
}
