package com.gnurt.fashion_ecommerce.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class MapperUtils {
	private MapperUtils() {
	}

	public static final ModelMapper MODEL_MAPPER = new ModelMapper();

	public static <T, R> List<R> toModels(List<T> lst, Class<R> modelClass) {
		return lst.stream().map(item -> MODEL_MAPPER.map(item, modelClass)).collect(Collectors.toList());
	}

	public static <T, R> List<R> toEntities(List<T> lst, Class<R> entityClass) {
		return lst.stream().map(item -> MODEL_MAPPER.map(item, entityClass)).collect(Collectors.toList());
	}

	public static <T, R> R toModel(T item, Class<R> modelClass) {
		return MODEL_MAPPER.map(item, modelClass);
	}

	public static <T, R> R toEntity(T item, Class<R> entityClass) {
		return MODEL_MAPPER.map(item, entityClass);
	}
}
