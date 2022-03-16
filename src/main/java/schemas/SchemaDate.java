
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
package schemas;

import java.util.*;
import java.time.*;
import java.time.format.*;
import org.json.simple.JSONObject;

import utils.JsonWrapper;

public class SchemaDate {

    private LocalDateTime fecha;
    private String codigo;

    private String medico;
    private String sucursal;
    private String paciente;
    
    public SchemaDate(String _codigo){
        if (_codigo.length() > 0) {
            JSONObject date = JsonWrapper.getUniversal("citas","id",_codigo);
            if (date != null){
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                fecha = LocalDateTime.parse(((String)date.get("fecha")),myFormatObj);
                codigo = (String)date.get("id");
            }
        }
    }

    public String getFecha(){ 
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fecha.format(myFormatObj); 
    }

    public void setFecha(String _fecha){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        fecha = LocalDateTime.parse(_fecha,myFormatObj);
    }

    public String getCodigo(){
        return codigo; 
    }

    public void setCodigo(String _codigo){
        codigo = _codigo;
    }

    public String getMedico(){
        return medico; 
    }

    public void setMedico(String _medico){
        medico = _medico;
    }

    public String getSucursal(){
        return sucursal; 
    }

    public void setSucursal(String _sucursal){
        sucursal = _sucursal;
    }

    public String getPaciente(){
        return paciente; 
    }

    public void setPaciente(String _paciente){
        paciente = _paciente;
    }

    public void commit(){
        JSONObject date = new JSONObject();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        date.put("fecha",fecha.format(myFormatObj)); 
        date.put("codigo",codigo); 
        date.put("medico",medico); 
        date.put("sucursal",sucursal); 
        date.put("paciente",paciente); 

        JsonWrapper.setUniversal(date, "citas", "id", codigo);
    }
}
