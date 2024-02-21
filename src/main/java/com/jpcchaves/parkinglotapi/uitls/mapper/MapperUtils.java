package com.jpcchaves.parkinglotapi.uitls.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class MapperUtils {
    private final ModelMapper mapper;

    public MapperUtils(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public <O, D> D parseObject(O origin,
                                Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public <O, D, T extends Collection<D>> T parseObjectsCollection(Collection<O> origin,
                                                                    Class<D> destination,
                                                                    Class<T> collectionType) {
        return origin
                .stream()
                .map(o -> mapper.map(o, destination))
                .collect(Collectors.toCollection(() -> instantiateCollection(collectionType)));
    }

    private <T extends Collection<D>, D> T instantiateCollection(Class<T> collectionType) {
        try {
            return collectionType.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate collection type", e);
        }
    }
}
