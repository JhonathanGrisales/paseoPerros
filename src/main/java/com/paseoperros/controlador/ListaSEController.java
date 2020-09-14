/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paseoperros.controlador;

import co.edu.umanizales.listase.modelo.ListaSE;
import co.edu.umanizales.listase.modelo.Nodo;
import co.edu.umanizales.listase.modelo.Perro;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;

/**
 *
 * @author carloaiza
 */
@Named(value = "listaSEController")
@SessionScoped
public class ListaSEController implements Serializable {

    private ListaSE listaPerros;

    private Perro perroMostrar;

    private Nodo temp;

    private String mesg;

    /**
     * Creates a new instance of ListaSEController
     */
    public ListaSEController() {
    }

    @PostConstruct
    private void iniciar() {
        listaPerros = new ListaSE();
        //// Conectaría a un archivo plano o a una base de datos para llenar la 
        //lista de perros
        listaPerros.adicionarNodo(new Perro("Pastor", (byte) 1, (byte) 3, "M"));
        listaPerros.adicionarNodo(new Perro("Lulú", (byte) 2, (byte) 4, "F"));
        listaPerros.adicionarNodo(new Perro("Firulais", (byte) 3, (byte) 6, "M"));

        listaPerros.adicionarNodoAlInicio(new Perro("Lola", (byte) 4, (byte) 5, "F"));
        perroMostrar = listaPerros.getCabeza().getDato();
        temp = listaPerros.getCabeza();

    }

    public Nodo getTemp() {
        return temp;
    }

    public void setTemp(Nodo temp) {
        this.temp = temp;
    }

    public Perro getPerroMostrar() {
        return perroMostrar;
    }

    public void setPerroMostrar(Perro perroMostrar) {
        this.perroMostrar = perroMostrar;
    }

    public ListaSE getListaPerros() {
        return listaPerros;
    }

    public void setListaPerros(ListaSE listaPerros) {
        this.listaPerros = listaPerros;
    }

    public void irSiguiente() {
        //if(temp.getSiguiente()!=null)
        //{
        temp = temp.getSiguiente();
        perroMostrar = temp.getDato();
        //}
    }

    public void irPrimero() {
        temp = listaPerros.getCabeza();
        perroMostrar = temp.getDato();
    }

    public void irUltimo() {

        temp = listaPerros.getCabeza();
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        /// Parado en el último nodo
        perroMostrar = temp.getDato();
    }

    public void invertir() {
        listaPerros.invertir();
        irPrimero();
    }

    public void intercambiar() {
        listaPerros.intercambiarExtremos();
        irPrimero();
    }

    //METODO PARA MOSTRAR LOS DATOS DE LA LISTA
    public void mostrarLista() {

        Nodo recorrer = temp;
        while (recorrer != null) {

            System.out.println("[ " + recorrer.getDato() + "]");
            recorrer = recorrer.getSiguiente();

        }

    }

    public void borrarId() {

        ExternalContext x = FacesContext.getCurrentInstance().getExternalContext();
        int ide;
        ide = Integer.parseInt(x.getRequestParameterMap().get("Formulario:Borrar"));

        temp = listaPerros.getCabeza();

        while (temp != null && temp.getDato().getNumero() != ide) {

            temp = temp.getSiguiente();
        }

        if (temp != null && temp.getDato().getNumero() == ide) {

            listaPerros.eliminarNodoporId(ide);
            irPrimero();
        } else if (temp == null) {

            System.out.println("");

            mostrarMensaje();

        }

    }

    public void ordenarMacho() {

        listaPerros.ordenarMasculino();
        irPrimero();
    }

    public void ordenarHembra() {

        listaPerros.ordenarFemenino();
        irPrimero();
    }

    public void mostrarMensaje() {

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Elemento no encontrado"));
    }

    public void filtrar() {

        ExternalContext x = FacesContext.getCurrentInstance().getExternalContext();
        byte ide;
        ide = Byte.parseByte(x.getRequestParameterMap().get("Formulario:Filtrar"));

        temp = listaPerros.getCabeza();

        while (temp != null && temp.getDato().getNumero() != ide) {

            temp = temp.getSiguiente();
        }

        if (temp != null && temp.getDato().getNumero() == ide) {

            Nodo filtro = temp;

            perroMostrar = temp.getDato();

        } else if (temp == null) {

            System.out.println("");

            mostrarMensaje();

        }

    }
}
