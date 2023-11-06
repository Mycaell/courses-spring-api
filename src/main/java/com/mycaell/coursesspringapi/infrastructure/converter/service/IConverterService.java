package com.mycaell.coursesspringapi.infrastructure.converter.service;

import java.util.List;

public interface IConverterService {
    <T> T convert(Object data, Class<T> type);

    <S, T> List<T> convert(List<S> source, Class<T> targetClass);
}
