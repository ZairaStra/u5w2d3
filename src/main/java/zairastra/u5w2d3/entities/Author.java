package zairastra.u5w2d3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private int id;

    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private String avatar;

    public Author(String name, String surname, String email, LocalDate birthDate) {

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.avatar = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
    }
}
