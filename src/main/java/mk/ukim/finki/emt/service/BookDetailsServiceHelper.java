package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookDetails;
import mk.ukim.finki.emt.model.jpa.FileEmbeddable;

import java.sql.Blob;

/**
 * Created by atanask on 3.4.17.
 */
public interface BookDetailsServiceHelper {

    String getBookDescription(long bookId);

    int getDataSize(long bookId);

    Blob getData(long bookId);

    String getContentType(long bookId);

    String getFileName(long bookId);

    void addDetailsToBook(long bookId, String description, Blob data, String fileName, String contentType, int size);

    void updateBookDescription(long bookId, String description);

    void updateFileName(long bookId, String fileName);

    void updateData(long bookId, Blob data);

    void setContentType(long bookId, String contentType);

}