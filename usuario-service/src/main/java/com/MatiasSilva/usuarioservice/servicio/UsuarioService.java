package com.MatiasSilva.usuarioservice.servicio;

import com.MatiasSilva.usuarioservice.entidades.Usuario;
import com.MatiasSilva.usuarioservice.feignclients.AutoFeignClient;
import com.MatiasSilva.usuarioservice.feignclients.MotoFeignClient;
import com.MatiasSilva.usuarioservice.modelos.Auto;
import com.MatiasSilva.usuarioservice.modelos.Moto;
import com.MatiasSilva.usuarioservice.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    AutoFeignClient autoFeignClient;

    @Autowired
    MotoFeignClient motoFeignClient;
    public List<Auto> obtenerAutos(Long usuarioId){
        List<Auto> autos = restTemplate.getForObject("http://localhost:8081/auto/usuario/" + usuarioId, List.class);
        return autos;
    }

    public List<Moto> obtenerMotos(Long usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://localhost:8082/moto/usuario/" + usuarioId, List.class);
        return motos;
    }
    public Auto guardarAuto(Long usuarioId, Auto auto){
        auto.setUsuarioId(usuarioId);
        Auto autoNuevo = autoFeignClient.guardar(auto);
        return autoNuevo;
    }

    public Moto guardarMoto(Long usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto motoNueva = motoFeignClient.guardar(moto);
        return motoNueva;
    }

    public Map<String, Object> obtenerUsuarioyVehiculos(Long usuarioId){
        Map<String, Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuario == null){
            resultado.put("Mensaje", "El usuario no existe");
            return resultado;
        }

        resultado.put("Usuario", usuario);
        List<Auto> autos = autoFeignClient.obtenerAutos(usuarioId);
        if(autos.isEmpty()){
            resultado.put("Mensaje", "El usuario no posee autos");
        }else{
            resultado.put("Autos", autos);
        }

        List<Moto> motos = motoFeignClient.obtenerMotos(usuarioId);
        if(autos.isEmpty()){
            resultado.put("Mensaje", "El usuario no posee motos");
        }else{
            resultado.put("Motos", motos);
        }

        return resultado;
    }
    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }
    public Usuario obtenerUsuario(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }
    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
