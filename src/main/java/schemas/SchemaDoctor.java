
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
package schemas;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import utils.JsonWrapper;

public class SchemaDoctor extends SchemaPersona{
    
    private String sucursal;
    private String ID;
    
    public String getSucursal(){
        return sucursal;
    }
    
    public void setSucursal(String _sucursal){
        sucursal = _sucursal;
    }
        
    public String getID(){
        return ID;
    }
    
    public void setID(String _id){
        ID = _id;
    }
    
    public SchemaDoctor(String doctorId){
        
    }

    public String getCódigoDeLicencia(){ return ""; }
    public void setCódigoDeLicencia(String id){}
    
    public void commit(){ 
        JSONObject medico = new JSONObject();  
        medico.put("id",this.getCedula()); 
        medico.put("nombre", this.getNombre()); 
        medico.put("apellido", this.getApellido()); 
        medico.put("cedula",this.getCedula()); 
        medico.put("sexo",this.getSexo()); 
        medico.put("lugarDeNacimiento",this.getLugarDeNacimiento()); 
        medico.put("estadoCivil",this.getEstadoCivil()); 
        medico.put("direccionDeHabitacion",this.getDireccionDeHabitacion()); 
        medico.put("telefono",this.getTelefono()); 
        medico.put("profesion",this.getProfesion()); 
        medico.put("ocupacion",this.getOcupacion()); 
        medico.put("sucursal", this.getSucursal());
        JsonWrapper.setUniversal(medico, "medicos", "id", this.getCedula()); 
    } 
}
