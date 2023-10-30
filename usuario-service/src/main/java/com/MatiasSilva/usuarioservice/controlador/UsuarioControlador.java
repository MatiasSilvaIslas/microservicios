package com.MatiasSilva.usuarioservice.controlador;

import com.MatiasSilva.usuarioservice.entidades.Usuario;
import com.MatiasSilva.usuarioservice.modelos.Auto;
import com.MatiasSilva.usuarioservice.modelos.Moto;
import com.MatiasSilva.usuarioservice.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private UsuarioService  usuarioService;
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios(){
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") Long id){
        Usuario usuario = usuarioService.obtenerUsuario(id);
        if(usuario == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/autos/{usuarioId}")
    public ResponseEntity<List<Auto>> listarAutos(@PathVariable("usuarioId") Long id){
        Usuario usuario = usuarioService.obtenerUsuario(id);
        if(usuario == null) {
            return ResponseEntity.notFound().build();
        }

        List<Auto> autos = usuarioService.obtenerAutos(id);
        return ResponseEntity.ok(autos);
    }

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") Long id){
        Usuario usuario = usuarioService.obtenerUsuario(id);
        if(usuario == null) {
            return ResponseEntity.notFound().build();
        }

        List<Moto> motos = usuarioService.obtenerMotos(id);
        return ResponseEntity.ok(motos);
    }

    @PostMapping
    public  ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PostMapping("/auto/{usuarioId}")
    public  ResponseEntity<Auto> guardarAuto(@PathVariable("usuarioId") Long usuarioId, @RequestBody Auto auto){
        Auto nuevoAuto = usuarioService.guardarAuto(usuarioId, auto);
        return ResponseEntity.ok(nuevoAuto);
    }
    @PostMapping("/moto/{usuarioId}")
    public  ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") Long usuarioId, @RequestBody Moto moto){
        Moto nuevaMoto = usuarioService.guardarMoto(usuarioId, moto);
        return ResponseEntity.ok(nuevaMoto);
    }

    @GetMapping("/todos/{usuarioId}")
    ResponseEntity<Map<String, Object>> listarVehiculos(@PathVariable("usuarioId") Long usuarioId){
        Map<String, Object> resultado = usuarioService.obtenerUsuarioyVehiculos(usuarioId);
        return ResponseEntity.ok(resultado);
    }
}
