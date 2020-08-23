package com.vtb.app;

import com.vtb.app.bookstore.GetBookByIdRequest;
import com.vtb.app.bookstore.GetAllBooksResponse;
import com.vtb.app.bookstore.GetBookByIdResponse;
import com.vtb.app.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BookEndPoint {
    private static final String NAMESPACE_URI = "http://www.vtb.com/app/bookstore";

    private BookService bookService;

    @Autowired
    public BookEndPoint(BookService bookService) {
        this.bookService = bookService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookByIdRequest")
    @ResponsePayload
    public GetBookByIdResponse getBook(@RequestPayload GetBookByIdRequest request){
        GetBookByIdResponse response = new GetBookByIdResponse();
        response.setBook(bookService.findBookById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBooksRequest")
    @ResponsePayload
    public GetAllBooksResponse getBooks(){
        GetAllBooksResponse response = new GetAllBooksResponse();
        response.getBook().addAll(bookService.findAllBooks());
        return response;
    }


}
