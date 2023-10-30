package com.MatiasSilva.autoservice.servicio;

import com.MatiasSilva.autoservice.entidades.Auto;
import com.MatiasSilva.autoservice.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoServicio {
    @Autowired
    private AutoRepository autoRepository;
    public List<Auto> obtenerAutos(){
        return autoRepository.findAll();
    }
    public Auto obtenerAuto(Long id){
        return autoRepository.findById(id).orElse(null);
    }
    public Auto guardarAuto(Auto auto){
        return autoRepository.save(auto);
    }
    public List<Auto> obtenerAutosByUsuarioId(Long usuarioId){
        return autoRepository.findByUsuarioId(usuarioId);
    }
}
