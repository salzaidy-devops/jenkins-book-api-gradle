package com.example.books;

import com.example.books.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BookControllerTest {

    private final TestRestTemplate rest = new TestRestTemplate();

    @Test
    void listBooks_returnsSeedData() {
        ResponseEntity<Book[]> resp = rest.getForEntity("http://localhost:8081/api/books", Book[].class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().length).isGreaterThanOrEqualTo(2);
    }
}
