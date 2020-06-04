package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
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
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started bootstrap code.");

        Publisher puffin = new Publisher("Puffin", "8 High Oaks Road", "Welwyn Garden City", "AL8 7BH");
        publisherRepository.save(puffin);

        Publisher intrepid = new Publisher("Intrepid", "1 The Mall", "London", "W1A 1AB");
        publisherRepository.save(intrepid);

        System.out.println("Number of publishers: " + publisherRepository.count());

        Author enid = new Author("Enid", "Blyton");
        Book five = new Book("Five Go on an Adventure", "10001");
        enid.getBooks().add(five);
        five.getAuthors().add(enid);
        five.setPublisher(puffin);
        puffin.getBooks().add(five);

        authorRepository.save(enid);
        bookRepository.save(five);
        publisherRepository.save(puffin);

        Author tolkien = new Author("JRR", "Tolkien");
        Book lotr = new Book("The Lord of the Rings", "10002");
        tolkien.getBooks().add(lotr);
        lotr.getAuthors().add(tolkien);
        lotr.setPublisher(intrepid);
        intrepid.getBooks().add(lotr);

        authorRepository.save(tolkien);
        bookRepository.save(lotr);
        publisherRepository.save(intrepid);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Intrepid number of books: " + intrepid.getBooks().size());
    }

}
