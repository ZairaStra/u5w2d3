package zairastra.u5w2d3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zairastra.u5w2d3.entities.Author;
import zairastra.u5w2d3.payloads.NewAuthorPayload;
import zairastra.u5w2d3.services.AuthorsService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorsService authorsService;

    @GetMapping
    public List<Author> getAuthors() {
        return this.authorsService.findAll();
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
