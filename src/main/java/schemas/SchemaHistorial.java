
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
package schemas;

import java.time.LocalDate;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import utils.JsonWrapper;

public class SchemaHistorial {
    
    private String ID;
    private String paciente;
    private String sucursal;
    private JSONArray citas;
    
    public SchemaHistorial(String historialID, String cedulaPaciente){
        ID = historialID;
        String _sucursal = "";
        JSONArray sucursales = JsonWrapper.getField("sucursales");
        for(Object obj: sucursales){
            JSONObject sucursalAComparar = (JSONObject) obj;
            JSONArray pacientesAComparar = (JSONArray)sucursalAComparar.get("pacientes");
            if(pacientesAComparar.contains(cedulaPaciente)){
                _sucursal = sucursalAComparar.get("nombre").toString();
            }
        }        
        if (historialID.length() > 0) {
            JSONObject historial = JsonWrapper.getUniversal("historial","id",historialID);
            if (historial != null){
                paciente = (String)historial.get("paciente");
                sucursal = (String)historial.get("sucursal");
                citas = (JSONArray)historial.get("dates");
            }else{
                paciente = cedulaPaciente;
                citas = new JSONArray();
                sucursal = _sucursal;
            }
        }else{
            paciente = cedulaPaciente;
            citas = new JSONArray();
            sucursal = _sucursal;
        }
    }
    
    public String getPaciente(){ 
        return paciente; 
    }

    public String getSucursal(){
        return sucursal; 
    }

    public void setSucursal(String _sucursal){
        sucursal = _sucursal;
    }

    public void pushCita(String minima, String maxima, String peso, String pesoTalla, String talla){
        JSONObject cita = new JSONObject(); 
        
        cita.put("id",Integer.toString(citas.size()));
        cita.put("minima",minima);
        cita.put("maxima",maxima);
        cita.put("peso",peso);
        cita.put("pesoTalla",pesoTalla);
        cita.put("talla",talla);
        citas.add(cita);
    }
    
    public JSONArray getCitas(){
        return citas;
    }
    
    public void commit(){
        JSONObject historial = new JSONObject(); 
        historial.put("id", ID);
        historial.put("paciente", paciente);
        historial.put("sucursal", sucursal);
        historial.put("dates",citas);
        JsonWrapper.setUniversal(historial, "historial", "paciente", paciente);
    }
}
