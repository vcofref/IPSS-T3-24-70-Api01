package cl.ipss.apis70.responses;

import java.util.List;

import cl.ipss.apis70.models.Videojuego;
import lombok.Data;

@Data
public class VideoJuegosResponse {
    private int status;
    private String message;
    private List<Videojuego> videojuego;
}
