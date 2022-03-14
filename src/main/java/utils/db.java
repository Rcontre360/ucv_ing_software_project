/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andres
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

public class db {
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
    public static String FILE_NAME = "db.json";
     
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
    
    public static JSONObject getUniversal(String ID, String variableToModify){
        JSONObject json = _getJson();
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
        JSONObject json = _getJson();
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
            file = new FileWriter("db.json");
            file.write(json.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
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


}
