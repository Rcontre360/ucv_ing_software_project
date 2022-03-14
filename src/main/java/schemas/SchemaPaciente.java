/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schemas;

/**
 *
 * @author andres
 */
public class SchemaPaciente {
    
    String ID;
    int tlfPersonaRelacionada;
    
    public SchemaPaciente(String pacienteID){
        
    }

    public int gettlfPersonaRelacionada(){
        return tlfPersonaRelacionada;
    }

    public void settlfPersonaRelacionada(int _tlfPersonaRelacionada){
        tlfPersonaRelacionada = _tlfPersonaRelacionada;
    }

}
