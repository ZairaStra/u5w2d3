package zairastra.u5w2d3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zairastra.u5w2d3.payloads.ErrorsDTO;
import zairastra.u5w2d3.payloads.ErrorsWithListDTO;

import java.time.LocalDateTime;

@RestControllerAdvice //annotazione specifica per la classe che gestisce le eccezioni
//è un controller vero e proprio, solo specializzato
public class ExceptionsHandler {

    //BAD REQUEST - NON HA UN MESSAGGIO NELL'ECCEZIONE - lo specifico nella singola eccezione?
    //RICORDA: ti serve un payload da ritornare - SOLO JSON
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
//come negli altri controller, serve a specificare che tipo di statuscode voglio
    public ErrorsDTO handleBadRequest(BadRequestException exception) {
        return new ErrorsDTO(exception.getMessage(), LocalDateTime.now());
    }

    //BAD REQUEST - ERRORI DI VALIDAZIONE
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListDTO handleValidationErrors(ValidationException exception) {
        return new ErrorsWithListDTO(exception.getMessage(), LocalDateTime.now(), exception.getErrorMessages());
    }

    //NOTFOUNDEXCEPTION
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(NotFoundException exception) {
        return new ErrorsDTO(exception.getMessage(), LocalDateTime.now());
    }

    //ECCEZIONI NON GESTITE SINGOLARMENTE
    //RICORDATI DI NON RESTITUIRE IL MESSAGGIO CHE VIENE DALL'ECCEZIONE PER NON DIFFONDERE DATI SENSIBILI
    //SCRIVI MESSAGGIO CUSTOM QUI
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleServerErrore(Exception exception) {
        exception.printStackTrace();//questo mi stampa in console lo stack trace per capire dove sta l'errore
        return new ErrorsDTO("Ooops, we have a problem!", LocalDateTime.now());
    }
}
