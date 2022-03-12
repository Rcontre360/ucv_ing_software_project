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
    public static String FILE_NAME = "formato.json";
     
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
