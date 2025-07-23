package zairastra.u5w2d3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zairastra.u5w2d3.entities.Author;
import zairastra.u5w2d3.payloads.NewAuthorPayload;
import zairastra.u5w2d3.services.AuthorsService;

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
    public Author getAuthorByIdAndUpdate(@PathVariable int authorId, @RequestBody NewAuthorPayload body) {
        return this.authorsService.findAuthorByIdAndUpdate(authorId, body);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getAuthorByIdAndDelete(@PathVariable int authorId) {
        this.authorsService.findAuthorByIdAndDelete(authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody NewAuthorPayload body) {
        return this.authorsService.saveAuthor(body);
    }

}
