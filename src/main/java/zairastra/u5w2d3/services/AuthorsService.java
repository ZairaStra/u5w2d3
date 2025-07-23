package zairastra.u5w2d3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zairastra.u5w2d3.entities.Author;
import zairastra.u5w2d3.exceptions.BadRequestException;
import zairastra.u5w2d3.exceptions.NotFoundException;
import zairastra.u5w2d3.payloads.NewAuthorPayload;
import zairastra.u5w2d3.repositories.AuthorsRepository;

@Service
@Slf4j
public class AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;

// non serve pià la lista fake visto che accedo al db
//    private List<Author> authorsFakeDatabase = new ArrayList<>();

    //1.cerca tutto - uso la paginazione
    public Page<Author> findAll(int pageNumb, int pageSize) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumb, pageSize);
        return authorsRepository.findAll(pageable);
    }

    //2.cerca per id
    public Author findAuthorById(int authorId) {
//        Author found = null;
//        for (Author author : this.authorsFakeDatabase) {
//            if (author.getId() == authorId) found = author;
//        }
//        //passa il parametro dell'id qui per scrivere il messaggio di eccezione nell'exception
//        if (found == null) throw new NotFoundException(authorId);
//        return found;

        //ESISTE UNA VERSOPE SUPREBREVE:
        return authorsRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    //3.cerca per id e modifica
    public Author findAuthorByIdAndUpdate(int authorId, NewAuthorPayload payload) {
        Author found = findAuthorById(authorId); //NotFoundException è già dentro questo metodo (vd sopra)

        if (!found.getEmail().equals(payload.getEmail())) {
            authorsRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
                throw new BadRequestException(author.getEmail() + " is already in our system");
            });
        }

        found.setName(payload.getName());
        found.setSurname(payload.getSurname());
        found.setEmail(payload.getEmail());
        found.setBirthDate(payload.getBirthDate());

        found.setAvatar("https://ui-avatars.com/api/?name=" + payload.getName() + "+" + payload.getSurname());

        Author updatedAuthor = authorsRepository.save(found);

        log.info("The author " + updatedAuthor.getName() + " " + updatedAuthor.getSurname() + " has been updated");

        return updatedAuthor;
    }


    //4.cerca per id e cancella - VOID
    public void findAuthorByIdAndDelete(int authorId) {
        Author found = findAuthorById(authorId); //NotFoundException è già dentro questo metodo (vd sopra)
//        if (found == null) throw new NotFoundException(authorId);
        authorsRepository.delete(found);
    }


    //5.salva
    public Author saveAuthor(NewAuthorPayload payload) {

        authorsRepository.findByEmail(payload.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + payload.getEmail() + " è già in uso!");
        });
        Author newAuthor = new Author(payload.getName(), payload.getSurname(), payload.getEmail(), payload.getBirthDate());
        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + payload.getName() + "+" + payload.getSurname());

        Author savedAuthor = authorsRepository.save(newAuthor);

        log.info("The author " + payload.getName() + " " + payload.getSurname() + " has been saved");
        return savedAuthor;
    }
}
