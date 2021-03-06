package de.tuhrig.rsd.review.system.infrastructure.database;

import de.tuhrig.rsd.review.system.domain.ReviewStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        PersistenceConfig.class
})
@TestPropertySource({
        "classpath:application.properties"
})
@Transactional
public class ReviewSpringDataRepositoryTest {

    @Autowired
    private ReviewSpringDataRepository springDataRepository;

    @Test
    public void should_SaveAuditingColumns() {
        ReviewEntity entity = new ReviewEntity();
        entity.setReviewId("MY_ID");
        entity.setReviewStatus(ReviewStatus.REJECTED);

        springDataRepository.save(entity);

        ReviewEntity loaded = springDataRepository.findOne("MY_ID");

        assertThat(loaded.getCreatedDate()).isNotNull();
        assertThat(loaded.getLastModifiedDate()).isNotNull();
    }
}