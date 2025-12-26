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

    @Test
    public void CT01_deveCriarLivrosComSucesso() {
        // Arrange
        Book book = BookFactory.createValidBook();

        // Act + Assert
        BookClient.createBook(book)
                .then()
                .statusCode(200) // ou 201, dependendo da API
                .body("title", equalTo(book.getTitle()));
    }
}

