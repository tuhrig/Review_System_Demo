package de.tuhrig.rsd.common.infrastructure.jms;

import de.tuhrig.rsd.common.domain.DomainEvent;
import de.tuhrig.rsd.common.utils.SubclassFinder;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class builds and returns a map which associates domain events to
 * with their simple class name. Note that we don't use the full class name
 * as the domain event implementations might resist in different packages in
 * each micro-service. So the complete class name (including packages) would
 * vary from system to system.
 *
 * Example:
 *
 *      {
 *          "MyDomainEventA": de.tuhrig.domain.MyDomainEventA.class,
 *          "MyDomainEventB": de.tuhrig.domain.MyDomainEventB.class
 *      }
 */
public class TypeIdMappingFactory {

    public static Map<String, Class<?>> getTypeIdMapping() {
        return SubclassFinder
                .findAllSubtypes(DomainEvent.class, "de/tuhrig/rsd") // All domain events under the root package!
                .stream()
                .collect(Collectors.toMap(clazz -> clazz.getSimpleName(), clazz -> clazz)); // simple name to class
    }
}