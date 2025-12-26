package com.qa.api.client;
    /*
criar
*/


import com.qa.api.model.Book;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

    public class BookClient {

        private static final String BOOKS_ENDPOINT = "/Books";

        // CREATE
        public static Response createBook(Book book) {
            return given()
                    .contentType(ContentType.JSON)
                    .body(book)
                    .when()
                    .post(BOOKS_ENDPOINT);
        }
        public static Response updateBook(int id, Book book) {
            return given()
                    .contentType("application/json")
                    .body(book)
                    .when()
                    .put(BOOKS_ENDPOINT + "/" + id);
        }

    }

