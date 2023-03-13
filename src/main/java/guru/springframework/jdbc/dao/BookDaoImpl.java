package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

    BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public void updateBook(Book saved) {
        Book fetched = bookRepository.getById(saved.getId());
        fetched.setTitle(saved.getTitle());
        fetched.setIsbn(saved.getIsbn());
        fetched.setAuthorId(saved.getAuthorId());
        fetched.setPublisher(saved.getPublisher());
        bookRepository.save(fetched);
    }

    @Override
    public Book findBookByTitle(String cleanCode) {
        return bookRepository.findBookByTitle(cleanCode).orElseThrow(EntityNotFoundException::new);
    }
}
