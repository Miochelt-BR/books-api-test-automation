package com.qa.api.factory;

import com.github.javafaker.Faker;
import com.qa.api.model.Book;

import java.time.LocalDateTime;

/*
 Centraliza a criação de dados

evita dados criticos

Facilita a manutenção
Utilização de boas praticas 
*/

public class BookFactory {
    private static final Faker faker= new Faker();

    public static Book criacaoValidacaoDoBook(){
        Book book =new Book();
        book.setId(faker.number().numberBetween(1,9999));
        book.setTitle(faker.book().title());
        book.setDescription(faker.lorem().sentence());
        book.setPageCount(faker.number().numberBetween(50,500));
        book.setExcerpt(faker.lorem().paragraph());
        book.setPublishDate(LocalDateTime.now().toString());
        return book;

    }
}
