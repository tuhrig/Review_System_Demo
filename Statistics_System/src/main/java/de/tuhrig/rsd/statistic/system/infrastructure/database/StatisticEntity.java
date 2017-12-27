package de.tuhrig.rsd.statistic.system.infrastructure.database;

import de.tuhrig.rsd.statistic.system.domain.review.ReviewStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "statistic")
public class StatisticEntity {

    @Id
    private String statisticId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewStatus reviewStatus;

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