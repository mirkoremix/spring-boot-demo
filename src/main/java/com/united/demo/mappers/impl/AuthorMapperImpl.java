package com.united.demo.mappers.impl;

import com.united.demo.domain.Author;
import com.united.demo.domain.dto.AuthorDto;
import com.united.demo.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<Author, AuthorDto> {

    private ModelMapper mapper;

    public AuthorMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public AuthorDto mapTo(Author author) {
        return mapper.map(author, AuthorDto.class);
    }

    @Override
    public Author mapFrom(AuthorDto authorDto) {
        return mapper.map(authorDto, Author.class);
    }
}
