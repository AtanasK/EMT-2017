package mk.ukim.finki.emt.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by atanask on 3.4.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BookDetailsHelperTest {

    @Autowired
    BookDetailsServiceHelper bookDetailsServiceHelper;

    @Test
    public void testAddDetailsToBook() {

    }
}