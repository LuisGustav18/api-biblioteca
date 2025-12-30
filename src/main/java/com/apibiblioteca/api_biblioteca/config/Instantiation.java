package com.apibiblioteca.api_biblioteca.config;

import com.apibiblioteca.api_biblioteca.domain.*;
import com.apibiblioteca.api_biblioteca.domain.enums.Condition;
import com.apibiblioteca.api_biblioteca.repository.*;
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
    private LibraryRepository libraryRepository;

    @Autowired
    private BookInventoryRepository bookInventoryRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        publisherRepository.deleteAll();
        bookRepository.deleteAll();
        bookInventoryRepository.deleteAll();
        libraryRepository.deleteAll();

        Library library01 = new Library(null,
                "Central Biblioteca"
        );

        Library library02 = new Library(null,
                "Municipal Bi"
        );

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
                Condition.NEW
        );

        Book book2 = new Book(
                null,
                "Effective Java",
                2018,
                "9780134685991",
                "Joshua Bloch",
                publisher2.getId(),
                Condition.GOOD
        );

        Book book3 = new Book(
                null,
                "Design Patterns",
                1994,
                "9780201633610",
                "Erich Gamma",
                publisher3.getId(),
                Condition.FAIR
        );

        Book book4 = new Book(
                null,
                "Refactoring",
                2019,
                "9780134757599",
                "Martin Fowler",
                publisher2.getId(),
                Condition.GOOD
        );

        Book book5 = new Book(
                null,
                "Domain-Driven Design",
                2003,
                "9780321125217",
                "Eric Evans",
                publisher1.getId(),
                Condition.POOR
        );

        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4, book5));


        BookInventory bookInventory01 = new BookInventory(
                null,
                book1.getId(),
                10
        );

        BookInventory bookInventory02 = new BookInventory(
                null,
                book3.getId(),
                2
        );

        BookInventory bookInventory03 = new BookInventory(
                null,
                book1.getId(),
                2
        );

        bookInventoryRepository.saveAll(Arrays.asList(bookInventory01, bookInventory02, bookInventory03));

        library01.getIdBooksInventory().add(bookInventory01.getId());

        library01.getIdBooksInventory().add(bookInventory02.getId());

        library02.getIdBooksInventory().add(bookInventory03.getId());

        user01.getWishlist().add(book1);

        user01.getWishlist().add(book2);

        user02.getWishlist().add(book3);

        user02.getWishlist().add(book4);

        user02.getWishlist().add(book5);

        libraryRepository.saveAll(Arrays.asList(library01, library02));

        userRepository.saveAll(Arrays.asList(user01, user02));
    }
}
