package com.MatiasSilva.autoservice.controlador;

import com.MatiasSilva.autoservice.entidades.Auto;
import com.MatiasSilva.autoservice.servicio.AutoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auto")
public class AutoControlador {
    @Autowired
    private AutoServicio autoServicio;

    @GetMapping
    public ResponseEntity<List<Auto>> obtenerAutos(){
        List<Auto> autos = autoServicio.obtenerAutos();
        if(autos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(autos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auto> obtenerUsuario(@PathVariable("id") Long id){
        Auto auto = autoServicio.obtenerAuto(id);
        if(auto == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(auto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Auto>> obtenerAutosPorUsuarioId(@PathVariable("usuarioId") Long usuarioId){
        List<Auto> autos = autoServicio.obtenerAutosByUsuarioId(usuarioId);
        if(autos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(autos);
    }

    @PostMapping
    public ResponseEntity<Auto> guardarAuto(@RequestBody Auto auto){
        Auto nuevoAuto = autoServicio.guardarAuto(auto);
        return ResponseEntity.ok(nuevoAuto);
    }
}
