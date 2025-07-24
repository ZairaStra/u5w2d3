package zairastra.u5w2d3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//il payload non continene alcun dato che non è "inserito dall'utente", o meglio generato dal server
//quindi ha perfettamente senso usare il costruttore di lombok, non bisogna escludere id o altro
@AllArgsConstructor
public class NewBlogPayload {
    private String category;
    private String title;
    //    private String cover;
    private String content;
    private double readingTime;
    private int authorId;

    @Override
    public String toString() {
        return "NewBlogPayload{" +
                "category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", readingTime=" + readingTime +
                ", authorId=" + authorId +
                '}';
    }
}
