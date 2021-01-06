package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository=publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher cbse =new Publisher("CBSE","Delhi, India");
        publisherRepository.save(cbse);
        System.out.println("Number of publishers: "+ publisherRepository.count());

        Author eric=new Author("Eric","Evans");
        Book book1=new Book("Domain Design","123123");

        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);
        book1.setPublisher(cbse);
        cbse.getBooks().add(book1);

        authorRepository.save(eric);
        bookRepository.save(book1);
        publisherRepository.save(cbse);

        Author steve=new Author("Steven", "Smith");
        Book bookSteve=new Book("Development with EJB","2345");

        steve.getBooks().add(bookSteve);
        bookSteve.getAuthors().add(steve);
        bookSteve.setPublisher(cbse);
        cbse.getBooks().add(bookSteve);

        authorRepository.save(steve);
        bookRepository.save(bookSteve);
        publisherRepository.save(cbse);

        System.out.println("Started in bootstrap");
        System.out.println("Number of books: "+ bookRepository.count());
        System.out.println("Published books by cbse: "+ cbse.getBooks().size());

    }
}
