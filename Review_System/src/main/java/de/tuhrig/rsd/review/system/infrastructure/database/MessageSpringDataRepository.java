package de.tuhrig.rsd.review.system.infrastructure.database;

import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository.
 *
 * @see "http://docs.spring.io/spring-data/jpa/docs/current/reference/html"
 */
public interface MessageSpringDataRepository extends CrudRepository<MessageEntity, Integer> {

    MessageEntity findFirstByListenerAndSuccessAndExecutionCountLessThan(String listener, boolean success, int executionCountLimit);
}