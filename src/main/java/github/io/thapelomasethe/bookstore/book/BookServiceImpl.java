/*
 * Copyright 2023 Thapelo Masethe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github.io.thapelomasethe.bookstore.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Thapelo Masethe
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookDto saveBook(BookDto book) {
        final BookEntity bookEntity = bookRepository.save(BookMapper.INSTANCE.toEntity(book));
        return BookMapper.INSTANCE.toDto(bookEntity);
    }

    @Override
    public List<BookDto> saveBooks(List<BookDto> books) {
        final List<BookEntity> bookEntityList = books.stream().map(BookMapper.INSTANCE::toEntity).toList();
        final List<BookEntity> savedBooks = bookRepository.saveAll(bookEntityList);
        return savedBooks.stream().map(BookMapper.INSTANCE::toDto).toList();
    }

    @Override
    public boolean deleteBook(String bookId) {
        final BookEntity bookEntity = bookRepository.findBookEntityByBookId(bookId);

        if (Objects.nonNull(bookId)) {
            bookRepository.delete(bookEntity);
            return true;
        }
        return false;
    }
}
