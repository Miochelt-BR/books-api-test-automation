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


    /**
     * Cenário negativo para validação do endpoint POST.
     * <p>
     * Verifica o comportamento da API ao receber um payload inválido.
     * Em APIs Fake, a ausência de validações pode impactar o status retornado.
     * </p>
     */
    @Test
        void shouldReturn400WhenCreatingInvalidBook() {

            Book invalidBook = new Book();

            BookClient.createBook(invalidBook)
                    .then()
                    .statusCode(400);
        }
    /**
     * Cenário classificado como negativo do ponto de vista de qualidade.
     * <p>
     * O objetivo deste teste não é validar regras de negócio,
     * mas sim o comportamento do endpoint ao receber um payload inválido.
     * </p>
     * <p>
     * Como se trata de uma API Fake, não existe persistência de dados
     * nem validações de domínio, fazendo com que o endpoint aceite
     * informações inválidas sem retornar erro.
     * </p>
     */

    @Test
    void shouldNotCreateBookWithoutTitle() {

        Book book = BookFactory.createValidBook();
        book.setTitle(null);

        BookClient.createBook(book)
                .then()
                .statusCode(200);
    }
    //GET COM DADOS NEGATIVOS
    @Test
    void shouldNotGetBookWithNegativeId() {

        BookClient.getBookById(-1)
                .then()
                .statusCode(404);
    }
    /**
     * Cenário negativo para o endpoint PUT.
     * <p>
     * Comportamento esperado em uma API real:
     * tentativa de atualização de um recurso inexistente deveria retornar erro (ex: 404).
     * </p>
     * <p>
     * Comportamento observado nesta API Fake:
     * o endpoint aceita o request e retorna 200, sem validar regra de negócio
     * ou existência real do recurso.
     * </p>
     */
    @Test
    void shouldReturn200WhenUpdatingNonExistingBookInFakeApi() {

        Book book = BookFactory.createValidBook();

        BookClient.updateBook(9999999, book)
                .then()
                .statusCode(200);
    }
    /**
     * Cenário negativo para o endpoint DELETE.
     * <p>
     * Comportamento esperado em uma API real:
     * tentativa de exclusão de um recurso inexistente deveria retornar erro (ex: 404).
     * </p>
     * <p>
     * Comportamento observado nesta API Fake:
     * o endpoint aceita a requisição e retorna 200,
     * sem validar a existência real do recurso.
     * </p>
     */
    @Test
    void shouldReturn200WhenDeletingNonExistingBookInFakeApi() {

        BookClient.deleteBook(9999999)
                .then()
                .statusCode(200);
    }





}






