/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schemas;

import org.json.simple.JSONObject;
import utils.JsonWrapper;

/**
 *
 * @author andres
 */
public class SchemaPaciente extends SchemaPersona {
    
    private String ID;
    private int tlfPersonaRelacionada;
    
    public SchemaPaciente(String pacienteID){
        super();
    }

    public int gettlfPersonaRelacionada(){
        return tlfPersonaRelacionada;
    }

    public void settlfPersonaRelacionada(int _tlfPersonaRelacionada){
        tlfPersonaRelacionada = _tlfPersonaRelacionada;
    }
    
    public void commit(){
        JSONObject paciente = new JSONObject(); 
        paciente.put("tlfPersonaRelacionada",tlfPersonaRelacionada);
        paciente.put("nombre",getNombre());
        paciente.put("apellido",getApellido());
        paciente.put("cedula",getCedula());
        paciente.put("sexo",getSexo());
        paciente.put("lugarDeNacimiento",getLugarDeNacimiento());
        paciente.put("fechaDeNacimiento",getFechaDeNacimiento());
        paciente.put("estadoCivil",getEstadoCivil());
        paciente.put("direccionDeHabitacion",getDireccionDeHabitacion());
        paciente.put("telefono",getTelefono());
        paciente.put("profesion",getProfesion());
        paciente.put("ocupacion",getOcupacion());
        JsonWrapper.setUniversal(paciente, "pacientes", "ID", ID);
    }
}
