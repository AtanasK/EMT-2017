package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.BookDetails;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.model.jpa.FileEmbeddable;
import mk.ukim.finki.emt.persistence.BookRepository;
import mk.ukim.finki.emt.persistence.CategoryRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by atanask on 3.4.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BookDetailsHelperTest {

    @Autowired
    BookDetailsServiceHelper bookDetailsServiceHelper;

    @Autowired
    CategoryServiceHelper categoryServiceHelper;

    @Autowired
    BookServiceHelper bookServiceHelper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private Book testBook;
    Category testCategory;

    @After
    public void clearTestEntities() {
        if (testBook != null) {
            bookRepository.delete(testBook);
        }
        if (testCategory != null) {
            categoryRepository.delete(testCategory);
        }
    }

    @Test
    public void testCrud() throws SQLException {
        testCategory = categoryServiceHelper.createTopLevelCategory("test_category");

        String[] authors = new String[2];
        authors[0] = "Test author 1";
        authors[1] = "Test author 2";

        testBook = bookServiceHelper.createBook("Test book", testCategory.id, authors, "testISBN", 1234.0);

        byte[] testBytes = new byte[5];

        for (int i = 0; i < testBytes.length; i++)
            testBytes[i] = (byte) i;

        String testContentType = "contentType123";


        FileEmbeddable picture = new FileEmbeddable();
        picture.contentType = testContentType;
        picture.data = new SerialBlob(testBytes);
        picture.size = testBytes.length;
        picture.fileName = testBook.name;

        String testDescription = "Test description";


        bookDetailsServiceHelper.addDetailsToBook(testBook.id, testDescription, picture.data, picture.fileName, picture.contentType, picture.size);

        String bookDesc = bookDetailsServiceHelper.getBookDescription(testBook.id);
        Assert.assertEquals("No description", testDescription, bookDesc);

        FileEmbeddable bookPicture = bookDetailsServiceHelper.getBookPicture(testBook.id);
        Assert.assertEquals("No picture inserted", picture, bookPicture);

        testDescription = "Test update description";


        bookDetailsServiceHelper.updateBookDescription(testBook.id, testDescription);
        bookDesc = bookDetailsServiceHelper.getBookDescription(testBook.id);
        Assert.assertEquals("Test description not updated", testDescription, bookDesc);

        testContentType = "New content type";

        for (int i = 0; i < testBytes.length; i++)
            testBytes[i] = (byte) (i + 2);

        picture.contentType = testContentType;
        picture.data = new SerialBlob(testBytes);
        picture.size = testBytes.length;
        picture.fileName = testBook.name;

        bookDetailsServiceHelper.updateBookPicture(testBook.id, testBytes, picture.contentType);
        bookPicture = bookDetailsServiceHelper.getBookPicture(testBook.id);
        Assert.assertEquals("Picture not updated", picture, bookPicture);


    }


}