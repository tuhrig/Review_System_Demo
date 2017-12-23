package de.tuhrig.rsd.review.system.ports.web;

import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewFixtures;
import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import de.tuhrig.rsd.review.system.infrastructure.memory.ReviewStubRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ReviewListController.class,
        ReviewStubRepository.class
})
public class ReviewListControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewListController reviewListController;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(reviewListController).build();
    }

    @Test
    public void should_ReturnApprovedReviews() throws Exception {

        Review approvedReview = ReviewFixtures.anApprovedFiveStarSmartphoneReview();
        Review openedReview = ReviewFixtures.anInitialFiveStarSmartphoneReview();

        reviewRepository.save(approvedReview);
        reviewRepository.save(openedReview);

        mockMvc
                .perform(get("/reviews/approved"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].reviewId").value(approvedReview.getReviewId().toString()))
                .andExpect(jsonPath("$[0].subject").value(approvedReview.getSubject()))
                .andExpect(jsonPath("$[0].content").value(approvedReview.getContent()))
                .andExpect(jsonPath("$[0].rating").value(approvedReview.getRating().getRating()));
    }
}