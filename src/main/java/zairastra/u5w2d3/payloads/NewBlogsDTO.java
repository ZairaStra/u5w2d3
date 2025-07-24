package zairastra.u5w2d3.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewBlogsDTO(
        String category,
        @NotEmpty(message = "Title is required")
        String title,
        @NotNull(message = "A blog must have a text")
        @NotEmpty(message = "A blog's text must contain words")
        String content,
        double readingTime,
        @NotNull(message = "Author is required")
        int authorId) {
}
