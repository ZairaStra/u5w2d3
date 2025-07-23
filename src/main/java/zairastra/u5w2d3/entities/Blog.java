package zairastra.u5w2d3.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private int id;

    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;

    //NO RELAZIONE BIDIREZIONALE - SOLO BLOG CON ID DI AUTHOR
    //NO MAP IN AUTHOR
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Blog(String category, String title, String content, double readingTime) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
        //non mi ero resa conto che la traccia chiedeva una cover dinamicamente creata dal servere
        this.cover = "https://picsum.photos/200/300";
    }
}
