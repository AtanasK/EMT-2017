package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookPicture;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.model.jpa.FileEmbeddable;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Riste Stojanov
 */
public interface QueryService {

  Page<Book> getBooksInCategory(Long categoryId, int page, int pageSize);

  Page<Book> getPromotedBooks(int page, int pageSize);

  List<Category> findTopLevelCategories();

  BookPicture getByBookId(Long bookId);

  FileEmbeddable getDownloadFile(Long bookId);

  List<Book> searchBook(String query);
}
