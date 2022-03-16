
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

    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
    private LocalDate fecha;
    private String codigo;

    private String medico;
    private String sucursal;
    private String paciente;
    
    public SchemaDate(String _codigo){
        codigo =  _codigo;
        if (_codigo.length() > 0) {
            JSONObject date = JsonWrapper.getUniversal("citas","id",_codigo);
            if (date != null){
                fecha = LocalDate.parse(((String)date.get("fecha")),SchemaDate.dateFormat);
            }
        }
    }

    public String getFecha(){ 
        return fecha.format(SchemaDate.dateFormat); 
    }

    public void setFecha(String _fecha){
        fecha = LocalDate.parse(_fecha,SchemaDate.dateFormat);
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
        date.put("fecha",fecha.format(SchemaDate.dateFormat)); 
        date.put("codigo",codigo); 
        date.put("medico",medico); 
        date.put("sucursal",sucursal); 
        date.put("paciente",paciente); 

        JsonWrapper.setUniversal(date, "citas", "id", codigo);
    }

    public void removeJson(){
        JsonWrapper.filterField("citas","id",codigo);
    }
}
