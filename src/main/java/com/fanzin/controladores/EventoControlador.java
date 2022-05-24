package com.fanzin.controladores;

import com.fanzin.entidades.Evento;
import com.fanzin.enumeraciones.ActividadesEvento;
import com.fanzin.servicios.EventoServicio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/evento")
public class EventoControlador {

    @Autowired
    private EventoServicio eventoServicio;
    
    
    @GetMapping("/cartelera")
    public String cartelera() {
        return "eventos.html";
    }
    
    @GetMapping("/form")
    public String form() {
        return "crear_evento_form.html";
    }

    @GetMapping("/listar")
    public String listarEvento(ModelMap modelo) {
        List<Evento> eventos = eventoServicio.listar();

        modelo.put("eventos", eventos);

        return ".html";
    }

    @PostMapping("/form")
    public String crearEvento(ModelMap modelo, @RequestParam String idOrganizador, @RequestParam String contenido, @RequestParam String direccion, @RequestParam String valor, MultipartFile archivo, @RequestParam Date fecha,@RequestParam String titulo, @RequestParam ActividadesEvento actividad) {

        try {
            eventoServicio.crear(idOrganizador, contenido, direccion, valor, archivo, fecha,titulo, actividad);
            modelo.put("exito", "Se guardó correctamente");
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("idOrganizador", idOrganizador);
            modelo.put("contenido", contenido);
            modelo.put("direccion", direccion);
            modelo.put("valor", valor);
            //CUIDADO CON LA IMAGEN VER VIDEO
            modelo.put("archivo", archivo);
            modelo.put("fecha", fecha);

            return "html";
        }
        return "html";

    }

    
    
    
    
    
}
