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
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;
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

    private byte filtrar;

    private int borrar;

    private int datoBuscar;

    private Perro perroEncontrado;

    private DefaultDiagramModel model;

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
        listaPerros.adicionarNodo(new Perro("Luki", (byte) 5, (byte) 9, "M"));

        listaPerros.adicionarNodoAlInicio(new Perro("Lola", (byte) 4, (byte) 5, "F"));
        perroMostrar = listaPerros.getCabeza().getDato();
        temp = listaPerros.getCabeza();
        inicializarModelo();

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

    public byte getFiltrar() {
        return filtrar;
    }

    public void setFiltrar(byte filtrar) {
        this.filtrar = filtrar;
    }

    public int getBorrar() {
        return borrar;
    }

    public void setBorrar(int borrar) {
        this.borrar = borrar;
    }

    public int getDatoBuscar() {
        return datoBuscar;
    }

    public void setDatoBuscar(int datoBuscar) {
        this.datoBuscar = datoBuscar;
    }

    public Perro getPerroEncontrado() {
        return perroEncontrado;
    }

    public void setPerroEncontrado(Perro perroEncontrado) {
        this.perroEncontrado = perroEncontrado;
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
        inicializarModelo();
        pintarIntercambio();

    }

    //Probar sincronización
    public void buscarPerro() {

        perroEncontrado = listaPerros.buscarPosicion(datoBuscar);
        pintarBusqueda();

    }

    //METODO PARA MOSTRAR LOS DATOS DE LA LISTA
    public void mostrarLista() {

        Nodo recorrer = temp;
        while (recorrer != null) {

            System.out.println("[ " + recorrer.getDato() + "]");
            recorrer = recorrer.getSiguiente();

        }

    }

    public void ordenarMacho() {
        
        

        listaPerros.ordenarMasculino();
        irPrimero();
        inicializarModelo();
    }

    public void ordenarHembra() {

        listaPerros.ordenarFemenino();
        irPrimero();
        inicializarModelo();
    }

    public void mostrarMensaje() {

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Elemento no encontrado"));
    }

    public void borrarId() {

        temp = listaPerros.getCabeza();

        while (temp != null && temp.getDato().getNumero() != borrar) {

            temp = temp.getSiguiente();
            inicializarModelo();
        }

        if (temp != null && temp.getDato().getNumero() == borrar) {

            listaPerros.eliminarNodoporId(borrar);
            irPrimero();
            inicializarModelo();

        } else if (temp == null) {

            mostrarMensaje();
            irPrimero();
        }

    }

    public void inicializarModelo() {

//        model = new DefaultDiagramModel();
//        model.setMaxConnections(-1);
//
//        FlowChartConnector connector = new FlowChartConnector();
//        connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
//        model.setDefaultConnector(connector);
//
//        Element[] elemento;
//
//        for (int i = 1; i < listaPerros.contarNodos() + 1; i++) {
//
//            elemento = new Element[listaPerros.contarNodos() + 1];
//
//            elemento[i] = new Element(listaPerros.buscarPosicion(i).getNombre(), String.valueOf(16 * (i - 1)) + "em", "6em");
//            elemento[i].addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
//            elemento[i].addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
//            
//
//            model.addElement(elemento[i]);
//
//            model.connect(createConnection(elemento[1].getEndPoints().get(0), elemento[2].getEndPoints().get(0), null));
//        }
//
//            
//            Element start = new Element(listaPerros.buscarPosicion(i).getNombre(), "0em", "6em");
//            start.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
//
//            Element trouble = new Element(listaPerros.buscarPosicion(i).getNombre(), "20em", "6em");
//            trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
//            trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
//
//            
//            Element giveup = new Element(listaPerros.buscarPosicion(i).getNombre(), "40em", "6em");
//            giveup.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
//            giveup.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
//
//            Element succeed = new Element(listaPerros.buscarPosicion(i).getNombre(), "60em", "6em");
//
//            succeed.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
//
//            model.addElement(start);
//            model.addElement(trouble);
//            model.addElement(giveup);
//            model.addElement(succeed);
//
//            model.connect(createConnection(start.getEndPoints().get(0), trouble.getEndPoints().get(0), null));
//            model.connect(createConnection(trouble.getEndPoints().get(1), giveup.getEndPoints().get(0), null));
//            model.connect(createConnection(giveup.getEndPoints().get(1), succeed.getEndPoints().get(0), null));
//Instanciar el modelo
        model = new DefaultDiagramModel();
        //Definir el modelo la cantidad de enlaces -1 (Infinito)
        model.setMaxConnections(-1);
        //Pregunto si hay datos
        if (listaPerros.getCabeza() != null) {

            //Llamar ayudante y ubicar en el primero 
            Nodo ayudante = listaPerros.getCabeza();
            //Recorro mientras el ayudante tenga ddato 
            int posX = 2;
            int posY = 2;
            while (ayudante != null) {

                Element perroPintar = new Element(ayudante.getDato().getNombre(), posX + "em", posY + "em");

//                if (ayudante.getDato().getNombre().toLowerCase().startsWith("p")) {
//                    perroPintar.setStyleClass("ui-diagram-success");
//                }
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));

                model.addElement(perroPintar);

                ayudante = ayudante.getSiguiente();
                posX = posX + 5;
                posY = posY + 5;
            }
            //YA SE PINTARON TODOS LOS ELEMENTOS 

            //PROPIEDAD PARA EL CONECTOR
            FlowChartConnector connector = new FlowChartConnector();
            connector.setPaintStyle("{strokeStyle:'#092CB0',lineWidth:3}");
            model.setDefaultConnector(connector);

            for (int i = 0; i < model.getElements().size() - 1; i++) {

                model.connect(createConnection(model.getElements().get(i).getEndPoints().get(0), model.getElements().get(i + 1).getEndPoints().get(1), null));

            }
        }

        /*

        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
        model.setDefaultConnector(connector);

        Element start = new Element("Fight for your dream", "20em", "6em");
        start.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
        start.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));

        Element trouble = new Element("Do you meet some trouble?", "20em", "18em");
        trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
        trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
        trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));

        Element giveup = new Element("Do you give up?", "20em", "30em");
        giveup.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
        giveup.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        giveup.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));

        Element succeed = new Element("Succeed", "50em", "18em");
        succeed.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        succeed.setStyleClass("ui-diagram-success");

        Element fail = new Element("Fail", "50em", "30em");
        fail.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        fail.setStyleClass("ui-diagram-fail");

        model.addElement(start);
        model.addElement(trouble);
        model.addElement(giveup);
        model.addElement(succeed);
        model.addElement(fail);

        model.connect(createConnection(start.getEndPoints().get(0), trouble.getEndPoints().get(0), null));
        model.connect(createConnection(trouble.getEndPoints().get(1), giveup.getEndPoints().get(0), "Yes"));
        model.connect(createConnection(giveup.getEndPoints().get(1), start.getEndPoints().get(1), "No"));
        model.connect(createConnection(trouble.getEndPoints().get(2), succeed.getEndPoints().get(0), "No"));
        model.connect(createConnection(giveup.getEndPoints().get(2), fail.getEndPoints().get(0), "Yes"));
    
         */
    }

    public DiagramModel getModel() {
        return model;
    }

    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }

    public String irCrearPerro() {

        perroEncontrado = new Perro();
        return "crear";
    }

    public void guardarPerro() {

        
        listaPerros.adicionarNodo(perroEncontrado);
        perroEncontrado = new Perro();
        irPrimero();

    }

    public String irHome() {

        perroEncontrado = new Perro();
        inicializarModelo();
        return "home";
    }

    public void pintarBusqueda() {
        model = new DefaultDiagramModel();
        //Definir el modelo la cantidad de enlaces -1 (Infinito)
        model.setMaxConnections(-1);
        //Pregunto si hay datos
        if (listaPerros.getCabeza() != null) {

            //Llamar ayudante y ubicar en el primero 
            Nodo ayudante = listaPerros.getCabeza();
            //Recorro mientras el ayudante tenga ddato 
            int posX = 2;
            int posY = 2;
            while (ayudante != null) {

                Element perroPintar = new Element(ayudante.getDato().getNombre(), posX + "em", posY + "em");

                if (ayudante.getDato().getNumero() == perroEncontrado.getNumero()) {
                    perroPintar.setStyleClass("ui-diagram-success");
                }
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));

                model.addElement(perroPintar);

                ayudante = ayudante.getSiguiente();
                posX = posX + 5;
                posY = posY + 5;
            }
            //YA SE PINTARON TODOS LOS ELEMENTOS 

            //PROPIEDAD PARA EL CONECTOR
            FlowChartConnector connector = new FlowChartConnector();
            connector.setPaintStyle("{strokeStyle:'#092CB0',lineWidth:3}");
            model.setDefaultConnector(connector);

            for (int i = 0; i < model.getElements().size() - 1; i++) {

                model.connect(createConnection(model.getElements().get(i).getEndPoints().get(0), model.getElements().get(i + 1).getEndPoints().get(1), null));

            }
        }
    }

    public void pintarIntercambio() {
        model = new DefaultDiagramModel();
        //Definir el modelo la cantidad de enlaces -1 (Infinito)
        model.setMaxConnections(-1);
        //Pregunto si hay datos
        if (listaPerros.getCabeza() != null) {

            //Llamar ayudante y ubicar en el primero 
            Nodo ayudante = listaPerros.getCabeza();
            //Recorro mientras el ayudante tenga ddato 
            int posX = 2;
            int posY = 2;
            while (ayudante != null) {

                Element perroPintar = new Element(ayudante.getDato().getNombre(), posX + "em", posY + "em");

                if (ayudante == temp || ayudante.getSiguiente() == null) {

                    perroPintar.setStyleClass("ui-diagram-success");
                }
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));

                model.addElement(perroPintar);

                ayudante = ayudante.getSiguiente();
                posX = posX + 5;
                posY = posY + 5;
            }
            //YA SE PINTARON TODOS LOS ELEMENTOS 

            //PROPIEDAD PARA EL CONECTOR
            FlowChartConnector connector = new FlowChartConnector();
            connector.setPaintStyle("{strokeStyle:'#092CB0',lineWidth:3}");
            model.setDefaultConnector(connector);

            for (int i = 0; i < model.getElements().size() - 1; i++) {

                model.connect(createConnection(model.getElements().get(i).getEndPoints().get(0), model.getElements().get(i + 1).getEndPoints().get(1), null));

            }
        }
    }

}
