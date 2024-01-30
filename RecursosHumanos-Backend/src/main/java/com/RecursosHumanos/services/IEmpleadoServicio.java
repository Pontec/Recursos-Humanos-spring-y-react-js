package com.RecursosHumanos.services;

import com.RecursosHumanos.model.Empleado;

import java.util.List;

public interface IEmpleadoServicio {
    public List<Empleado> listaEmpleados();
    public Empleado buscarEmpleadoPorId(Integer idEmpleado);
    public Empleado guardarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Empleado empleado);
}
