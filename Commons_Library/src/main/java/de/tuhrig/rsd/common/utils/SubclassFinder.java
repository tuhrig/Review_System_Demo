package de.tuhrig.rsd.common.utils;

import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SubclassFinder {

    public static List<Class> findAllSubtypes(Class clazz, String packageName) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(clazz));
        return provider
                .findCandidateComponents(packageName) // e.g. org/my/project
                .stream()
                .map(BeanDefinition::getBeanClassName)
                .map(SubclassFinder::forName)
                .collect(toList());
    }

    @SneakyThrows
    private static Class forName(String className) {
        return Class.forName(className);
    }
}
