package com.RecursosHumanos.controller;

import com.RecursosHumanos.exception.RecursoNoEncontradoException;
import com.RecursosHumanos.model.Empleado;
import com.RecursosHumanos.services.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//http://localhost:8080/recursos-humanos
@RequestMapping ("/recursos-humanos")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoController {
    private static final Logger logger=
            LoggerFactory.getLogger(EmpleadoController.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        var empleados = empleadoServicio.listaEmpleados();
        empleados.forEach(empleado -> logger.info(empleados.toString()));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado a agregar: " + empleado);
        return empleadoServicio.guardarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado>
    obtenerEmpleadoPorId(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado == null)
            throw new RecursoNoEncontradoException("No se encontro el empleaodo id:" + id);
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado>
    actualizarEmpleado (@PathVariable Integer id, @RequestBody Empleado empleadoRecibido){
       Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
       if (empleado == null)
           throw new RecursoNoEncontradoException("El id recibido no existe: " + id);
       empleado.setNombre(empleadoRecibido.getNombre());
       empleado.setDepartamento(empleadoRecibido.getDepartamento());
       empleado.setSueldo(empleadoRecibido.getSueldo());
       empleadoServicio.guardarEmpleado(empleado);
       return ResponseEntity.ok(empleado);

    }
}
