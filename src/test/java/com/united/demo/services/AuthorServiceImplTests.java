package com.united.demo.services;

import com.united.demo.domain.Author;
import com.united.demo.repositories.AuthorRepository;
import com.united.demo.services.impl.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTests {

    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorServiceImpl underTest;

    @Test
    public void testFetchByIdSuccess() {
        Author a1 = Author.builder().id(1L).firstName("a1").lastName("a1").email("a1@email.com").build();
        when(repository.findById(a1.getId())).thenReturn(Optional.of(a1));

        final Optional<Author> result = underTest.findById(a1.getId());

        assertThat(result).isNotNull().isEqualTo(Optional.of(a1));
    }

    @Test
    public void testFetchByIdReturningEmptyOptional() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        final Optional<Author> result = underTest.findById(any());

        assertThat(result).isEmpty();
    }

    @Test
    public void testCreateNewAuthorSuccess() {
        Author a1 = Author.builder().id(1L).firstName("a1").lastName("a1").email("a1@email.com").build();
        when(repository.save(a1)).thenReturn(a1);

        final Author result = underTest.save(a1);

        assertThat(result).isNotNull().isEqualTo(a1);
    }

//    @Test // nije dobar test, jer se desi da se baci exception i ne dodje do verify
//    public void testSaveAuthorDidntHappen() {
//        when(repository.save(any())).thenThrow(new RuntimeException("something went wrong"));
//
//        final Author result = underTest.save(any());
//
//        verify(repository, never()).save(any(Author.class));
//    }

    @Test
    public void functionTest() {
        Function<Integer, String> intToString = Object::toString;
        Function<String, String> quote = s -> "'" + s + "'";

        Function<Integer, String> quoteIntToString = quote.compose(intToString);

        var dd = quoteIntToString.apply(5);
    }
}
