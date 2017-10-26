package de.tuhrig.rsd.review.system;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;

import java.util.Collections;

@AllArgsConstructor
public final class JmsUtil {

    private static final int MAX_TRIES = 5000;
    private JmsTemplate jmsTemplate;

    private int getMessageCount(String queueName) {
        return jmsTemplate.browseSelected(queueName, "true = true", (s, qb) -> Collections.list(qb.getEnumeration()).size());
    }

    public void waitForAll(String queueName) {
        int i = 0;
        while (i <= MAX_TRIES) {
            if (getMessageCount(queueName) == 0) {
                return;
            }
            i++;
        }
    }
}
