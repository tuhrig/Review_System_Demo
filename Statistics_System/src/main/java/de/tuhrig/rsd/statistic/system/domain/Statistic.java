package de.tuhrig.rsd.statistic.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Jackson mapper default!
@EqualsAndHashCode(of = "statisticId")
@Entity
public class Statistic {

    @EmbeddedId
    private StatisticId statisticId;

    @JsonIgnore
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

    public Statistic(ReviewStatus reviewStatus) {
        this.statisticId = StatisticId.createNew();
        this.reviewStatus = reviewStatus;
    }
}
