package de.tuhrig.rsd.review.system.infrastructure.database;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "message")
@EntityListeners(AuditingEntityListener.class)
public class MessageEntity {

    @Id
    private String id;
    private String type;
    private String content;
    private String listener;
    private int executionCount;
    private boolean success;
    private String errorMessage;

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
