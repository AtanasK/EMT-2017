package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookDetails;
import mk.ukim.finki.emt.model.jpa.FileEmbeddable;
import mk.ukim.finki.emt.persistence.AuthorsRepository;
import mk.ukim.finki.emt.persistence.BookPictureRepository;
import mk.ukim.finki.emt.persistence.BookRepository;
import mk.ukim.finki.emt.persistence.CategoryRepository;
import mk.ukim.finki.emt.service.BookDetailsServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
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

    public BookDetailsHelperImpl(
            CategoryRepository categoryRepository,
            BookRepository bookRepository,
            AuthorsRepository authorsRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.authorsRepository = authorsRepository;
    }

    @Override
    public String getBookDescription(long bookId) {
        Book book = bookRepository.findOne(bookId);

        BookDetails bookDetails = book.details;

        return bookDetails.description;
    }

    @Override
    public int getDataSize(long bookId) {
        Book book = bookRepository.findOne(bookId);

        FileEmbeddable downloadFile = book.details.downloadFile;

        return downloadFile.size;
    }

    @Override
    public Blob getData(long bookId) {
        Book book = bookRepository.findOne(bookId);

        FileEmbeddable downloadFile = book.details.downloadFile;

        return downloadFile.data;
    }

    @Override
    public String getContentType(long bookId) {
        Book book = bookRepository.findOne(bookId);

        FileEmbeddable downloadFile = book.details.downloadFile;

        return downloadFile.contentType;
    }

    @Override
    public String getFileName(long bookId) {
        Book book = bookRepository.findOne(bookId);

        FileEmbeddable downloadFile = book.details.downloadFile;

        return downloadFile.fileName;
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
    }

    @Override
    public void updateBookDescription(long bookId, String description) {
        Book book = bookRepository.findOne(bookId);

        book.details.description = description;
    }

    @Override
    public void updateFileName(long bookId, String fileName) {
        Book book = bookRepository.findOne(bookId);

        FileEmbeddable downloadFile = book.details.downloadFile;

        downloadFile.fileName = fileName;
    }

    @Override
    public void updateData(long bookId, Blob data) {
        Book book = bookRepository.findOne(bookId);

        FileEmbeddable downloadFile = book.details.downloadFile;

        downloadFile.data = data;
    }

    @Override
    public void setContentType(long bookId, String contentType) {
        Book book = bookRepository.findOne(bookId);

        FileEmbeddable downloadFile = book.details.downloadFile;

        downloadFile.contentType = contentType;
    }
}
