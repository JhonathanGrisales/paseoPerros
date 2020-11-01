/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paseoperros.controlador;

import co.edu.umanizales.listase.modelo.Infante;
import co.edu.umanizales.listase.modelo.ListaDECInfante;
import co.edu.umanizales.listase.modelo.ListaDECircular;
import co.edu.umanizales.listase.modelo.NodoDE;
import co.edu.umanizales.listase.modelo.NodoDEInfante;
import co.edu.umanizales.listase.modelo.Perro;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.PostConstruct;
import static org.primefaces.component.barchart.BarChartBase.PropertyKeys.model;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author Jhony
 */
@Named(value = "controladorTingoTango")
@SessionScoped
public class ControladorTingoTango implements Serializable {

    private Infante infanteGuardar;
    private List<Infante> listadoInfantes;
    private ListaDECircularCrontroller tingoTango;
    private boolean estadoJuego;
    private byte cantidadInfantes;
    private byte numOportunidades;
    private byte posIgreso;
    private NodoDEInfante ayudante;
    private ListaDECInfante listaNiñosDEC;
    private DashboardModel model;
    private String sexo;

    /**
     * Creates a new instance of ControladorTingoTango
     */
    public ControladorTingoTango() {
    }

    @PostConstruct

    private void iniciarDEC() {

        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();

        column1.addWidget("acciones");
        column2.addWidget("lista");

        model.addColumn(column1);
        model.addColumn(column2);

        listaNiñosDEC = new ListaDECInfante();

//        listaNiñosDEC.adicionarNiñoAlinicioDeCircular(new Infante("Jero", true, (byte) 12));
//        listaNiñosDEC.adicionarNiñoAlinicioDeCircular(new Infante("Mateo", true, (byte) 10));
//        listaNiñosDEC.adicionarNiñoAlinicioDeCircular(new Infante("Mateo", true, (byte) 10));
//        infanteGuaradar = listaNiñosDEC.getCabezaInfante().getDato();
        ayudante = listaNiñosDEC.getCabezaInfante();

        listadoInfantes = new ArrayList<>();

    }

    //GET Y SET
    public void setModel(DashboardModel model) {
        this.model = model;

    }

    public DashboardModel getModel() {
        return model;
    }

    public Infante getInfanteGuardar() {
        return infanteGuardar;
    }

    public void setInfanteGuardar(Infante infanteGuardar) {
        this.infanteGuardar = infanteGuardar;
    }

    public List<Infante> getListadoInfantes() {
        return listadoInfantes;
    }

    public void setListadoInfantes(List<Infante> listadoInfantes) {
        this.listadoInfantes = listadoInfantes;
    }

    public ListaDECircularCrontroller getTingoTango() {
        return tingoTango;
    }

    public void setTingoTango(ListaDECircularCrontroller tingoTango) {
        this.tingoTango = tingoTango;
    }

    public boolean isEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(boolean estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    public byte getCantidadInfantes() {
        return cantidadInfantes;
    }

    public void setCantidadInfantes(byte cantidadInfantes) {
        this.cantidadInfantes = cantidadInfantes;
    }

    public byte getNumOportunidades() {
        return numOportunidades;
    }

    public void setNumOportunidades(byte numOportunidades) {
        this.numOportunidades = numOportunidades;
    }

    public byte getPosIgreso() {
        return posIgreso;
    }

    public void setPosIgreso(byte posIgreso) {
        this.posIgreso = posIgreso;
    }

    public NodoDEInfante getAyudante() {
        return ayudante;
    }

    public void setAyudante(NodoDEInfante ayudante) {
        this.ayudante = ayudante;
    }

    public ListaDECInfante getListaNiñosDEC() {
        return listaNiñosDEC;
    }

    public void setListaNiñosDEC(ListaDECInfante listaNiñosDEC) {
        this.listaNiñosDEC = listaNiñosDEC;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    //GET Y SET
    public String irCrearNiño() {

        infanteGuardar = new Infante();
        return "crearniño";
    }

    public String irHome() {

        infanteGuardar = new Infante();
        return "tingotango";
    }

    public int contarInfantes() {

        int tamanio = listadoInfantes.size();

        return tamanio;

    }

    public void adicionarArray() {

        listadoInfantes.add(infanteGuardar);
        infanteGuardar = new Infante();

    }

    public void configurarJuego() {

        if (cantidadInfantes > contarInfantes()) {

            System.out.println("Mensaje");
            JsfUtil.addErrorMessage("No tiene los suficientes niños para jugar ");

        } else {

            int cont = 0;

            while (cont != cantidadInfantes) {

                for (Infante infa : listadoInfantes) {

                    if (infa instanceof Infante) {

                        listaNiñosDEC.adicionarNiñoAlinicioDeCircular(infa);
////                        listadoInfantes.remove(infa);
                        cont++;

                    }

                }

            }

        }

        listaNiñosDEC.mostrarListaDEC();

    }

}
