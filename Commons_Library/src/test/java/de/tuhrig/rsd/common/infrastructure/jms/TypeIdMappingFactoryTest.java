package de.tuhrig.rsd.common.infrastructure.jms;

import de.tuhrig.rsd.common.domain.MyEventA;
import de.tuhrig.rsd.common.domain.MyEventB;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TypeIdMappingFactoryTest {

    @Test
    public void should_ReturnIdTypeMapping_ForAllEvents() {
        Map<String, Class<?>> mapping = TypeIdMappingFactory.getTypeIdMapping();
        assertThat(mapping).isEqualTo(expectedMappings());
    }

    private HashMap<String, Class<?>> expectedMappings() {
        HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("MyEventA", MyEventA.class);
        typeIdMappings.put("MyEventB", MyEventB.class);
        return typeIdMappings;
    }
}