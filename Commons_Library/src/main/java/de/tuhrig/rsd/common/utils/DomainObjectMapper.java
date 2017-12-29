package de.tuhrig.rsd.common.utils;

public interface DomainObjectMapper<D, T> {
    D toDomain(T object);
    T fromDomain(D object);
}