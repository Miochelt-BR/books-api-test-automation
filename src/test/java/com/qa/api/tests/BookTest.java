package com.qa.api.tests;
import com.qa.api.client.BookClient;
import com.qa.api.factory.BookFactory;
import com.qa.api.model.Book;
import com.qa.api.utils.Config;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.equalTo;


public class BookTest {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = Config.BASE_URL;
    }

    //post
    @Test
    public void shouldCreateBookSuccessfully() {
        // Arrange
        Book book = BookFactory.createValidBook();

        // Act + Assert
        BookClient.createBook(book)
                .then()
                .statusCode(200) // ou 201, dependendo da API
                .body("title", equalTo(book.getTitle()));
    }
    // Update
    @Test
    void shouldUpdateBookSuccessfully() {

        // Arrange
        Book book = BookFactory.createValidBook();

        BookClient.createBook(book)
                .then()
                .statusCode(200);

        book.setTitle("Updated Book Title");

        // Act + Assert
        BookClient.updateBook(book.getId(), book)
                .then()
                .statusCode(200); //  valida comportamento
    }

    @Test

    void shouldGetAllBooksSuccessfully() {

        BookClient.getAllBooks()
                .then()
                .statusCode(200);
    }

    @Test
    void shouldGetBookByIdSuccessfully() {

        BookClient.getBookById(1)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldDeleteBookSuccessfully() {

        Book book = BookFactory.createValidBook();

        BookClient.createBook(book)
                .then()
                .statusCode(200);

        BookClient.deleteBook(book.getId())
                .then()
                .statusCode(200);
    }
    // POST DADOS QUE NÃO EXISTEM
    @Test
    void shouldReturn400WhenCreatingInvalidBook() {

        Book invalidBook = new Book();

        BookClient.createBook(invalidBook)
                .then()
                .statusCode(400);
    }
    //GET COM DADOS NEGATIVOS
    @Test
    void shouldNotGetBookWithNegativeId() {

        BookClient.getBookById(-1)
                .then()
                .statusCode(404);
    }


}






