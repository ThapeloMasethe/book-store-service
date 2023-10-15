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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.List;

/**
 * @author Thapelo Masethe
 */
@Slf4j
@RestController()
@RequestMapping("book-store/")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(path = "v1/save-books", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebAsyncTask<ResponseEntity<List<BookDto>>> saveBooksV1(final List<BookDto> books) {
        final WebAsyncTask<ResponseEntity<List<BookDto>>> webAsyncTask = new WebAsyncTask<>(() -> {
            try {
                return ResponseEntity.ok(bookService.saveBooks(books));
            } catch (HttpClientErrorException e) {
                return ResponseEntity.status(e.getStatusCode()).build();
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        });

        webAsyncTask.onCompletion(() -> {
        });

        return webAsyncTask;
    }
}
