package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.api.TareasApi;
import com.app.entity.Tarea;
import com.app.model.Response;
import com.app.service.IEstadoTareaService;
import com.app.service.ITareaService;
import com.app.service.IUsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Tareas", description = "Endpoints para gestionar tareas")
public class TareaController implements TareasApi {

	@Autowired
    private ITareaService tareaService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IEstadoTareaService estadoTareaService;


    @Override
    public ResponseEntity<List<com.app.model.Tarea>> tareasGet() {
        var tareas = tareaService.findAll().stream()
                .map(this::mapToModel)
                .toList();
        return ResponseEntity.ok(tareas);
    }


    @Override
    public ResponseEntity<Response> tareaPost(@Valid com.app.model.Tarea tareaReq) {

        if (tareaService.findByTarea(tareaReq.getNombre()).isPresent()) {
            Response error = new Response(400, "La tarea con ese nombre ya existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        if (usuarioService.findById(tareaReq.getUsuario().getId()).isEmpty()) {
            Response error = new Response(400, "El usuario asociado no existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        if (estadoTareaService.findById(tareaReq.getEstadoTarea().getId()).isEmpty()) {
            Response error = new Response(400, "El estado de tarea no es válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        var entity = mapToEntity(tareaReq);
        tareaService.save(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(201, "Se ha creado la tarea correctamente"));
    }

    
    @Override
    public ResponseEntity<Response> tareaIdPut(@PathVariable Integer id, @Valid com.app.model.Tarea tareaReq) {

        var tareaOpt = tareaService.findById(id.longValue());
        if (tareaOpt.isEmpty()) {
            Response error = new Response(404, "La tarea con id " + id + " no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        var tareaMismoNombre = tareaService.findByTarea(tareaReq.getNombre());
        if (tareaMismoNombre.isPresent() && !tareaMismoNombre.get().getId().equals(id.longValue())) {
            Response error = new Response(400, "La tarea con ese nombre ya existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        if (usuarioService.findById(tareaReq.getUsuario().getId()).isEmpty()) {
            Response error = new Response(400, "El usuario asociado no existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        if (estadoTareaService.findById(tareaReq.getEstadoTarea().getId()).isEmpty()) {
            Response error = new Response(400, "El estado de tarea no es válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        var entityToUpdate = mapToEntity(tareaReq);
        entityToUpdate.setId(id.longValue());

        tareaService.save(entityToUpdate);

        return ResponseEntity.ok(new Response(200, "Se ha creado la tarea correctamente"));
    }    
    
    
    @Override
    public ResponseEntity<Response> tareaIdDelete(@PathVariable Integer id) {
        return tareaService.findById(id.longValue())
                .map(tarea -> {
                    tareaService.delete(id.longValue());
                    return ResponseEntity.ok(new Response(200, "Tarea eliminada correctamente"));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response(1, "No existe tarea en BD con el ID: " + id)));
    }


    private com.app.model.Tarea mapToModel(Tarea entity) {
        var model = new com.app.model.Tarea();
        model.setId(entity.getId());
        model.setNombre(entity.getNombre());

        var usuario = new com.app.model.UsuarioRef();
        usuario.setId(entity.getUsuario().getId());
        usuario.setUsername(entity.getUsuario().getUsername());
        model.setUsuario(usuario);

        var estado = new com.app.model.EstadoTareaRef();
        estado.setId(entity.getEstadoTarea().getId());
        estado.setNombre(entity.getEstadoTarea().getNombre());
        model.setEstadoTarea(estado);

        return model;
    }

    private Tarea mapToEntity(com.app.model.Tarea model) {
        var entity = new Tarea();
        entity.setNombre(model.getNombre());
        entity.setUsuario(usuarioService.findById(model.getUsuario().getId()).orElseThrow());
        entity.setEstadoTarea(estadoTareaService.findById(model.getEstadoTarea().getId()).orElseThrow());
        return entity;
    }
}
