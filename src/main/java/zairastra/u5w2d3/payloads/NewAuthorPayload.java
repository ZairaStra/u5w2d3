package zairastra.u5w2d3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//il payload non continene alcun dato che non è "inserito dall'utente", o meglio generato dal server
//quindi ha perfettamente senso usare il costruttore di lombok, non bisogna escludere id o altro
@AllArgsConstructor
public class NewAuthorPayload {
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", email='" + email + '\'' +
                        ", birthDate=" + birthDate;

    }
}