package zairastra.u5w2d3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zairastra.u5w2d3.entities.Author;
import zairastra.u5w2d3.exceptions.ValidationException;
import zairastra.u5w2d3.payloads.NewAuthorResponseDTO;
import zairastra.u5w2d3.payloads.NewAuthorsDTO;
import zairastra.u5w2d3.services.AuthorsService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorsService authorsService;

    @GetMapping // lo riscrivo in modo che si accordi alla paginazone
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size) {
        return this.authorsService.findAll(page, size);
    }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable int authorId) {
        return this.authorsService.findAuthorById(authorId);
    }

    @PutMapping("/{authorId}")
    public Author getAuthorByIdAndUpdate(@PathVariable int authorId, @RequestBody @Validated NewAuthorsDTO payload) {
        return this.authorsService.findAuthorByIdAndUpdate(authorId, payload);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getAuthorByIdAndDelete(@PathVariable int authorId) {
        this.authorsService.findAuthorByIdAndDelete(authorId);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Author createAuthor(@RequestBody @Validated NewAuthorsDTO payload, BindingResult validationResult) {
//        if (validationResult.hasErrors()) {
//            validationResult.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));
//        } else {
//            Author newAuthor = this.authorsService.saveAuthor(payload);
//            return new NewAuthorResponseDTO(newAuthor.getId());
//        }
//        return this.authorsService.saveAuthor(payload);
//    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewAuthorResponseDTO createAuthor(@RequestBody @Validated NewAuthorsDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            //creo una lista di errori di validazione
            List<String> errors = validationResult.getFieldErrors().stream()
                    //li mappo per raggiungere SOLO IL MESSAGGIO
                    .map(fieldError -> fieldError.getDefaultMessage())
                    //li richiudo in una lista da stampare
                    .toList();
            throw new ValidationException(errors);
        }

        Author newAuthor = this.authorsService.saveAuthor(payload);
        return new NewAuthorResponseDTO(newAuthor.getId());
    }


}
