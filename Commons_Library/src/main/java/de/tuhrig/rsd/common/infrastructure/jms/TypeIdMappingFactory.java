package de.tuhrig.rsd.common.infrastructure.jms;

import de.tuhrig.rsd.common.domain.DomainEvent;
import de.tuhrig.rsd.common.utils.SubclassFinder;

import java.util.Map;
import java.util.stream.Collectors;

public class TypeIdMappingFactory {

    public static Map<String, Class<?>> getTypeIdMapping() {
        return SubclassFinder
                .findAllSubtypes(DomainEvent.class, "de/tuhrig/rsd")
                .stream()
                .collect(Collectors.toMap((Class clazz) -> getClassName(clazz), clazz -> clazz));
    }

    private static String getClassName(Class clazz) {
        String className = clazz.getSimpleName();
        className = camelCaseToUpperCase(className);
        return className.toUpperCase();
    }

    private static String camelCaseToUpperCase(String string) {
        String[] parts = string.split("(?<=[a-z])(?=[A-Z])");
        return String.join("_", parts);
    }
}
