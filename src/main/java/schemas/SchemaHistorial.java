
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
package schemas;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import utils.JsonWrapper;

public class SchemaHistorial {
    
    String ID;
    float peso;
    float talla;
    float indiceMasaCorporal;
    int  tensionDiastolica;
    int tensionSistolica;
    int pulso;    
    
    public SchemaHistorial(String historialID){
        
    }

    public float getPeso(){
        return peso;
    }

    public void setPeso(float _peso){
        peso = _peso;
    }
    
    public float getTalla(){
        return talla;
    }

    public void setTalla(float _talla){
        talla = _talla;
    }
    
    public float getIMC(){
        return indiceMasaCorporal;
    }

    public void setIMC(float _IMC){
        indiceMasaCorporal = _IMC;
    }
    
    public int getTensionDiastolica(){
        return tensionDiastolica;
    }

    public void setTensionDiastolica(int _tensionDiastolica){
        tensionDiastolica = _tensionDiastolica;
    }
    
    public int getTensionSistolica(){
        return tensionSistolica;
    }
    
    public void setTensionSistolica(int _tensionSistolica){
        tensionSistolica = _tensionSistolica;
    }
    
    public int getPulso(){
        return pulso;
    }

    public void setPulso(int _pulso){
        pulso = _pulso;
    }
    
    public void commit(){
        JSONObject historial = new JSONObject(); 
        historial.put("peso",peso);
        historial.put("talla",talla);
        historial.put("indiceMasaCorporal",indiceMasaCorporal);
        historial.put("tensionDiastolica",tensionDiastolica);
        historial.put("tensionSistolica",tensionSistolica);
        JsonWrapper.setUniversal(historial, "historial", "id", ID);
    }
}
