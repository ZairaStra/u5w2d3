package zairastra.u5w2d3.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime stamp) {
}
