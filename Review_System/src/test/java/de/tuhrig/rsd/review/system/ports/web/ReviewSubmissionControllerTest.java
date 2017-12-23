package de.tuhrig.rsd.review.system.ports.web;

import de.tuhrig.rsd.review.system.application.ReviewSubmissionService;
import de.tuhrig.rsd.review.system.domain.CreateReviewCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ReviewSubmissionController.class
})
public class ReviewSubmissionControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ReviewSubmissionService reviewSubmissionServiceMock;

    @Autowired
    private ReviewSubmissionController reviewController;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(reviewController).build();
    }

    @Test
    public void should_ReceiveReview() throws Exception {
        mockMvc
                .perform(
                        post("/reviews")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"subject\": \"Test Review\", \"content\": \"Some review content.\", \"rating\": 5 }")
                )
                .andExpect(status().isOk());
    }

    @Test
    public void should_PassReviewToSubmissionService() throws Exception {
        mockMvc
                .perform(
                        post("/reviews")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"subject\": \"Test Review\", \"content\": \"Some review content.\", \"rating\": 5 }")
                );

        ArgumentCaptor<CreateReviewCommand> captor = ArgumentCaptor.forClass(CreateReviewCommand.class);
        Mockito.verify(reviewSubmissionServiceMock).createReview(captor.capture());
        CreateReviewCommand createReviewCommand = captor.getValue();

        assertThat(createReviewCommand.getSubject()).isEqualTo("Test Review");
        assertThat(createReviewCommand.getContent()).isEqualTo("Some review content.");
        assertThat(createReviewCommand.getRating()).isEqualTo(5);
    }
}