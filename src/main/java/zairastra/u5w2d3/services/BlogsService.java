package zairastra.u5w2d3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zairastra.u5w2d3.entities.Blog;
import zairastra.u5w2d3.exceptions.NotFoundException;
import zairastra.u5w2d3.payloads.NewBlogPayload;

import java.util.ArrayList;
import java.util.List;

//service resta uguale, devi solo ricordarti di injectarlo in controller
@Service
@Slf4j
public class BlogsService {
    private List<Blog> blogsFakeDatabase = new ArrayList<>();

    //i metodi crud sono 5 -> mi servono 5 metodi

    //1.cerca tutto
    public List<Blog> findAll() {
        return this.blogsFakeDatabase;
    }

    //2.cerca per id
    public Blog findBlogById(int blogId) {
        Blog found = null;
        for (Blog blog : this.blogsFakeDatabase) {
            if (blog.getId() == blogId) found = blog;
        }
        //passa il parametro dell'id qui per scrivere il messaggio di eccezione nell'exception
        if (found == null) throw new NotFoundException(blogId);
        return found;
    }

    //3.cerca per id e modifica
    public Blog findBlogByIdAndUpdate(int blogId, NewBlogPayload payload) {
        //riuso il metodo findBlogById
        Blog found = findBlogById(blogId);

        found.setCategory(payload.getCategory());
        found.setTitle(payload.getTitle());
        found.setContent(payload.getContent());
        found.setReadingTime(payload.getReadingTime());

        if (found == null) throw new NotFoundException(blogId);
        return found;
    }


    //4.cerca per id e cancella - RICORDATI CHE NON TORN ANIENTE - VOID
    public void findBlogByIdAndDelete(int blogId) {
        //riuso il metodo findBlogById
        Blog found = findBlogById(blogId);

        if (found == null) throw new NotFoundException(blogId);
        this.blogsFakeDatabase.remove(found);
    }


    //5.salva
    public Blog saveBlog(NewBlogPayload payload) {
        Blog newBlog = new Blog(payload.getCategory(), payload.getTitle(), payload.getContent(), payload.getReadingTime());
        this.blogsFakeDatabase.add(newBlog);

        log.info("The blog " + payload.getTitle() + " has been saved");
        return newBlog;
    }


}
