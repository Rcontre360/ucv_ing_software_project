
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
package schemas;

import java.util.*;

import utils.JsonWrapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SchemaPersona {
    
    private String nombre;
    private String apellido;
    private String cedula;
    private char sexo;
    private String lugarDeNacimiento;
    private String fechaDeNacimiento;
    private String estadoCivil;
    private String direccionDeHabitacion;
    private String telefono;
    private String profesion;
    private String ocupacion;    
    
    public SchemaPersona(){ }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public char getSexo(){
        return sexo;
    }
    
    public String getLugarDeNacimiento(){
        return lugarDeNacimiento;
    }
    
    public String getFechaDeNacimiento(){
        return fechaDeNacimiento;
    }
    
    public String getEstadoCivil(){
        return estadoCivil;
    }
    
    public String getDireccionDeHabitacion(){
        return direccionDeHabitacion;
    }
    
    public String getTelefono(){
        return telefono;
    }
    
    public String getProfesion(){
        return profesion;
    }
    
    public String getOcupacion(){
        return ocupacion;
    }
    
    public void setNombre(String _nombre){
        nombre = _nombre;
    }
    
    public void setApellido(String _apellido){
        nombre = _apellido;
    }
    
    public void setCedula(String _cedula) {
        cedula = _cedula;
    }

    public void setSexo(char _sexo) {
        sexo = _sexo;
    }

    public void setLugarDeNacimiento(String _lugarDeNacimiento) {
        lugarDeNacimiento = _lugarDeNacimiento;
    }

    public void setFechaDeNacimiento(String _fechaDeNacimiento) {
        fechaDeNacimiento = _fechaDeNacimiento;
    }

    public void setEstadoCivil(String _estadoCivil) {
        estadoCivil = _estadoCivil;
    }

    public void setDireccionDeHabitacion(String _direccionDeHabitacion) {
        direccionDeHabitacion = _direccionDeHabitacion;
    }

    public void setTelefono(String _telefono) {
        telefono = _telefono;
    }

    public void setProfesion(String _profesion) {
        profesion = _profesion;
    }

    public void setOcupacion(String _ocupacion) {
        ocupacion = _ocupacion;
    }
    
    public void commit(){
        JSONObject medico = new JSONObject(); 
        medico.put("nombre", nombre);
        medico.put("apellido", apellido);
        medico.put("cedula",cedula);
        medico.put("sexo",sexo);
        medico.put("lugarDeNacimiento",lugarDeNacimiento);
        medico.put("estadoCivil",estadoCivil);
        medico.put("direccionDeHabitacion",direccionDeHabitacion);
        medico.put("telefono",telefono);
        medico.put("profesion",profesion);
        medico.put("ocupacion",ocupacion);
        JsonWrapper.setUniversal(medico, "medico", "ciMedico", cedula);
    }
    
}
