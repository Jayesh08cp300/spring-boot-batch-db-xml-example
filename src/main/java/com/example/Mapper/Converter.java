package com.example.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Converter<Dto, Entity> {

	private final Function<Dto, Entity> toEntity;
	private final Function<Entity, Dto> toDto;

	public Converter(final Function<Dto, Entity> toEntity, final Function<Entity, Dto> toDto) {
		this.toEntity = toEntity;
		this.toDto = toDto;
	}

	public final Entity convertToEntity(final Dto dto) {
		return toEntity.apply(dto);
	}

	public final Dto convertToDto(final Entity entity) {
		return toDto.apply(entity);
	}

	public final List<Entity> convertToEntities(final Collection<Dto> dtos) {
		return dtos.stream()
				.map(this::convertToEntity)
				.collect(Collectors.toList());
	}

	public final List<Dto> convertToDtos(final Collection<Entity> entities) {
		return entities.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
}