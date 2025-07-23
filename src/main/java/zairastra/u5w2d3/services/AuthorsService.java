package zairastra.u5w2d3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zairastra.u5w2d3.entities.Author;
import zairastra.u5w2d3.exceptions.NotFoundException;
import zairastra.u5w2d3.payloads.NewAuthorPayload;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorsService {
    private List<Author> authorsFakeDatabase = new ArrayList<>();

    //1.cerca tutto
    public List<Author> findAll() {
        return this.authorsFakeDatabase;
    }

    //2.cerca per id
    public Author findAuthorById(int authorId) {
        Author found = null;
        for (Author author : this.authorsFakeDatabase) {
            if (author.getId() == authorId) found = author;
        }
        //passa il parametro dell'id qui per scrivere il messaggio di eccezione nell'exception
        if (found == null) throw new NotFoundException(authorId);
        return found;
    }

    //3.cerca per id e modifica
    public Author findAuthorByIdAndUpdate(int authorId, NewAuthorPayload payload) {
        Author found = findAuthorById(authorId);

        found.setName(payload.getName());
        found.setSurname(payload.getSurname());
        found.setEmail(payload.getEmail());
        found.setBirthDate(payload.getBirthDate());

        if (found == null) throw new NotFoundException(authorId);
        return found;
    }


    //4.cerca per id e cancella - VOID
    public void findAuthorByIdAndDelete(int authorId) {
        Author found = findAuthorById(authorId);

        if (found == null) throw new NotFoundException(authorId);
        this.authorsFakeDatabase.remove(found);
    }


    //5.salva
    public Author saveAuthor(NewAuthorPayload payload) {
        Author newAuthor = new Author(payload.getName(), payload.getSurname(), payload.getEmail(), payload.getBirthDate());
        this.authorsFakeDatabase.add(newAuthor);

        log.info("The author " + payload.getName() + " " + payload.getSurname() + " has been saved");
        return newAuthor;
    }
}
