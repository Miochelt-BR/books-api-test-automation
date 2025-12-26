package com.qa.api.model;

import lombok.Data;
/*
 O que o Lombok faz aqui?

Com apenas @Data, ele gera automaticamente:

getters

setters

toString()

equals() e hashCode()
*/

@Data
public class Book {
    private int id;
    private String title;
    private String description;
    private int pageCount;
    private String excerpt;
    private String publishDate;

}
