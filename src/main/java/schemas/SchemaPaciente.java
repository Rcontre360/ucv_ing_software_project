/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schemas;

import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import utils.JsonWrapper;

/**
 *
 * @author andres
 */
public class SchemaPaciente extends SchemaPersona {
    
    private String tlfPersonaRelacionada = "";
    private String sucursal = "";
    private JSONArray citas = new JSONArray();
    
    public SchemaPaciente(){
        super();
    }

    public String gettlfPersonaRelacionada(){
        return tlfPersonaRelacionada;
    }

    public void settlfPersonaRelacionada(String _tlfPersonaRelacionada){
        tlfPersonaRelacionada = _tlfPersonaRelacionada;
    }

    public String getSucursal(){
        return sucursal;
    }

    public void setSucursal(String _sucursal){
        sucursal = _sucursal;
    }

    public JSONArray getDates(){
        return citas;
    }
    
    public void addDate(String dateID){
        citas.add(dateID);
    }
  
    public void commit(){
        JSONObject paciente = JsonWrapper.getUniversal("pacientes","cedula",this.getCedula());
        
        if (paciente == null){
            JSONObject historial = new JSONObject();
            JSONArray patientHistory = new JSONArray();
            String id = UUID.randomUUID().toString().replace("-","").substring(0,8);
            paciente = new JSONObject();

            patientHistory.add(id);

            historial.put("paciente",this.getCedula());
            historial.put("sucursal",sucursal);
            historial.put("id",id);
            historial.put("dates",new JSONArray());
            paciente.put("citas",getDates());
            paciente.put("historial",patientHistory);
            JsonWrapper.setUniversal(historial, "historial", "id", id);
        }else{
            JSONArray dates = (JSONArray)paciente.get("citas");
            JSONArray localDates = getDates();
            for (Object rawDate : localDates)dates.add((String)rawDate);

            paciente.put("citas",dates);
        }

        paciente.put("tlfPersonaRelacionada",tlfPersonaRelacionada);
        paciente.put("sucursal",sucursal);
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
        JsonWrapper.setUniversal(paciente, "pacientes", "cedula", this.getCedula());
    }
}
