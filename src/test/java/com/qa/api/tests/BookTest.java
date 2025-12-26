package com.qa.api.tests;

import com.qa.api.client.BookClient;
import com.qa.api.factory.BookFactory;
import com.qa.api.model.Book;
import com.qa.api.utils.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BookTest {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = Config.BASE_URL;
    }

    //post
    @Test
    public void CT01_shouldCreateBookSuccessfully() {
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
        BookClient.createBook(book);

        book.setTitle("Updated Book Title");

        // Act + Assert
        BookClient.updateBook(book.getId(), book)
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Book Title"));
    }



}


