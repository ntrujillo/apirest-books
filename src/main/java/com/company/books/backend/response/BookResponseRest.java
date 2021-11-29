package com.company.books.backend.response;

import lombok.Data;

@Data
public class BookResponseRest extends ResponseRest {

    private BookResponse bookResponse = new BookResponse();

}
