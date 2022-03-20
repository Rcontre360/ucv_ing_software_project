package utils;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonWrapper {
    public static final String FILE_NAME = "formato.json";
    public static final String DB_NAME = "db.json";
     
    public static JSONArray getBoard() {
        JSONObject json = _getJson(DB_NAME);
        JSONArray board = (JSONArray) json.get("juntaDirectiva");
        return board;
    }
    
    /*agregado por joiner*/
    public static JSONArray getHistory() {
        JSONObject json = _getJson(DB_NAME);
        JSONArray historial = (JSONArray) json.get("historial");
        return historial;
    }

    public static JSONObject getBranchOffice(String name) {
        JSONObject json = _getJson(DB_NAME);
        JSONArray branches = (JSONArray) json.get("sucursales");

        for (Object curBranch : branches) {
            JSONObject obj = (JSONObject)curBranch;
            if (obj.get("nombre").equals(name)) {
                return obj;
            }
        }
        return null;
    }

    public static JSONObject getDate(String ID){
        JSONObject json = _getJson(FILE_NAME);
        JSONArray branches = (JSONArray) json.get("sucursales");

        for (Object obj : branches) {
            JSONArray patients = (JSONArray)((JSONObject)obj).get("pacientes");
            for (Object rawPatient : patients) {
                JSONObject patient = (JSONObject)rawPatient;
                for (Object rawDate : (JSONArray)patient.get("citas")) {
                    JSONObject date = (JSONObject)rawDate; 
                    if (date.get("id").equals(ID))
                        return date;
                }
            }
        }
        return null;
    }
   

    public static boolean loadJson(){
        JSONObject json = _getJson(FILE_NAME);
        
        if (json.get("loaded") != null && json.get("loaded").equals("true"))
            return true;

        JSONObject db = new JSONObject();

        db.put("juntaDirectiva",json.get("juntaDirectiva"));
        db.put("sucursales",_parseBranches((JSONArray)json.get("sucursales")));
        db.put("medicos",_parseDoctors((JSONArray)json.get("sucursales")));
        db.put("pacientes",_parsePatients((JSONArray)json.get("sucursales")));
        db.put("citas",_parseDates((JSONArray)json.get("sucursales")));
        db.put("historial",_parseHistory((JSONArray)json.get("sucursales")));

        json.put("loaded","true");

        try (FileWriter file = new FileWriter(DB_NAME)) {
            //We can write any JSONArray or JSONObject instance to the file
            FileWriter input = new FileWriter(FILE_NAME);
            
            file.write(db.toJSONString()); 
            file.flush();
            input.write(json.toJSONString());
            input.flush();
 
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static JSONObject _getJson(String fileName){
        JSONParser jsonParser = new JSONParser();
        JSONObject obj = new JSONObject();

        try (FileReader reader = new FileReader(fileName))
        {
            obj = (JSONObject)jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return obj;
    }
    
    private static JSONArray _parseBranches(JSONArray branches){
        JSONArray result = new JSONArray();
        for (Object curBranch : branches) {
            JSONObject branch = (JSONObject)curBranch;
            JSONObject newBranch = new JSONObject();
            JSONArray doctorList = getOnlyField((JSONArray)branch.get("medicos"),"id");
            JSONArray patientList = getOnlyField((JSONArray)branch.get("pacientes"),"cedula");

            newBranch.put("nombre",(String)branch.get("nombre"));
            newBranch.put("medicos",doctorList);
            newBranch.put("pacientes",patientList);
            newBranch.put("direccion","");
            result.add(newBranch);
        }
        return result;
    }

    private static JSONArray _parseDoctors(JSONArray branches){
        JSONArray result = new JSONArray();
        for (Object curBranch : branches) {
            JSONObject branch = (JSONObject)curBranch;
            for (Object rawDoctor : (JSONArray)branch.get("medicos")){
                JSONObject doctor = (JSONObject)rawDoctor;
                doctor.put("sucursal",branch.get("nombre"));
                doctor.put("apellido", ""); 
                doctor.put("cedula",""); 
                doctor.put("sexo",""); 
                doctor.put("lugarDeNacimiento",""); 
                doctor.put("estadoCivil",""); 
                doctor.put("direccionDeHabitacion",""); 
                doctor.put("telefono",""); 
                doctor.put("profesion","medico"); 
                doctor.put("ocupacion","medico"); 

                result.add(doctor);
            }
        }
        return result;
    }

    private static JSONArray _parsePatients(JSONArray branches){
        JSONArray result = new JSONArray();
        for (Object curBranch : branches) {
            JSONObject branch = (JSONObject)curBranch;
            for (Object rawPatient : (JSONArray)branch.get("pacientes")){
                JSONObject patient = (JSONObject)rawPatient;
                JSONObject newPatient = new JSONObject();

                newPatient.put("cedula",patient.get("cedula"));
                newPatient.put("nombre",patient.get("nombre"));
                newPatient.put("sucursal",branch.get("nombre"));
                newPatient.put("citas",getOnlyField((JSONArray)patient.get("citas"),"id"));
                newPatient.put("historial",getOnlyField((JSONArray)patient.get("historial"),"id"));
                newPatient.put("tlfPersonaRelacionada","");
                newPatient.put("estadoCivil","");
                newPatient.put("ocupacion","");
                newPatient.put("nombre","");
                newPatient.put("fechaDeNacimiento","");
                newPatient.put("direccionDeHabitacion","");
                newPatient.put("apellido","");
                newPatient.put("lugarDeNacimiento","");
                newPatient.put("sexo","");
                newPatient.put("telefono","");
                newPatient.put("profesion","");
                result.add(newPatient);
            }
        }
        return result;
    }

    private static JSONArray _parseDates(JSONArray branches){
        JSONArray result = new JSONArray();
        for (Object curBranch : branches) {
            JSONObject branch = (JSONObject)curBranch;
            for (Object rawPatient : (JSONArray)branch.get("pacientes")){
                JSONObject patient = (JSONObject)rawPatient;

                for (Object rawDate : (JSONArray)patient.get("citas")){
                    JSONObject date = (JSONObject)rawDate;
                    date.put("paciente",patient.get("cedula"));
                    date.put("sucursal",branch.get("nombre"));
                    date.put("solicitudCancelar",false);
                    date.put("registered",false);
                    date.put("fechaModificacion","");
                    result.add(date);
                }
            }
        }
        return result;
    }

    private static JSONArray _parseHistory(JSONArray branches){
        JSONArray result = new JSONArray();
        for (Object curBranch : branches) {
            JSONObject branch = (JSONObject)curBranch;
            for (Object rawPatient : (JSONArray)branch.get("pacientes")){
                JSONObject patient = (JSONObject)rawPatient;

                for (Object rawHistory : (JSONArray)patient.get("historial")){
                    JSONObject history = (JSONObject)rawHistory;
                    JSONArray dates = _flatHistoryDates((JSONArray)history.get("citas"));
                    JSONObject newHistory = new JSONObject();

                    newHistory.put("id",history.get("id"));
                    newHistory.put("paciente",patient.get("cedula"));
                    newHistory.put("sucursal",branch.get("nombre"));
                    newHistory.put("dates",dates);
                    result.add(newHistory);
                }
            }
        }
        return result;
    }

    private static JSONArray _flatHistoryDates(JSONArray dates) {
        JSONArray result = new JSONArray();
        for (Object rawDate:dates){
            JSONObject date = (JSONObject)rawDate;
            JSONObject values = (JSONObject)date.get("valores");
            JSONObject tension = (JSONObject)values.get("tension");
            JSONObject newDate = new JSONObject();

            newDate.put("id",(String)date.get("id"));
            newDate.put("peso",(String)values.get("peso"));
            newDate.put("talla",(String)values.get("talla"));
            newDate.put("pesoTalla",(String)values.get("pesoTalla"));
            newDate.put("maxima",(String)tension.get("maxima"));
            newDate.put("minima",(String)tension.get("minima"));

            result.add(newDate);
        }
        return result;
    }
    
    // field: field of json to search
    // key: field of obj to check
    // value: value to compare
    public static JSONObject getUniversal(String field, String key, String value){
        JSONObject json = _getJson(DB_NAME);
        JSONArray fieldJson = (JSONArray) json.get(field);

        for (Object obj : fieldJson) {
            String curValue = (String) ((JSONObject)obj).get(key);  
            System.out.println(curValue);
            System.out.println(value);
            System.out.println(curValue.length());
            System.out.println(value.length());
            if (curValue != null && curValue.equals(value)){
                return ((JSONObject)obj);
            }
        }
        return null;
    }

    public static JSONArray getAllUniversal(String field, String key, String value){
        JSONObject json = _getJson(DB_NAME);
        JSONArray fieldJson = (JSONArray) json.get(field);
        JSONArray result = new JSONArray();

        for (Object obj : fieldJson) {
            String curValue = (String) ((JSONObject)obj).get(key);  
            if (curValue.equals(value))
                result.add((JSONObject)obj);
        }
        return result;
    }

    public static JSONArray getField(String field){
        JSONObject json = _getJson(DB_NAME);
        return (JSONArray) json.get(field);
    }
    
    public static void setUniversal(JSONObject newObject, String field, String idKeyName, String id){
        System.out.print("a \n");
        JSONObject json = _getJson(DB_NAME);
        JSONArray arrayToModify =(JSONArray) json.get(field);
        int index = 0,found = 0;
        System.out.print(field+" "+json.get(field)+"\n");

        for (Object obj : arrayToModify) {
            String curValue = (String) ((JSONObject)obj).get(idKeyName);  
            System.out.print(obj);
            if (curValue != null && curValue.equals(id)){
                found = 1;
                break;
            }
            index++;
        }
        
        System.out.print("c \n");

        if (found == 1)
            arrayToModify.set(index,newObject);
        else 
            arrayToModify.add(newObject);
        System.out.print("e \n");
        json.put(field, arrayToModify);

        try {
            FileWriter file;
            file = new FileWriter(DB_NAME);
            file.write(json.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void filterField(String field, String key, String value) {
        JSONObject json = _getJson(DB_NAME);
        JSONArray arrayToModify =(JSONArray) json.get(field);

        for (Object obj : arrayToModify) {
            String curValue = (String) ((JSONObject)obj).get(key);  
            if (curValue.equals(value))
                arrayToModify.remove(obj);
        }

        json.put(field, arrayToModify);

        try {
            FileWriter file;
            file = new FileWriter(DB_NAME);
            file.write(json.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray getOnlyField(JSONArray arr,String field){
        JSONArray result = new JSONArray();

        for (Object raw : arr){
            JSONObject object = (JSONObject)raw;
            result.add(object.get(field));
        }
        
        return result;
    }
}
