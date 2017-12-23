package de.tuhrig.rsd.review.system.application;

import de.tuhrig.rsd.common.application.EventPublisher;
import de.tuhrig.rsd.review.system.domain.CreateReviewCommand;
import de.tuhrig.rsd.review.system.domain.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReviewSubmissionServiceTest {

    @Mock
    private ReviewRepository reviewRepositoryMock;

    @Mock
    private EventPublisher eventPublisherMock;

    @InjectMocks
    private ReviewSubmissionService reviewSubmissionService;

    @Test
    public void should_SaveReview() throws Exception {
        CreateReviewCommand createReviewCommand = createReviewCommand();
        reviewSubmissionService.createReview(createReviewCommand);
        verify(reviewRepositoryMock).save(any());
    }

    @Test
    public void should_SendSubmissionEvent() throws Exception {
        CreateReviewCommand createReviewCommand = createReviewCommand();
        reviewSubmissionService.createReview(createReviewCommand);
        verify(eventPublisherMock).publish(anyList());
    }

    private CreateReviewCommand createReviewCommand() {
        CreateReviewCommand createReviewCommand = new CreateReviewCommand();
        createReviewCommand.setSubject("My review");
        createReviewCommand.setContent("Some content");
        createReviewCommand.setRating(1);
        return createReviewCommand;
    }
}