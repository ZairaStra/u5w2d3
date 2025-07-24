package zairastra.u5w2d3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zairastra.u5w2d3.entities.Author;
import zairastra.u5w2d3.entities.Blog;
import zairastra.u5w2d3.exceptions.NotFoundException;
import zairastra.u5w2d3.payloads.NewBlogsDTO;
import zairastra.u5w2d3.repositories.BlogsRepository;

//service resta uguale, devi solo ricordarti di injectarlo in controller
@Service
@Slf4j
public class BlogsService {

    @Autowired
    private BlogsRepository blogsRepository;

    @Autowired
    private AuthorsService authorsService;

//    private List<Blog> blogsFakeDatabase = new ArrayList<>();

    //i metodi crud sono 5 -> mi servono 5 metodi

    //1.cerca tutto
    public Page<Blog> findAll(int pageNumb, int pageSize) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumb, pageSize);
        return blogsRepository.findAll(pageable);
    }

    //2.cerca per id
    public Blog findBlogById(int blogId) {
        return blogsRepository.findById(blogId).orElseThrow(() -> new NotFoundException(blogId));
    }

    //3.cerca per id e modifica
    public Blog findBlogByIdAndUpdate(int blogId, NewBlogsDTO payload) {
        //riuso il metodo findBlogById
        Blog found = findBlogById(blogId);

        found.setCategory(payload.category());
        found.setTitle(payload.title());
        found.setContent(payload.content());
        found.setReadingTime(payload.readingTime());

        Blog updatedBlog = blogsRepository.save(found);

        log.info("The blog " + updatedBlog.getTitle() + " has been updated");

        return updatedBlog;
    }


    //4.cerca per id e cancella - RICORDATI CHE NON TORN ANIENTE - VOID
    public void findBlogByIdAndDelete(int blogId) {
        //riuso il metodo findBlogById
        Blog found = findBlogById(blogId);
        blogsRepository.delete(found);
    }


    //5.salva
//    public Blog saveBlog(NewBlogPayload payload) {
//        Blog newBlog = new Blog(payload.getCategory(), payload.getTitle(), payload.getContent(), payload.getReadingTime());
//        this.blogsFakeDatabase.add(newBlog);
//
//        log.info("The blog " + payload.getTitle() + " has been saved");
//        return newBlog;
//    }
    public Blog saveBlog(NewBlogsDTO payload) {
        // Verifico che l'autore esista
        Author foundAuthor = authorsService.findAuthorById(payload.authorId());

        Blog newBlog = new Blog(payload.category(), payload.title(), "https://picsum.photos/200/300", payload.content(), payload.readingTime(), foundAuthor);

//        //queste due righe sono sbagliate, devi mettere tutto nel costruttore e poi li passi nel newBlog
//        newBlog.setAuthor(foundAuthor);
//
//        newBlog.setCover("https://picsum.photos/200/300");

        Blog savedBlog = blogsRepository.save(newBlog);

        log.info("The blog " + payload.title() + " written by " + foundAuthor.getName() + " " + foundAuthor.getSurname() + " has been saved");

        return savedBlog;
    }


}
