
 
package schemas;

import java.util.*;
import java.time.*;
import java.time.format.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import utils.JsonWrapper;

public class SchemaDate {

    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
    private LocalDate fecha;
    private String codigo = "";
    private String medico = "";
    private String sucursal = "";
    private String paciente = "";

    private LocalDate fechaModificacion =null;
    private boolean solicitudCancelar = false;
    private boolean registered = false;
    
    public SchemaDate(String _codigo){
        codigo =  _codigo;
        if (_codigo.length() > 0) {
            JSONObject date = JsonWrapper.getUniversal("citas","id",_codigo);
            if (date != null){
                fecha = LocalDate.parse(((String)date.get("fecha")),SchemaDate.dateFormat);
                if((String)date.get("fechaModificacion")!=null){
                    fechaModificacion = LocalDate.parse(((String)date.get("fechaModificacion")),SchemaDate.dateFormat);
                } 
                paciente = (String)date.get("paciente");
                sucursal = (String)date.get("sucursal");
                medico = (String)date.get("medico");
                solicitudCancelar = (boolean)date.get("solicitudCancelar");
                registered = (boolean)date.get("registered");
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
    
    public boolean getRegistered(){
        return registered; 
    }

    public void setRegistered(boolean _registered){
        registered = _registered;
    }
    public boolean getSolicitudCancelar(){
        return solicitudCancelar; 
    }

    public void setSolicitudCancelar(boolean _solicitudCancelar){
        solicitudCancelar = _solicitudCancelar;
    }

    public LocalDate getFechaModificacion(){
        return fechaModificacion; 
    }

    public void setFechaModificacion(String _fechaModificacion){
        fechaModificacion = LocalDate.parse(_fechaModificacion,SchemaDate.dateFormat);
    }
    
    public String getPaciente(){
        return paciente; 
    }

    public void setPaciente(String _paciente){
        paciente = _paciente;
    }

    public void commit() throws Exception {
        JSONObject date = new JSONObject();
        String rawFecha = fecha.format(SchemaDate.dateFormat);
        JSONArray allDates = JsonWrapper.getAllUniversal("citas","fecha",rawFecha);
        JSONArray freeDoctors = JsonWrapper.getOnlyField(JsonWrapper.getField("medicos"),"id");
        String oldDoctor = (String)date.get("medico");
        boolean hasOldDoctor = (oldDoctor != null && oldDoctor.length() >0);

        if (hasOldDoctor) 
            for (Object obj : allDates) {
                JSONObject curDate = (JSONObject)obj;
                String curFecha = (String) curDate.get("fecha");  
                if (curFecha.equals(rawFecha)){
                    freeDoctors.remove((String)curDate.get("medico"));
                }
            }

        if (freeDoctors.size() <= 0 && !hasOldDoctor)
            throw new Exception("No doctor found");

        String doctorId = (String)freeDoctors.get(0);
        JSONObject doctor = JsonWrapper.getUniversal("medicos","id",doctorId);

        date.put("fecha",rawFecha); 
        System.out.print("codigo: ");
        System.out.print(codigo);
        date.put("id",codigo); 
        if (!hasOldDoctor)
            date.put("medico",freeDoctors.get(0)); 
        date.put("sucursal",(String)doctor.get("sucursal")); 
        date.put("paciente",paciente); 
        date.put("registered", registered); 

        if (fechaModificacion != null)
            date.put("fechaModificacion",(String)fechaModificacion.format(SchemaDate.dateFormat));

        date.put("solicitudCancelar",solicitudCancelar);
        date.put("registered",registered);

        //TODO remove date id from patient too

        JsonWrapper.setUniversal(date, "citas", "id", codigo);
    }

    public void removeJson(){
        JsonWrapper.filterField("citas","id",codigo);
    }
}
