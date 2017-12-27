package de.tuhrig.rsd.common.utils;

import de.tuhrig.rsd.common.domain.DomainEvent;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SubclassFinderTest {

    @Test
    public void should_FindAllEvents() {
        List<Class> subtypes = SubclassFinder.findAllSubtypes(DomainEvent.class, "de/tuhrig/rsd");
        assertThat(subtypes).hasSize(2);
    }
}