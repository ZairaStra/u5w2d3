package zairastra.u5w2d3.controllers;


//qui setto i metodi del crud - chiamo cioè le notazioni
//il "metodo interno", quello che fa effettivamente il lavoro resta nel service->
//le query personalizzate le scrivo nel service e le chiamo dentro i metodi di interrogazione del db che scrivo qui

//se avessi un db dovrei anche fare i repository per accedere alle query base

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zairastra.u5w2d3.entities.Blog;
import zairastra.u5w2d3.exceptions.ValidationException;
import zairastra.u5w2d3.payloads.NewBlogResponseDTO;
import zairastra.u5w2d3.payloads.NewBlogsDTO;
import zairastra.u5w2d3.services.BlogsService;

import java.util.List;

@RestController// ricorda: sempre Rest, non solo Controller
@RequestMapping("/blogs") //mi serve per non riscrivere sempre l'url, nei metodi in cui serve una path la aggiungo
public class BlogController {

    @Autowired
    private BlogsService blogsService;

    //1.cerca tutto - GET lista
    @GetMapping
    public Page<Blog> getBlogs(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size) {
        return this.blogsService.findAll(page, size);
    }

    //2.cerca per id - GET singolo
    @GetMapping("/{blogId}")
    //questa è la path, l aparte variabile che si aggiunge all'indirizzo fissato nel RequestMapping
    public Blog getBlogById(@PathVariable int blogId) {
        return this.blogsService.findBlogById(blogId);
    }


    //3.cerca per id e modifica - PUT
    @PutMapping("/{blogId}") //in questo caso non solo mi serve l apath ma anche il payload per modificare i campi
    //in pratica il payload serve ogni volta che mi servono dati che provengono dall'utente - anche nalla post/save
    public Blog getBlogByIdAndUpdate(@PathVariable int blogId, @RequestBody @Validated NewBlogsDTO payload) {
        return this.blogsService.findBlogByIdAndUpdate(blogId, payload);
    }

    //4.cerca per id ed elimina - DELETE
    //VOID
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //serve per ottenere uno statuscode specifico, non solo ok
    public void getBlogByIdAndDelete(@PathVariable int blogId) {
        this.blogsService.findBlogByIdAndDelete(blogId);
    }


    //5.salva - POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewBlogResponseDTO createBlog(@RequestBody @Validated NewBlogsDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            //creo una lista di errori di validazione
            List<String> errors = validationResult.getFieldErrors().stream()
                    //li mappo per raggiungere SOLO IL MESSAGGIO
                    .map(fieldError -> fieldError.getDefaultMessage())
                    //li richiudo in una lista da stampare
                    .toList();
            throw new ValidationException(errors);
        }
//dovrei fare un payload per la risposta di blog ma non finirò mai intempo se lo faccio
        Blog savedBlog = this.blogsService.saveBlog(payload);
        return new NewBlogResponseDTO(savedBlog.getTitle());
    }
}

