/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paseoperros.controlador;

import co.edu.umanizales.listase.modelo.ListaDE;
import co.edu.umanizales.listase.modelo.Nodo;
import co.edu.umanizales.listase.modelo.NodoDE;
import co.edu.umanizales.listase.modelo.Perro;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author Jhony
 */
@Named(value = "listaDEController")
@SessionScoped
public class ListaDEController implements Serializable {

    private ListaDE listaPerrosDE;

    private Perro perroMostrarDE;

    private NodoDE tempDE;

    private int borrarPosicion;

    private int posicionUno;

    private int posicionDos;

    private Perro perroEncontradoDE;

    private int datoBuscarDE;

    private int posicion;

    private DefaultDiagramModel model;

    private int seleccionUbicacionDE = 0;

    /**
     * Creates a new instance of ListaDEController
     */
    public ListaDEController() {
    }

    @PostConstruct
    private void iniciarDE() {

        listaPerrosDE = new ListaDE();

        listaPerrosDE.adicionarNodo(new Perro("Pastor", (byte) 1, (byte) 5, "M"));
        listaPerrosDE.adicionarNodo(new Perro("Firulais", (byte) 2, (byte) 4, "M"));
        listaPerrosDE.adicionarNodo(new Perro("Kuki", (byte) 3, (byte) 7, "F"));
        listaPerrosDE.adicionarNodo(new Perro("Princesa", (byte) 4, (byte) 9, "F"));

        perroMostrarDE = listaPerrosDE.getCabezaDe().getDato();

        tempDE = listaPerrosDE.getCabezaDe();

        inicializarModelo();

    }

    /////////// Get y Set
    public ListaDE getListaPerrosDE() {
        return listaPerrosDE;
    }

    public void setListaPerrosDE(ListaDE listaPerrosDE) {
        this.listaPerrosDE = listaPerrosDE;
    }

    public Perro getPerroMostrarDE() {
        return perroMostrarDE;
    }

    public void setPerroMostrarDE(Perro perroMostrarDE) {
        this.perroMostrarDE = perroMostrarDE;
    }

    public NodoDE getTempDE() {
        return tempDE;
    }

    public void setTempDE(NodoDE tempDE) {
        this.tempDE = tempDE;
    }

    public int getBorrarPosicion() {
        return borrarPosicion;
    }

    public void setBorrarPosicion(int borrarPosicion) {
        this.borrarPosicion = borrarPosicion;
    }

    public int getPosicionUno() {
        return posicionUno;
    }

    public void setPosicionUno(int posicionUno) {
        this.posicionUno = posicionUno;
    }

    public int getPosicionDos() {
        return posicionDos;
    }

    public void setPosicionDos(int posicionDos) {
        this.posicionDos = posicionDos;
    }

    public Perro getPerroEncontradoDE() {
        return perroEncontradoDE;
    }

    public void setPerroEncontradoDE(Perro perroEncontradoDE) {
        this.perroEncontradoDE = perroEncontradoDE;
    }

    public int getDatoBuscarDE() {
        return datoBuscarDE;
    }

    public void setDatoBuscarDE(int datoBuscarDE) {
        this.datoBuscarDE = datoBuscarDE;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

    public int getSeleccionUbicacionDE() {
        return seleccionUbicacionDE;
    }

    public void setSeleccionUbicacionDE(int seleccionUbicacionDE) {
        this.seleccionUbicacionDE = seleccionUbicacionDE;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /////////// Get y Set
    public String irCrearPerroDE() {

        perroEncontradoDE = new Perro();
        posicion = 1;
        seleccionUbicacionDE = 0;
        return "crearDE";
    }

    public void guardarPerroDE() {

        switch (seleccionUbicacionDE) {

            case 1:
                listaPerrosDE.adicionarAlInicio(perroEncontradoDE);
                posicion = 1;
                seleccionUbicacionDE = 0;
                break;
            case 2:
                listaPerrosDE.adicionarNodo(perroEncontradoDE);
                posicion = 1;
                seleccionUbicacionDE = 0;
                break;
            case 3:
                listaPerrosDE.adicionarNodoEnSPosicion(perroEncontradoDE, posicion);
                posicion = 1;
                seleccionUbicacionDE = 0;
                break;
            default:
                listaPerrosDE.adicionarNodo(perroEncontradoDE);
                posicion = 1;
                seleccionUbicacionDE = 0;

        }

        perroEncontradoDE = new Perro();
        JsfUtil.addSuccessMessage("Se ha adicionado el perro a la lista");
        irPriemroDe();

    }

    public String irHomeDE() {

        perroEncontradoDE = new Perro();
        inicializarModelo();
        return "listade";
    }

    public void irPriemroDe() {

        if (listaPerrosDE.getCabezaDe() != null) {
            tempDE = listaPerrosDE.getCabezaDe();
            perroMostrarDE = tempDE.getDato();

        } else {

            JsfUtil.addErrorMessage("No existen datos en la lista");
        }

    }

    public void irAnterior() {

        if (listaPerrosDE.getCabezaDe() != null) {

            tempDE = tempDE.getAnteriorDE();
            perroMostrarDE = tempDE.getDato();

        } else {

            JsfUtil.addErrorMessage("Si datos");
        }

    }

    public void irSiguiente() {

        if (listaPerrosDE.getCabezaDe() != null) {

            tempDE = tempDE.getSiguienteDE();
            perroMostrarDE = tempDE.getDato();

        } else {

            JsfUtil.addErrorMessage("Sin datos");
        }

    }

    public void irUltimo() {

        tempDE = listaPerrosDE.getCabezaDe();

        while (tempDE.getSiguienteDE() != null) {
            tempDE = tempDE.getSiguienteDE();
        }
        /// Parado en el último nodo
        perroMostrarDE = tempDE.getDato();
    }

    public void invertir() {
        listaPerrosDE.invertirDE();
        irPriemroDe();
        inicializarModelo();
    }

    public void intercambiar() {
        listaPerrosDE.intercambiarExtremosDE();
        irPriemroDe();
        inicializarModelo();
        pintarIntercambio();

    }

    public void ordenarMachoDE() {

        if (listaPerrosDE.getCabezaDe() != null) {

            int cont = 0;
            tempDE = listaPerrosDE.getCabezaDe();

            while (tempDE != null && cont <= 1) {

                if (tempDE.getDato().getSexo().equals("M")) {
                    cont = 1;
                }
                tempDE = tempDE.getSiguienteDE();
            }

            tempDE = listaPerrosDE.getCabezaDe();

            if (cont == 0) {

                JsfUtil.addErrorMessage("No hay Machos");

            } else if (cont >= 1 && tempDE.getSiguienteDE() != null) {

                listaPerrosDE.ordenarMasculinoDE();
                irPriemroDe();
                inicializarModelo();

            } else if (cont == 1 && tempDE.getSiguienteDE() == null) {

                JsfUtil.addErrorMessage("Solo hay un elemento");

            }

        } else {

            JsfUtil.addErrorMessage("No hay Elementos");
        }

    }

    public void ordenarHembraDE() {

        if (listaPerrosDE.getCabezaDe() != null) {

            int cont = 0;
            tempDE = listaPerrosDE.getCabezaDe();

            while (tempDE != null && cont <= 1) {

                if (tempDE.getDato().getSexo().equals("F")) {
                    cont = 1;
                }
                tempDE = tempDE.getSiguienteDE();
            }

            tempDE = listaPerrosDE.getCabezaDe();

            if (cont == 0) {

                JsfUtil.addErrorMessage("No hay Hembras");

            } else if (cont >= 1 && tempDE.getSiguienteDE() != null) {

                listaPerrosDE.ordenarFemeninoDE();
                irPriemroDe();
                inicializarModelo();

            } else if (cont == 1 && tempDE.getSiguienteDE() == null) {

                JsfUtil.addErrorMessage("Solo hay un elemento");

            }

        } else {

            JsfUtil.addErrorMessage("No hay Elementos");
        }

    }

    public void eliminarxPosicion() {

        if (tempDE.getSiguienteDE() != null) {

            listaPerrosDE.eliminarPorPosicion(borrarPosicion);
            JsfUtil.addSuccessMessage("Se Elimino con exito");
            irPriemroDe();
            inicializarModelo();

        } else if (tempDE.getSiguienteDE() == null && tempDE.getAnteriorDE() == null) {

            listaPerrosDE.eliminarPorPosicion(borrarPosicion);
            JsfUtil.addSuccessMessage("Se Elimino con exito");
            perroMostrarDE = null;
            inicializarModelo();
        } else {

            listaPerrosDE.eliminarPorPosicion(borrarPosicion);
            JsfUtil.addSuccessMessage("Se Elimino con exito");
            irPriemroDe();
            inicializarModelo();
        }

    }

    public void mostrar() {

        listaPerrosDE.mostrarLista();
    }

    public void eliminacionDirecta() {

        if (tempDE.getSiguienteDE() != null) {

            listaPerrosDE.eliminar(perroMostrarDE);
            JsfUtil.addSuccessMessage(perroMostrarDE.getNombre() + " :Se Elimino con exito");

            irPriemroDe();
            inicializarModelo();

        } else if (tempDE.getSiguienteDE() == null && tempDE.getAnteriorDE() == null) {

            listaPerrosDE.eliminar(perroMostrarDE);
            JsfUtil.addSuccessMessage(perroMostrarDE.getNombre() + " :Se Elimino con exito");
            perroMostrarDE = null;
            inicializarModelo();
        } else {

            listaPerrosDE.eliminar(perroMostrarDE);
            JsfUtil.addSuccessMessage(perroMostrarDE.getNombre() + " :Se Elimino con exito");
            irPriemroDe();
            inicializarModelo();
        }

    }

    public void intercambiarPosicionesDadas() {

        if (posicionUno == posicionDos) {

            JsfUtil.addErrorMessage("Las dos posiciones son iguales");

        } else {

            listaPerrosDE.intercambiarPosiciones(posicionUno, posicionDos);
            irPriemroDe();
            inicializarModelo();

        }

    }

    public void buscarPerro() {

        perroEncontradoDE = listaPerrosDE.buscarPosicionDE(datoBuscarDE);
        pintarBusqueda();

    }

    public void inicializarModelo() {

//Instanciar el modelo
        model = new DefaultDiagramModel();
        //Definir el modelo la cantidad de enlaces -1 (Infinito)
        model.setMaxConnections(-1);
        //Pregunto si hay datos
        if (listaPerrosDE.getCabezaDe() != null) {

            //Llamar ayudante y ubicar en el primero 
            NodoDE ayudante = listaPerrosDE.getCabezaDe();
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
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));

                model.addElement(perroPintar);

                ayudante = ayudante.getSiguienteDE();
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
                model.connect(createConnection(model.getElements().get(i + 1).getEndPoints().get(2), model.getElements().get(i).getEndPoints().get(3), null));
            }

        }
    }

    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }

    public void pintarIntercambio() {
        model = new DefaultDiagramModel();
        //Definir el modelo la cantidad de enlaces -1 (Infinito)
        model.setMaxConnections(-1);
        //Pregunto si hay datos
        if (listaPerrosDE.getCabezaDe() != null) {

            //Llamar ayudante y ubicar en el primero 
            NodoDE ayudante = listaPerrosDE.getCabezaDe();
            //Recorro mientras el ayudante tenga ddato 
            int posX = 2;
            int posY = 2;
            while (ayudante != null) {

                Element perroPintar = new Element(ayudante.getDato().getNombre(), posX + "em", posY + "em");

                if (ayudante == tempDE || ayudante.getSiguienteDE() == null) {

                    perroPintar.setStyleClass("ui-diagram-success");
                }
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));

                model.addElement(perroPintar);

                ayudante = ayudante.getSiguienteDE();
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
                model.connect(createConnection(model.getElements().get(i + 1).getEndPoints().get(2), model.getElements().get(i).getEndPoints().get(3), null));

            }
        }
    }

    public void pintarBusqueda() {

        model = new DefaultDiagramModel();
        //Definir el modelo la cantidad de enlaces -1 (Infinito)
        model.setMaxConnections(-1);
        //Pregunto si hay datos
        if (listaPerrosDE.getCabezaDe() != null) {

            //Llamar ayudante y ubicar en el primero 
            NodoDE ayudante = listaPerrosDE.getCabezaDe();
            //Recorro mientras el ayudante tenga ddato 
            int posX = 2;
            int posY = 2;
            while (ayudante != null) {

                Element perroPintar = new Element(ayudante.getDato().getNombre(), posX + "em", posY + "em");

                if (ayudante.getDato().getNumero() == perroEncontradoDE.getNumero()) {
                    perroPintar.setStyleClass("ui-diagram-success");
                }
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));

                model.addElement(perroPintar);

                ayudante = ayudante.getSiguienteDE();
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
                model.connect(createConnection(model.getElements().get(i + 1).getEndPoints().get(2), model.getElements().get(i).getEndPoints().get(3), null));

            }
        }
    }

}
