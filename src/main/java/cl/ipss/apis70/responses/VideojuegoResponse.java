package cl.ipss.apis70.responses;

import cl.ipss.apis70.models.Videojuego;
import lombok.Data;

@Data
public class VideojuegoResponse {

    private int status;
    private String message;
    private Videojuego videojuego;

}
