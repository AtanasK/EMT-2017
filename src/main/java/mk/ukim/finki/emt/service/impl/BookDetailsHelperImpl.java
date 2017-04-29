package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookDetails;
import mk.ukim.finki.emt.model.jpa.BookPicture;
import mk.ukim.finki.emt.model.jpa.FileEmbeddable;
import mk.ukim.finki.emt.persistence.*;
import mk.ukim.finki.emt.service.BookDetailsServiceHelper;
import mk.ukim.finki.emt.service.BookServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by atanask on 3.4.17.
 */
@Service
public class BookDetailsHelperImpl implements BookDetailsServiceHelper {

    @Autowired
    BookPictureRepository bookPictureRepository;
    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;
    private AuthorsRepository authorsRepository;
    private BookDetailsRepository bookDetailsRepository;

    @Autowired
    BookServiceHelper bookHelper;

    public BookDetailsHelperImpl(
            CategoryRepository categoryRepository,
            BookRepository bookRepository,
            AuthorsRepository authorsRepository,
            BookDetailsRepository bookDetailsRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.authorsRepository = authorsRepository;
        this.bookDetailsRepository = bookDetailsRepository;
    }

    @Override
    public String getBookDescription(long bookId) {
        Book book = bookRepository.findOne(bookId);

        BookDetails bookDetails = book.details;

        return bookDetails.description;
    }

    @Override
    public void addDetailsToBook(long bookId, String description, Blob data, String fileName, String contentType, int size) {
        Book book = bookRepository.findOne(bookId);

        BookDetails bookDetails = new BookDetails();

        bookDetails.description = description;

        FileEmbeddable downloadFile = new FileEmbeddable();

        downloadFile.data = data;
        downloadFile.fileName = fileName;
        downloadFile.contentType = contentType;
        downloadFile.size = size;

        bookDetails.downloadFile = downloadFile;
        bookDetails.book = book;

        book.details = bookDetails;

        bookDetailsRepository.save(bookDetails);
    }

    @Override
    public void updateBookDescription(long bookId, String description) {
        Book book = bookRepository.findOne(bookId);

        book.details.description = description;
    }

    @Override
    public void addDownloadFile(long bookId, FileEmbeddable downloadFile) {
        Book book = bookRepository.findOne(bookId);

        book.details.downloadFile = downloadFile;
    }

    @Override
    public FileEmbeddable getDownloadFile(long bookId) {
        Book book = bookRepository.findOne(bookId);

        return book.details.downloadFile;
    }

    @Override
    public FileEmbeddable getBookPicture(long bookId) {
        Book book = bookRepository.findOne(bookId);

        FileEmbeddable bookPicture = book.details.downloadFile;

        return bookPicture;
    }

    @Override
    public BookDetails getBookDetails(long bookId) {
        Book book = bookRepository.findOne(bookId);

        return book.details;
    }

    @Override
    public BookPicture updateBookPicture(long bookId, byte[] bytes, String contentType) throws SQLException {
        return bookHelper.addBookPicture(bookId, bytes, contentType);
    }


}
