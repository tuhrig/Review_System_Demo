package de.tuhrig.rsd.review.system.infrastructure.database;

import de.tuhrig.rsd.review.system.domain.ReviewStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "review")
@EntityListeners(AuditingEntityListener.class)
class ReviewEntity {

    @Id
    private String reviewId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewStatus reviewStatus;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int rating;

    @CreatedDate
    @Column(updatable = false)
    // This field is private as it should only be used for auditing. Don't use it for
    // any application or business logic.
    private Date createdDate;

    @LastModifiedDate
    // This field is private as it should only be used for auditing. Don't use it for
    // any application or business logic.
    private Date lastModifiedDate;
}
