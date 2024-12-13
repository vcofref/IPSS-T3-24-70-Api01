package cl.ipss.apis70.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.ipss.apis70.models.Videojuego;
import cl.ipss.apis70.responses.VideoJuegosResponse;
import cl.ipss.apis70.responses.VideojuegoResponse;
import cl.ipss.apis70.services.VideojuegoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@RequestMapping("api")
public class VideojuegoController {

    @Autowired
    private VideojuegoService videojuegoService;

    @PostMapping(value="crear", produces = "application/json")
    public ResponseEntity<Object> createVideoJuego(@RequestBody Videojuego videojuego){

        if(videojuego.getTitle().equals("") || videojuego.getTitle() == null){
            throw new RuntimeException("El titulo es Requerido");
        }

        videojuegoService.crear(videojuego);

        VideojuegoResponse videojuegoResponse = new VideojuegoResponse();
        videojuegoResponse.setStatus(200);
        videojuegoResponse.setMessage("Juego Creado Correctamente");
        videojuegoResponse.setVideojuego(videojuego);

        return ResponseEntity.ok()
        .body(videojuegoResponse);
    }

    @GetMapping(value="listar", produces = "application/json")
    public ResponseEntity<Object> getVideoJuegos() {
        VideoJuegosResponse videoJuegosResponse = new VideoJuegosResponse();
        videoJuegosResponse.setStatus(200);
        videoJuegosResponse.setMessage("Listado de Video Juegos Disponibles");
        videoJuegosResponse.setVideojuego(videojuegoService.ListarTodos());

        return ResponseEntity.ok()
        .body(videoJuegosResponse);
    }

    @GetMapping(value="buscar/{id}", produces = "application/json")
    public ResponseEntity<Object> getVideoJuego(@PathVariable String id) {
        VideojuegoResponse videojuegoResponse = new VideojuegoResponse();
        videojuegoResponse.setStatus(200);
        videojuegoResponse.setMessage("Juego Encontrado");
        videojuegoResponse.setVideojuego(videojuegoService.buscar(id));

        return ResponseEntity.ok()
        .body(videojuegoResponse);
    }

    @PutMapping(value="actualizar/{id}", produces = "application/json")
    public ResponseEntity<Object> setVideoJuego(@PathVariable String id, @RequestBody Videojuego videojuegoRequest){ //nuevos datos
        VideojuegoResponse videojuegoResponse = new VideojuegoResponse();
        Videojuego videojuego = new Videojuego(); //este esta en la DB
    
        videojuego = videojuegoService.buscar(id);
        videojuego.setTitle(videojuegoRequest.getTitle());
        videojuego.setFabricante(videojuegoRequest.getFabricante());
        videojuegoService.crear(videojuego);

        videojuegoResponse.setStatus(200);
        videojuegoResponse.setMessage("Juego Actualizado");
        videojuegoResponse.setVideojuego(videojuego);

        return ResponseEntity.ok()
        .body(videojuegoResponse);
    }

    @DeleteMapping(value="eliminar/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteVideojuego(@PathVariable String id){
        VideojuegoResponse videojuegoResponse = new VideojuegoResponse();
        videojuegoService.eliminar(id);

        videojuegoResponse.setStatus(200);
        videojuegoResponse.setMessage("Juego Eliminado");

        return ResponseEntity.ok()
        .body(videojuegoResponse);
    }
    

    

}
