package com.jpcchaves.parkinglotapi.uitls.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
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

    public <O, D> List<D> parseListObjects(List<O> origin,
                                           Class<D> destination) {
        return origin
                .stream()
                .map(o -> mapper.map(o, destination))
                .collect(Collectors.toList());
    }

    public <O, D> Set<D> parseSetObjects(Set<O> origin,
                                         Class<D> destination) {
        return origin
                .stream()
                .map(o -> mapper.map(o, destination))
                .collect(Collectors.toSet());
    }
}
