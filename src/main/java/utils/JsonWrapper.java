/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
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
        JSONObject json = _getJson();
        JSONArray board = (JSONArray) json.get("juntaDirectiva");
        return board;
    }

    public static JSONObject getBranchOffice(String name) {
        JSONObject json = _getJson();
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
        JSONObject json = _getJson();
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
        JSONObject json = _getJson();
        
        if (json.get("loaded") == "true")
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

    private static JSONObject _getJson(){
        JSONParser jsonParser = new JSONParser();
        JSONObject obj = new JSONObject();

        try (FileReader reader = new FileReader(FILE_NAME))
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
    
        private static JSONObject _getDB(){
        JSONParser jsonParser = new JSONParser();
        JSONObject obj = new JSONObject();

        try (FileReader reader = new FileReader(FILE_NAME))
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
            JSONArray doctorList = _getOnlyField((JSONArray)branch.get("medicos"),"id");
            JSONArray patientList = _getOnlyField((JSONArray)branch.get("pacientes"),"cedula");

            newBranch.put("nombre",(String)branch.get("nombre"));
            newBranch.put("medicos",doctorList);
            newBranch.put("pacientes",patientList);
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
                newPatient.put("citas",_getOnlyField((JSONArray)patient.get("citas"),"id"));
                newPatient.put("historial",_getOnlyField((JSONArray)patient.get("historial"),"id"));
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
                    history.put("paciente",patient.get("cedula"));
                    history.put("sucursal",branch.get("nombre"));
                    result.add(history);
                }
            }
        }
        return result;
    }
    
        public static JSONObject getUniversal(String ID, String variableToModify){
        JSONObject json = _getDB();
        JSONArray historiales = (JSONArray) json.get(variableToModify);

        for (Object obj : historiales) {
            String historialID = (String) ((JSONObject)obj).get("ID");  
            if (historialID.equals(ID)){
                return ((JSONObject)obj);
            }
        }
        return null;
    }
    
    public static void setUniversal(JSONObject newObject, String variableToModify){
        JSONObject json = _getDB();
        JSONArray arrayToModify;
        arrayToModify = (JSONArray) json.get(variableToModify);
        int i=0;
        boolean added = false;
        System.out.println(arrayToModify);
        if(arrayToModify != null){
            for (Object obj : arrayToModify) {
                System.out.println(((JSONObject)obj).get("ID"));
                String ID = (String) ((JSONObject)obj).get("ID");                
                if (ID.equals(newObject.get("ID"))){
                    System.out.println("added si");
                    arrayToModify.set(i, newObject);
                    added = true;
                }
                i++;
            }
            if(added == false){
                System.out.println("added no");
                arrayToModify.add(newObject);
            }
            json.put(variableToModify, arrayToModify);
        }else{
            JSONArray historialNewArray = new JSONArray();
            historialNewArray.add(newObject);
            json.put(variableToModify, historialNewArray);
        }

        System.out.println(arrayToModify);
        try {
            FileWriter file;
            file = new FileWriter(DB_NAME);
            file.write(json.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray _getOnlyField(JSONArray arr,String field){
        JSONArray result = new JSONArray();

        for (Object raw : arr){
            JSONObject object = (JSONObject)raw;
            result.add(object.get(field));
        }
        
        return result;
    }
}
