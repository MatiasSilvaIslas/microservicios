package com.MatiasSilva.usuarioservice.feignclients;

import com.MatiasSilva.usuarioservice.modelos.Auto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "auto-service", url = "http://localhost:8083")
public interface AutoFeignClient {
    @PostMapping("/auto")
    public Auto guardar(@RequestBody Auto auto);
    @GetMapping("auto/usuario/{usuarioId}")
    List<Auto> obtenerAutos(@PathVariable(name = "usuarioId") Long usuarioId);
}
