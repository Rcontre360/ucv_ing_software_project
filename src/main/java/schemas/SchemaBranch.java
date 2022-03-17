
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
package schemas;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import utils.JsonWrapper;

public class SchemaBranch {

    private String direccion;
    private String nombre;
    private int limite;
    private int capacidad = 1000000;
    
    public SchemaBranch(String branchName){ //if branch name isnt null, search on db
        nombre = branchName;
        if (branchName.length() > 0) {
            JSONObject branch = JsonWrapper.getUniversal("sucursales","nombre",branchName);
            if (branch != null){
                int limit = ((Long)branch.get("limite")).intValue();
                int capacity = ((Long)branch.get("capacidad")).intValue();

                direccion = (String)branch.get("direccion");
                limite = limit;
                capacidad = capacity;
            }
        }
    }

    public String getDireccion(){
        return direccion;
    }

    public void  setDireccion(String _direccion){
        direccion = _direccion;
    }

    public int getLimite(){
        return limite;
    }

    public void  setLimite(int _limite){
        limite = _limite;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public void  setCapacidad(int _capacidad){
        capacidad = _capacidad;
    }

    public void commit(){
        JSONObject branch = new JSONObject();
        branch.put("direccion",direccion);
        branch.put("limite",limite);
        branch.put("capacidad",capacidad);
        branch.put("nombre",nombre);
        branch.put("medicos",new JSONArray());
        branch.put("pacientes",new JSONArray());
        JsonWrapper.setUniversal(branch, "sucursales", "nombre", nombre);
    }
}
