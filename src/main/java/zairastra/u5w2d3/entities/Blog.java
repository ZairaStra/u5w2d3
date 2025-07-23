package zairastra.u5w2d3.entities;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

@Getter
@Setter
@ToString
public class Blog {
    @Setter(AccessLevel.NONE)
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;

    public Blog(String category, String title, String content, double readingTime) {
        //non essendoci db uso Random
        Random rndm = new Random();
        this.id = rndm.nextInt(1, 100);
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
        //non mi ero resa conto che la traccia chiedeva una cover dinamicamente creata dal servere
        this.cover = "https://picsum.photos/200/300";
    }
}
