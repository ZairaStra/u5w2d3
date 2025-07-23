package zairastra.u5w2d3.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("We haven't found an element with id " + id);
    }
}
