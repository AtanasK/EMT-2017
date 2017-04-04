package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookDetails;
import mk.ukim.finki.emt.model.jpa.BookPicture;
import mk.ukim.finki.emt.model.jpa.FileEmbeddable;

import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by atanask on 3.4.17.
 */
public interface BookDetailsServiceHelper {

    String getBookDescription(long bookId);

    FileEmbeddable getBookPicture(long bookId);

    BookPicture updateBookPicture(long bookId, byte[] bytes, String contentType) throws SQLException;

    void addDetailsToBook(long bookId, String description, Blob data, String fileName, String contentType, int size);

    BookDetails getBookDetails(long bookId);

    void updateBookDescription(long bookId, String description);

}