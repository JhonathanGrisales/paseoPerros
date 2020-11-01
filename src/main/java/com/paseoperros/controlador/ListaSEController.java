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
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import static javax.swing.text.html.HTML.Attribute.N;
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

    private int posicionUno;

    private int posicionDos;

    private int posicionAgregar;

    private int totalPerros = 0;

    private Perro perroEncontrado;

    private DefaultDiagramModel model;

    private int seleccionUbicacion = 0;

    private byte aleatorio = (byte) ((int) (Math.random() * (100 - 2) + 2));

    /**
     * Creates a new instance of ListaSEController
     */
    public ListaSEController() {
    }

    @PostConstruct
    private void iniciar() {
        listaPerros = new ListaSE();
        //// Conectaría a un archivo plano o a una base de datos para llenar la 
//        lista de perros
//        listaPerros.adicionarNodo(new Perro("Pastor", (byte) ((int) (Math.random() * (100 - 2) + 2)), (byte) 3, "M"));
//        listaPerros.adicionarNodo(new Perro("Lulú", (byte) ((int) (Math.random() * (100 - 2) + 2)), (byte) 4, "F"));
//        listaPerros.adicionarNodo(new Perro("Firulais", (byte) ((int) (Math.random() * (100 - 2) + 2)), (byte) 6, "M"));
//        listaPerros.adicionarNodo(new Perro("Luki", ((byte) ((int) (Math.random() * (100 - 2) + 2))), (byte) 9, "M"));
//

//        perroMostrar = listaPerros.getCabeza().getDato();
//        temp = listaPerros.getCabeza();
        totalPerros = listaPerros.contarNodos();
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

    public void setDatoBuscar(int datoBuscar) {
        this.datoBuscar = datoBuscar;
    }

    public Perro getPerroEncontrado() {
        return perroEncontrado;
    }

    public void setPerroEncontrado(Perro perroEncontrado) {
        this.perroEncontrado = perroEncontrado;
    }

    public int getSeleccionUbicacion() {
        return seleccionUbicacion;
    }

    public void setSeleccionUbicacion(int seleccionUbicacion) {
        this.seleccionUbicacion = seleccionUbicacion;
    }

    public int getPosicionAgregar() {
        return posicionAgregar;
    }

    public void setPosicionAgregar(int posicionAgregar) {
        this.posicionAgregar = posicionAgregar;
    }

    public int getTotalPerros() {
        return totalPerros;
    }

    public void setTotalPerros(int totalPerros) {
        this.totalPerros = totalPerros;
    }

    public byte getAleatorio() {
        return aleatorio;
    }

    public void setAleatorio(byte aleatorio) {
        this.aleatorio = aleatorio;
    }

    public void irSiguiente() {

        if (listaPerros.getCabeza() != null) {

            temp = temp.getSiguiente();
            perroMostrar = temp.getDato();
        } else {

            JsfUtil.addErrorMessage("Si datos");
        }

    }

    public void irPrimero() {

        if (listaPerros.getCabeza() != null) {
            temp = listaPerros.getCabeza();
            perroMostrar = temp.getDato();

        } else {

            JsfUtil.addErrorMessage("No existen datos en la lista");
        }

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
        inicializarModelo();
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

        if (listaPerros.getCabeza() != null) {

            int cont = 0;
            temp = listaPerros.getCabeza();

            while (temp != null && cont <= 1) {

                if (temp.getDato().getSexo().equals("M")) {
                    cont = 1;
                }
                temp = temp.getSiguiente();
            }

            temp = listaPerros.getCabeza();

            if (cont == 0) {

                JsfUtil.addErrorMessage("No hay Machos");

            } else if (cont >= 1 && temp.getSiguiente() != null) {

                listaPerros.ordenarMasculino();
                irPrimero();
                inicializarModelo();

            } else if (cont == 1 && temp.getSiguiente() == null) {

                JsfUtil.addErrorMessage("Solo hay un elemento");

            }

        } else {

            JsfUtil.addErrorMessage("No hay Elementos");
        }

    }

    public void ordenarHembra() {

        if (listaPerros.getCabeza() != null) {

            int cont = 0;
            temp = listaPerros.getCabeza();

            while (temp != null && cont <= 1) {

                if (temp.getDato().getSexo().equals("F")) {
                    cont = 1;

                }

                temp = temp.getSiguiente();
            }

            temp = listaPerros.getCabeza();

            if (cont == 0) {

                JsfUtil.addErrorMessage("No hay Hembras");

            } else if (cont >= 1 && temp.getSiguiente() != null) {

                listaPerros.ordenarFemenino();
                irPrimero();
                inicializarModelo();

            } else if (cont == 1 && temp.getSiguiente() == null) {

                JsfUtil.addErrorMessage("Solo hay un elemento");

            }

        } else {

            JsfUtil.addErrorMessage("No hay Elementos");
        }

    }

    public void mostrarMensaje() {

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Elemento no encontrado"));
    }

    public void eliminarxPosicion() {

        if (temp.getSiguiente() != null) {

            listaPerros.eliminarPorPosicion(borrar);
            JsfUtil.addSuccessMessage("Se Elimino con exito");
            irPrimero();
            inicializarModelo();

        } else if (temp.getSiguiente() == null && listaPerros.contarNodos() == 1) {

            listaPerros.eliminarPorPosicion(borrar);
            JsfUtil.addSuccessMessage("Se Elimino con exito");
            perroMostrar = null;
            inicializarModelo();
        } else {

            listaPerros.eliminarPorPosicion(borrar);
            JsfUtil.addSuccessMessage("Se Elimino con exito");
            irPrimero();
            inicializarModelo();
        }

    }

    public void intercambiarPosicionesDadasSE() {

        if (posicionUno == posicionDos) {

            JsfUtil.addErrorMessage("Las dos posiciones son iguales");

        } else {

            listaPerros.intercambiarPosicionesSE(posicionUno, posicionDos);
            irPrimero();
            inicializarModelo();
        }

    }

    public void inicializarModelo() {

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
        posicionAgregar = 1;
        seleccionUbicacion = 0;
        return "crear";
    }

    public byte crearAleatorio() {

        aleatorio = ((byte) ((int) (Math.random() * (100 - 2) + 2)));
        return aleatorio;

    }

    public void guardarPerro() {

        perroEncontrado.setNumero(aleatorio);

        switch (seleccionUbicacion) {

            case 1:

                listaPerros.adicionarNodoAlInicio(perroEncontrado);
                posicionAgregar = 1;
                seleccionUbicacion = 0;
                aleatorio = crearAleatorio();

                break;
            case 2:
                listaPerros.adicionarNodo(perroEncontrado);
                posicionAgregar = 1;
                seleccionUbicacion = 0;
                aleatorio = crearAleatorio();
                break;
            case 3:
                listaPerros.agregarEnPosicionSE(perroEncontrado, posicionAgregar);
                posicionAgregar = 1;
                seleccionUbicacion = 0;
                aleatorio = crearAleatorio();
                break;
            default:
                listaPerros.adicionarNodo(perroEncontrado);
                posicionAgregar = 1;
                seleccionUbicacion = 0;
                aleatorio = crearAleatorio();

        }

        totalPerros++;
        perroEncontrado = new Perro();
        JsfUtil.addSuccessMessage("Se ha adicionado el perro a la lista");
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
