package de.tuhrig.rsd.review.system.application;

import de.tuhrig.rsd.review.system.domain.Review;
import de.tuhrig.rsd.review.system.domain.ReviewFixtures;
import de.tuhrig.rsd.review.system.infrastructure.database.PersistenceConfig;
import de.tuhrig.rsd.review.system.infrastructure.database.ReviewRepositoryAdapter;
import de.tuhrig.rsd.review.system.ports.event.ReviewSubmittedEventSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        PersistenceConfig.class,
        ReviewRepositoryAdapter.class,
        ReviewSubmissionService.class
})
public class ReviewSubmissionServiceJpaTransactionTest {

    @MockBean
    private ReviewSubmittedEventSender reviewSubmittedEventSenderMock;

    @Autowired
    private ReviewRepositoryAdapter reviewRepositoryAdapter;

    @Autowired
    private ReviewSubmissionService reviewSubmissionService;

    @Test
    public void should_NotSaveReview_IfEventSendingFails() {

        Review review = ReviewFixtures.anInitialFiveStarSmartphoneReview();
        doThrow(new RuntimeException()).when(reviewSubmittedEventSenderMock).reviewSubmitted(review);

        try {
            reviewSubmissionService.submit(review);
        } catch (RuntimeException e){
            // we ignore this exception as it's the one we've mocked before...
        }
        Review loaded = reviewRepositoryAdapter.find(review.getReviewId());
        assertThat(loaded).isNull(); // Nothing must be saved as the transaction was rolled back!
    }
}