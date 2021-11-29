package com.company.books.backend.service;

import com.company.books.backend.model.Book;
import com.company.books.backend.model.dao.BookDao;
import com.company.books.backend.response.BookResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<BookResponseRest> getBooks() {
        log.info("start getBooks");
        BookResponseRest response = new BookResponseRest();
        try {
            List<Book> book = (List<Book>) bookDao.findAll();
            response.getBookResponse().setBook(book);
            response.setMetadata("OK", "00", "Respuesta Exitosa");
        } catch (Exception e) {
            response.setMetadata("Error", "-1", "Error al consultar libros");
            log.error("Error al consultar libros", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<BookResponseRest> getBookById(Long id) {
        log.info("Inicio metodo buscarPorId)");

        BookResponseRest response = new BookResponseRest();
        List<Book> list = new ArrayList<>();

        try {

            Optional<Book> book = bookDao.findById(id);

            if (book.isPresent()) {
                list.add(book.get());
                response.getBookResponse().setBook(list);
            } else {
                log.error("Error en consultar libro");
                response.setMetadata("Respuesta nok", "-1", "Libro no encontrado");
                return new ResponseEntity<BookResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error en consultar libro");
            response.setMetadata("Respuesta nok", "-1", "Error al consultar libro");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK); //devuelve 200
    }

    @Override
    @Transactional
    public ResponseEntity<BookResponseRest> create(Book book) {
        log.info("Inicio metodo crear libro");

        BookResponseRest response = new BookResponseRest();
        List<Book> list = new ArrayList<>();

        try {

            Book libroGuardada = bookDao.save(book);

            if( libroGuardada != null) {
                list.add(libroGuardada);
                response.getBookResponse().setBook(list);
            } else {
                log.error("Error en grabar libro");
                response.setMetadata("Respuesta nok", "-1", "Libro no guardado");
                return new ResponseEntity<BookResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch( Exception e) {
            log.error("Error en grabar libro");
            response.setMetadata("Respuesta nok", "-1", "Error al grabar libro");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "00", "Libro creado");
        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK); //devuelve 200
    }

    @Override
    @Transactional
    public ResponseEntity<BookResponseRest> update(Book book, Long id) {
        log.info("Inicio metodo actualizar");

        BookResponseRest response = new BookResponseRest();
        List<Book> list = new ArrayList<>();

        try {

            Optional<Book> libroBuscado = bookDao.findById(id);

            if (libroBuscado.isPresent()) {
                libroBuscado.get().setName(book.getName());
                libroBuscado.get().setDescription(book.getDescription());
                libroBuscado.get().setCategory(book.getCategory());

                Book libroActualizar = bookDao.save(libroBuscado.get()); //actualizando

                if( libroActualizar != null ) {
                    response.setMetadata("Respuesta ok", "00", "Libro actualizado");
                    list.add(libroActualizar);
                    response.getBookResponse().setBook(list);
                } else {
                    log.error("error en actualizar libro");
                    response.setMetadata("Respuesta nok", "-1", "Libro no actualizado");
                    return new ResponseEntity<BookResponseRest>(response, HttpStatus.BAD_REQUEST);
                }


            } else {
                log.error("error en actualizar libro");
                response.setMetadata("Respuesta nok", "-1", "libro no actualizado");
                return new ResponseEntity<BookResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch ( Exception e) {
            log.error("error en actualizar libro", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Libro no actualizado");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<BookResponseRest> delete(Long id) {
        log.info("Inicio metodo eliminar libro");

        BookResponseRest response = new BookResponseRest();

        try {
            //eliminamos el registro
            bookDao.deleteById(id);
            response.setMetadata("Ok", "00", "Libro eliminado");

        } catch (Exception e) {
            log.error("error en eliminar libro", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error", "-1", "Libro no eliminado");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
    }
}
