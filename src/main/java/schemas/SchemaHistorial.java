
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
package schemas;

import java.util.*;
import org.json.simple.JSONObject;

import utils.JsonWrapper;

public class SchemaHistorial {
    
    String ID;
    int peso;
    float talla;
    float indiceMasaCorporal;
    String tensionDiastolica;
    String tensionSistolica;
    float pulso;    
    
    public SchemaHistorial(String historialID){
        
    }

    public int getPeso(){
        return peso;
    }

    public void setPeso(int _peso){
        peso = _peso;
    }
    
    public float getTalla(){
        return talla;
    }

    public void setTalla(int _talla){
        talla = _talla;
    }
    
    public float getIMC(){
        return indiceMasaCorporal;
    }

    public void setIMC(int _IMC){
        indiceMasaCorporal = _IMC;
    }
    
    public String getTensionDiastolica(){
        return tensionDiastolica;
    }

    public void setTensionDiastolica(String _tensionDiastolica){
        tensionDiastolica = _tensionDiastolica;
    }
    
    public String getTensionSistolica(){
        return tensionSistolica;
    }
    
    public void setTensionSistolica(String _tensionSistolica){
        tensionSistolica = _tensionSistolica;
    }
    
    public float getPulso(){
        return pulso;
    }

    public void setPulso(float _pulso){
        pulso = _pulso;
    }
}
