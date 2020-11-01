/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paseoperros.controlador;

import co.edu.umanizales.listase.modelo.ListaDECircular;
import co.edu.umanizales.listase.modelo.NodoDE;
import co.edu.umanizales.listase.modelo.Perro;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;

/**
 *
 * @author Jhony
 */
@Named(value = "listaDECircularCrontroller")
@SessionScoped
public class ListaDECircularCrontroller implements Serializable {

    private ListaDECircular listaPerrosDEC;
    private Perro perroMostrarDEC;
    private NodoDE tempDEC;
    private String sentencia;

    /**
     * Creates a new instance of ListaDECircularCrontroller
     */
    public ListaDECircularCrontroller() {
    }

    @PostConstruct
    private void iniciarDE() {

        listaPerrosDEC = new ListaDECircular();

        listaPerrosDEC.adicionarAlinicioDeCircular(new Perro("Luki", (byte)6 , (byte) 8, "F"));
        listaPerrosDEC.adicionarAlinicioDeCircular(new Perro("Pastor", (byte) 5, (byte) 7, "M"));
        listaPerrosDEC.adicionarAlinicioDeCircular(new Perro("Firulais", (byte) 4, (byte) 8, "M"));
        listaPerrosDEC.adicionarAlinicioDeCircular(new Perro("Kike", (byte) 3, (byte) 3, "M"));
        listaPerrosDEC.adicionarAlinicioDeCircular(new Perro("Princesa", (byte) 2, (byte) 5, "F"));
        listaPerrosDEC.adicionarAlinicioDeCircular(new Perro("Lulu", (byte) 1, (byte) 1, "F"));
      
        
        
        perroMostrarDEC = listaPerrosDEC.getCabezaDeC().getDato();

        tempDEC = listaPerrosDEC.getCabezaDeC();

    }

    public ListaDECircular getListaPerrosDEC() {
        return listaPerrosDEC;
    }

    public void setListaPerrosDEC(ListaDECircular listaPerrosDEC) {
        this.listaPerrosDEC = listaPerrosDEC;
    }

    public Perro getPerroMostrarDEC() {
        return perroMostrarDEC;
    }

    public void setPerroMostrarDEC(Perro perroMostrarDEC) {
        this.perroMostrarDEC = perroMostrarDEC;
    }

    public NodoDE getTempDEC() {
        return tempDEC;
    }

    public void setTempDEC(NodoDE tempDEC) {
        this.tempDEC = tempDEC;
    }

    public String getSentencia() {
        return sentencia;
    }

    public void setSentencia(String sentencia) {
        this.sentencia = sentencia;
    }
    
    
    

    public void irPriemroDeC() {
        

        if (listaPerrosDEC.getCabezaDeC() != null) {
            
            tempDEC = listaPerrosDEC.getCabezaDeC();
            
            perroMostrarDEC = tempDEC.getDato();

        } else {

            JsfUtil.addErrorMessage("No existen datos en la lista");
        }

    }

    public void irAnterior() {

        if (listaPerrosDEC.getCabezaDeC() != null) {

            tempDEC = tempDEC.getAnteriorDE();
            perroMostrarDEC = tempDEC.getDato();

        } else {

            JsfUtil.addErrorMessage("Si datos");
        }

    }

    public void irSiguiente() {

        if (listaPerrosDEC.getCabezaDeC() != null) {

            tempDEC = tempDEC.getSiguienteDE();
            perroMostrarDEC = tempDEC.getDato();

        } else {

            JsfUtil.addErrorMessage("Sin datos");
        }

    }

    public void irUltimo() {

        tempDEC = listaPerrosDEC.getCabezaDeC();

        while (tempDEC.getAnteriorDE() != listaPerrosDEC.getCabezaDeC() ) {
            
            tempDEC = tempDEC.getSiguienteDE();
        }
        /// Parado en el último nodo
        perroMostrarDEC = tempDEC.getDato();
    }
    
    public void sumarEdades(){
    
    
    int numero = listaPerrosDEC.sumar(sentencia);
    JsfUtil.addSuccessMessage("la suma de las edades: " + String.valueOf(numero));
    
}
    
    

    public String irHome() {
        return "listadec";
    }

}
