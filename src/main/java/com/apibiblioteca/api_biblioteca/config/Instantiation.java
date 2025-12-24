package com.apibiblioteca.api_biblioteca.config;

import com.apibiblioteca.api_biblioteca.domain.Book;
import com.apibiblioteca.api_biblioteca.domain.Publisher;
import com.apibiblioteca.api_biblioteca.domain.User;
import com.apibiblioteca.api_biblioteca.domain.enums.BookStatus;
import com.apibiblioteca.api_biblioteca.domain.enums.Condition;
import com.apibiblioteca.api_biblioteca.repository.BookRepository;
import com.apibiblioteca.api_biblioteca.repository.PublisherRepository;
import com.apibiblioteca.api_biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        publisherRepository.deleteAll();
        bookRepository.deleteAll();

        User user01 = new User(
                null,
                "Carlos Andrade",
                "Carlinhos@gmail.com",
                encoder.encode("asa4443")
        );
        User user02 = new User(
                null,
                "Marquinhos Var",
                "Var@gmail.com",
                encoder.encode("Assr443")
        );

        Publisher publisher1 = new Publisher(
                null,
                "O'Reilly"
        );
        Publisher publisher2 = new Publisher(
                null,
                "Pearson"
        );
        Publisher publisher3 = new Publisher(
                null,
                "Addison-Wesley"
        );

        publisherRepository.saveAll(Arrays.asList(publisher1, publisher2, publisher3));

        Book book1 = new Book(
                null,
                "Clean Code",
                2008,
                "9780132350884",
                "Robert C. Martin",
                publisher1.getId(),
                Condition.NEW,
                BookStatus.AVAILABLE
        );

        Book book2 = new Book(
                null,
                "Effective Java",
                2018,
                "9780134685991",
                "Joshua Bloch",
                publisher2.getId(),
                Condition.GOOD,
                BookStatus.RESERVED
        );

        Book book3 = new Book(
                null,
                "Design Patterns",
                1994,
                "9780201633610",
                "Erich Gamma",
                publisher3.getId(),
                Condition.FAIR,
                BookStatus.AVAILABLE
        );

        Book book4 = new Book(
                null,
                "Refactoring",
                2019,
                "9780134757599",
                "Martin Fowler",
                publisher2.getId(),
                Condition.GOOD,
                BookStatus.MAINTENANCE
        );

        Book book5 = new Book(
                null,
                "Domain-Driven Design",
                2003,
                "9780321125217",
                "Eric Evans",
                publisher1.getId(),
                Condition.POOR,
                BookStatus.RESERVED
        );

        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4, book5));

        userRepository.saveAll(Arrays.asList(user01, user02));

    }
}
