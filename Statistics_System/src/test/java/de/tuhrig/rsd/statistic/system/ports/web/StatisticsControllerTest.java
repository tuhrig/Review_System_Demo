package de.tuhrig.rsd.statistic.system.ports.web;

import de.tuhrig.rsd.statistic.system.domain.Statistic;
import de.tuhrig.rsd.statistic.system.domain.StatisticFixtures;
import de.tuhrig.rsd.statistic.system.domain.StatisticRepository;
import de.tuhrig.rsd.statistic.system.infrastructure.memory.StatisticStubRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        StatisticsController.class,
        StatisticStubRepository.class
})
public class StatisticsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private StatisticsController reviewListController;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(reviewListController).build();
    }

    @Test
    public void shouldReturn_ApprovedStatistics() throws Exception {
        Statistic approvedStatistic = StatisticFixtures.anApprovedStatistic();
        statisticRepository.save(approvedStatistic);

        mockMvc
                .perform(get("/statistics/approved"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").value("1"));
    }

    @Test
    public void shouldReturn_RejectedStatistics() throws Exception {
        Statistic rejectedStatistic = StatisticFixtures.aRejectedStatistic();
        statisticRepository.save(rejectedStatistic);

        mockMvc
                .perform(get("/statistics/rejected"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").value("1"));
    }

    @Test
    public void shouldReturn_SubmittedStatistics() throws Exception {
        Statistic submittedStatistic = StatisticFixtures.aSubmittedStatistic();
        statisticRepository.save(submittedStatistic);

        mockMvc
                .perform(get("/statistics/submitted"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").value("1"));
    }
}