package com.mycaell.crudspring.infrastructure.converter.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConverterServiceImpl implements IConverterService {

    private final ModelMapper modelMapper;

    public ConverterServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <T> T convert(Object data, Class<T> type) {
        return modelMapper.map(data, type);
    }

    @Override
    public <S, T> List<T> convert(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

}
