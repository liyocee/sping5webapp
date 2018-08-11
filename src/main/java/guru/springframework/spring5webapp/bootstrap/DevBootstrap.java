package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by liyosi on Aug, 2018
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private AuthorRepository authorRepository;
  private BookRepository bookRepository;
  private PublisherRepository publisherRepository;

  public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    initData();
  }

  private void initData() {

    Publisher longHornPublisher = new Publisher("Long Horn Publishers", "New York");

    // Eric
    Author eric = new Author("Eric", "Evan");
    Book ddd = new Book("Domain Driven Design", "192121", longHornPublisher);

    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);


    publisherRepository.save(longHornPublisher);
    authorRepository.save(eric);
    bookRepository.save(ddd);

    // Rod
    Publisher oxfordPublisher = new Publisher("Oxford", "London");
    Author rod = new Author("Rod", "Johnson");
    Book noEJB = new Book("J2EE Development without JJB", "22121", oxfordPublisher);
    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);

    publisherRepository.save(oxfordPublisher);
    authorRepository.save(rod);
    bookRepository.save(noEJB);
  }
}
