/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paseoperros.controlador;

import co.edu.umanizales.listase.modelo.Infante;
import co.edu.umanizales.listase.modelo.ListaDECInfante;
import co.edu.umanizales.listase.modelo.NodoDEInfante;
import co.edu.umanizales.listase.modelo.OportunidadNiño;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
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
@Named(value = "controladorTingoTango")
@SessionScoped
public class ControladorTingoTango implements Serializable {

    private Infante infanteGuardar;
    private List<Infante> listadoInfantes;
    private List<Infante> listadoInfantesEliminados;

    private List<OportunidadNiño> listaOportunidades;
    private OportunidadNiño niñoSeleccionado;

    private ListaDECircularCrontroller tingoTango;

    private byte cantidadInfantes;
    private byte numOportunidades;
    private byte posIgreso;
    private NodoDEInfante ayudanteColor;
    private NodoDEInfante tempDEC;
    private ListaDECInfante listaNiñosDEC;
    private DashboardModel model;
    private String sexo;
    private DefaultDiagramModel modeloGrafica;

    private byte idAleatorio;

    private String textoBotonIniciarDer;
    private String textoBotonIzqui;
    private boolean estadoJuego;
    private boolean estadoJuegoIzqu;
    private String colorBotonDer;
    private String colorBotonIzqu;

    private OportunidadNiño infanteReingresar;
    private int seleccionGeneroIngresar = 0;
    private int seleccionUbicacion = 0;
    private int posicion;
    private byte numOporIngreso;
    private byte oporGenero;

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
        DashboardColumn column3 = new DefaultDashboardColumn();

        column1.addWidget("acciones");
        column2.addWidget("lista");

        column1.addWidget("Grafica");

        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);

        listaNiñosDEC = new ListaDECInfante();

//        Infante lulu = new Infante("Lulu", (byte) 1, "Femenino");
//        OportunidadNiño niño1 = new OportunidadNiño(lulu, (byte) 1);
//        listaNiñosDEC.adicionarNiñoAlinicioDeCircular(niño1);
//
//        Infante pastor = new Infante("Pastor", (byte) 2, "Masculino");
//        OportunidadNiño niño2 = new OportunidadNiño(pastor, (byte) 1);
//        listaNiñosDEC.adicionarNiñoAlinicioDeCircular(niño2);
//
//        Infante tomy = new Infante("Tomy", (byte) 4, "Masculino");
//        OportunidadNiño niño4 = new OportunidadNiño(tomy, (byte) 1);
//        listaNiñosDEC.adicionarNiñoAlinicioDeCircular(niño4);
//
//        Infante kira = new Infante("Kira", (byte) 5, "Masculino");
//        OportunidadNiño niño5 = new OportunidadNiño(kira, (byte) 1);
//        listaNiñosDEC.adicionarNiñoAlinicioDeCircular(niño5);
//        infanteGuardar = listaNiñosDEC.getCabezaInfante().getDato();
//        ayudanteColor = listaNiñosDEC.getCabezaInfante();
        listadoInfantes = new ArrayList<>();
        listadoInfantesEliminados = new ArrayList<>();
        listaOportunidades = new ArrayList<>();
        idAleatorio = 1;

        textoBotonIniciarDer = "INICIAR DERECHA";
        textoBotonIzqui = "INICIAR IZQUIERDA";
        estadoJuego = false;
        estadoJuegoIzqu = false;
        colorBotonDer = "#088A29";
        colorBotonIzqu = "#088A29";

//        modeloDerecha();
//        modeloIzuierda();
    }

    //GET Y SET
    public byte getOporGenero() {
        return oporGenero;
    }

    public void setOporGenero(byte oporGenero) {
        this.oporGenero = oporGenero;
    }

    public byte getNumOporIngreso() {
        return numOporIngreso;
    }

    public void setNumOporIngreso(byte numOporIngreso) {
        this.numOporIngreso = numOporIngreso;
    }

    public int getSeleccionGeneroIngresar() {
        return seleccionGeneroIngresar;
    }

    public void setSeleccionGeneroIngresar(int seleccionGeneroIngresar) {
        this.seleccionGeneroIngresar = seleccionGeneroIngresar;
    }

    public byte getIdAleatorio() {
        return idAleatorio;
    }

    public void setIdAleatorio(byte idAleatorio) {
        this.idAleatorio = idAleatorio;
    }

    public OportunidadNiño getInfanteReingresar() {
        return infanteReingresar;
    }

    public void setInfanteReingresar(OportunidadNiño infanteReingresar) {
        this.infanteReingresar = infanteReingresar;
    }

    public List<Infante> getListadoInfantesEliminados() {
        return listadoInfantesEliminados;
    }

    public void setListadoInfantesEliminados(List<Infante> listadoInfantesEliminados) {
        this.listadoInfantesEliminados = listadoInfantesEliminados;
    }

    public boolean isEstadoJuegoIzqu() {
        return estadoJuegoIzqu;
    }

    public void setEstadoJuegoIzqu(boolean estadoJuegoIzqu) {
        this.estadoJuegoIzqu = estadoJuegoIzqu;
    }

    public NodoDEInfante getAyudanteColor() {
        return ayudanteColor;
    }

    public void setAyudanteColor(NodoDEInfante ayudanteColor) {
        this.ayudanteColor = ayudanteColor;
    }

    public String getColorBotonIzqu() {
        return colorBotonIzqu;
    }

    public void setColorBotonIzqu(String colorBotonIzqu) {
        this.colorBotonIzqu = colorBotonIzqu;
    }

    public String getTextoBotonIzqui() {
        return textoBotonIzqui;
    }

    public void setTextoBotonIzqui(String textoBotonIzqui) {
        this.textoBotonIzqui = textoBotonIzqui;
    }

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

    public NodoDEInfante getTempDEC() {
        return tempDEC;
    }

    public void setTempDEC(NodoDEInfante tempDEC) {
        this.tempDEC = tempDEC;
    }

    public DefaultDiagramModel getModeloGrafica() {
        return modeloGrafica;
    }

    public void setModeloGrafica(DefaultDiagramModel modeloGrafica) {
        this.modeloGrafica = modeloGrafica;
    }

    public String getTextoBotonIniciarDer() {
        return textoBotonIniciarDer;
    }

    public void setTextoBotonIniciarDer(String textoBotonIniciarDer) {
        this.textoBotonIniciarDer = textoBotonIniciarDer;
    }

    public String getColorBotonDer() {
        return colorBotonDer;
    }

    public void setColorBotonDer(String colorBotonDer) {
        this.colorBotonDer = colorBotonDer;
    }

    public List<OportunidadNiño> getOportunidades() {
        return listaOportunidades;
    }

    public void setOportunidades(List<OportunidadNiño> oportunidades) {
        this.listaOportunidades = oportunidades;
    }

    public OportunidadNiño getNiñoSeleccionado() {
        return niñoSeleccionado;
    }

    public void setNiñoSeleccionado(OportunidadNiño niñoSeleccionado) {
        this.niñoSeleccionado = niñoSeleccionado;
    }

    public List<OportunidadNiño> getListaOportunidades() {
        return listaOportunidades;
    }

    public void setListaOportunidades(List<OportunidadNiño> listaOportunidades) {
        this.listaOportunidades = listaOportunidades;
    }

    public int getSeleccionUbicacion() {
        return seleccionUbicacion;
    }

    public void setSeleccionUbicacion(int seleccionUbicacion) {
        this.seleccionUbicacion = seleccionUbicacion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    //GET Y SET
    public void seleccionarInfante(OportunidadNiño infa) {

        infanteReingresar = infa;

    }

    public void capturarInfanteEditar(Infante infante) {

        listadoInfantes.remove(infante);
        infanteGuardar = infante;

    }

    public void actualizarInfante() {

        listadoInfantes.add(infanteGuardar);
        infanteGuardar = new Infante();

    }

    public void eliminarInfante(Infante infante) {

        listadoInfantes.remove(infante);

    }

    public void reingresarPorGenero() {

        switch (seleccionGeneroIngresar) {

            case 1:

                reingresarNiñas(niñoSeleccionado);

                break;

            case 2:

                reingresarNiños(niñoSeleccionado);

                break;

        }

    }

    public void controlarJuegoDerecha() {

        if (estadoJuegoIzqu == false) {

            textoBotonIniciarDer = "INICIAR DERECHA";
            colorBotonDer = "#088A29";
            estadoJuego = !estadoJuego;

            if (estadoJuego == false) {

                if (ayudanteColor.getDatoDos().getOportunidad() >= 1) {

                    ayudanteColor.getDatoDos().setOportunidad((byte) (ayudanteColor.getDatoDos().getOportunidad() - 1));
                    JsfUtil.addSuccessMessage(ayudanteColor.getDatoDos().getInfante().getNombre().toUpperCase() + " Pierde una oportunidad "
                            + " le quedan " + ayudanteColor.getDatoDos().getOportunidad() + " oportunidades");

                } else {

                    listaOportunidades.add(ayudanteColor.getDatoDos());

//                    listadoInfantes.add(ayudanteColor.getDato());
                    JsfUtil.addSuccessMessage("Se elimino " + ayudanteColor.getDatoDos().getInfante().getNombre());
                    NodoDEInfante tempral = ayudanteColor.getSiguienteDE();
                    listaNiñosDEC.eliminarNodoDirecto(ayudanteColor.getDatoDos());
                    ayudanteColor = tempral;

                    modeloDerecha();

                    if (listaNiñosDEC.contarNodosDEC() == 1) {

                        PrimeFaces current = PrimeFaces.current();
                        current.executeScript("PF('myDialogVar').show();");
                        reiniciar();

                        JsfUtil.addSuccessMessage("Niño Ganador " + ayudanteColor.getDatoDos().getInfante().getNombre());

                    }

                }

            }

            if (estadoJuego) {

                textoBotonIniciarDer = "PARAR DERECHA";
                colorBotonDer = "#B40404";

            }
        } else {

            JsfUtil.addErrorMessage("EL JUEGO ESTA CORRIENDO HACIA LA IZQUIERDA ");
        }

    }

    public void controlarJuegoIzqui() {

        if (estadoJuego == false) {

            textoBotonIzqui = "INICIAR IZQUIERDA";
            colorBotonIzqu = "#088A29";
            estadoJuegoIzqu = !estadoJuegoIzqu;

            if (estadoJuegoIzqu == false) {

                if (ayudanteColor.getDatoDos().getOportunidad() >= 1) {

                    ayudanteColor.getDatoDos().setOportunidad((byte) (ayudanteColor.getDatoDos().getOportunidad() - 1));
                    JsfUtil.addSuccessMessage(ayudanteColor.getDatoDos().getInfante().getNombre().toUpperCase() + " Pierde una oportunidad "
                            + " le quedan " + ayudanteColor.getDatoDos().getOportunidad() + " oportunidades");

                } else {

                    listaOportunidades.add(ayudanteColor.getDatoDos());

//                    listadoInfantes.add(ayudanteColor.getDato());
                    JsfUtil.addSuccessMessage("Se elimino " + ayudanteColor.getDatoDos().getInfante().getNombre());
                    NodoDEInfante tempral = ayudanteColor.getAnteriorDE();
                    listaNiñosDEC.eliminarNodoDirecto(ayudanteColor.getDatoDos());
                    ayudanteColor = tempral;

                    modeloIzuierda();

                    if (listaNiñosDEC.contarNodosDEC() == 1) {

                        PrimeFaces current = PrimeFaces.current();
                        current.executeScript("PF('myDialogVar').show();");
                        reiniciar();

                        JsfUtil.addSuccessMessage("Niño Ganador " + ayudanteColor.getDatoDos().getInfante().getNombre());

                    }

                }

            }

            if (estadoJuegoIzqu) {

                textoBotonIzqui = "PARAR IZQUIERDA";
                colorBotonIzqu = "#B40404";

            }

        } else {

            JsfUtil.addErrorMessage("EL JUEGO ESTA CORRIENDO HACIA LA DERECHA ");
        }

    }

    public void pasarSiguienteDerColor() {

        if (ayudanteColor.getSiguienteDE() != null) {

            ayudanteColor = ayudanteColor.getSiguienteDE();

        } else {

            /// empieza de nuevo
        }

        modeloDerecha();

    }

    public void pasarAnteriorDerColor() {

        if (ayudanteColor.getAnteriorDE() != null) {

            ayudanteColor = ayudanteColor.getAnteriorDE();

        } else {

            /// empieza de nuevo
        }

        modeloIzuierda();

    }

    public String irCrearNiño() {

        infanteGuardar = new Infante();
        return "crearniño";
    }

    public String irHome() {

        infanteGuardar = new Infante();
        return "tingotango";
    }

    public void retirarNiñas() {

        int contador = listaNiñosDEC.contarNiños("niña");

        if (contador != listaNiñosDEC.contarNodosDEC()) {

            guardarNiñas();
            listaNiñosDEC.eliminarNiños("niña");
            modeloDerecha();

        } else {

            JsfUtil.addErrorMessage("Solo hay niñas , el juego no puede quedar vacio");

        }

    }

    public void guardarNiñas() {

        int cont = 0;

        for (OportunidadNiño eliminarNiño : listaNiñosDEC.eliminarNiños("niña")) {

            if (eliminarNiño.getInfante() instanceof Infante) {

                listaOportunidades.add(eliminarNiño);
                cont++;

            }

        }

        if (cont == 0) {

            JsfUtil.addErrorMessage("No hay Niñas para retirar del juego");

        }

    }

    public void guardarNiños() {

        int cont = 0;

        for (OportunidadNiño eliminarNiño : listaNiñosDEC.eliminarNiños("niño")) {

            if (eliminarNiño.getInfante() instanceof Infante) {

                listaOportunidades.add(eliminarNiño);
                cont++;

            }

        }

        if (cont == 0) {

            JsfUtil.addErrorMessage("No hay Niños para retirar del juego");

        }

    }

    public void retirarNiños() {

        int contador = listaNiñosDEC.contarNiños("niño");

        if (contador != listaNiñosDEC.contarNodosDEC()) {

            guardarNiños();
            listaNiñosDEC.eliminarNiños("niño");
            modeloDerecha();

        } else {

            JsfUtil.addErrorMessage("Solo hay niños , el juego no puede quedar vacio");

        }

    }

    public void reingresarIndividual() {

        listaNiñosDEC.adicionarNiñoAlinicioDeCircular(infanteReingresar);
        listaOportunidades.remove(infanteReingresar);
        modeloDerecha();
        infanteReingresar = new OportunidadNiño();

    }

    public void reingresarInfante(OportunidadNiño infanteEliminado) {

        listaNiñosDEC.adicionarNiñoAlinicioDeCircular(infanteEliminado);
        modeloDerecha();
        listaOportunidades.remove(infanteEliminado);

    }

    public void reingresarNiñas(OportunidadNiño infanteEliminado) {

        int cont = 0;
        List<OportunidadNiño> listaTemporal = new ArrayList<>();

        for (OportunidadNiño Eliminado : listaOportunidades) {

            if (Eliminado.getInfante().getGenero().equals("Femenino")) {

                Eliminado.setOportunidad(oporGenero);

                listaNiñosDEC.adicionarNiñoAlinicioDeCircular(Eliminado);
                listaTemporal.add(Eliminado);
                cont++;

            }

        }

        listaOportunidades.removeAll(listaTemporal);
        modeloDerecha();

        if (cont == 0) {

            JsfUtil.addErrorMessage("No hay Niñas para Reingresar");

        }

    }

    public void reingresarEnPosDada() {

        infanteReingresar.setOportunidad(numOporIngreso);

        listaNiñosDEC.adicionarNodoPorPosicion(infanteReingresar, posicion);
        modeloIzuierda();
        listaOportunidades.remove(infanteReingresar);
        infanteReingresar = new OportunidadNiño();

        JsfUtil.addSuccessMessage("Se ha reingreso el niño a la lista");

        listaNiñosDEC.mostrarListaDEC();

    }

    public void reingresarNiño() {

        infanteReingresar.setOportunidad(numOporIngreso);

        switch (seleccionUbicacion) {

            case 1:
                listaNiñosDEC.adicionarNiñoAlinicioDeCircular(infanteReingresar);
                seleccionUbicacion = 0;

                break;
            case 2:
                listaNiñosDEC.adicionarNodoAlfinal(infanteReingresar);
                seleccionUbicacion = 0;
                ;
                break;

            default:
                listaNiñosDEC.adicionarNodoAlfinal(infanteReingresar);
                seleccionUbicacion = 0;

        }

        listaOportunidades.remove(infanteReingresar);
        modeloDerecha();
        infanteReingresar = new OportunidadNiño();

        JsfUtil.addSuccessMessage("Se ha reingreso el niño a la lista");

    }

    public void reingresarNiños(OportunidadNiño infanteEliminado) {

        int cont = 0;
        List<OportunidadNiño> listaTemporal = new ArrayList<>();

        for (OportunidadNiño Eliminado : listaOportunidades) {

            if (Eliminado.getInfante().getGenero().equals("Masculino")) {

                Eliminado.setOportunidad(oporGenero);

                listaNiñosDEC.adicionarNiñoAlinicioDeCircular(Eliminado);
                listaTemporal.add(Eliminado);
                cont++;

            }

        }

        listaOportunidades.removeAll(listaTemporal);
        modeloDerecha();

        if (cont == 0) {

            JsfUtil.addErrorMessage("No hay Niños para Reingresar");

        }
    }

    public int contarInfantes() {

        int tamanio = listadoInfantes.size();

        return tamanio;

    }

    public int contarInfantesEliminados() {

        int tamanio = listaOportunidades.size();

        return tamanio;

    }

    public void reiniciar() {

        listaNiñosDEC.setCabezaInfante(null);
        listaOportunidades.clear();
        modeloDerecha();
    }

    public void eliminarDirecta() {

        listaOportunidades.add(ayudanteColor.getDatoDos());
        listaNiñosDEC.eliminarNodoDirecto(ayudanteColor.getDatoDos());
        ayudanteColor = listaNiñosDEC.getCabezaInfante();
        modeloDerecha();

    }

    public void eliminarGenero(OportunidadNiño niño) {

        listaOportunidades.add(niño);
        listaNiñosDEC.eliminarNodoDirecto(niño);
        ayudanteColor = listaNiñosDEC.getCabezaInfante();
        modeloDerecha();

    }

    public void contarListaInfantes() {

        listaNiñosDEC.contarNodosDEC();

    }

    public void adicionarArray() {

        infanteGuardar.setIdentificador(idAleatorio++);

        listadoInfantes.add(infanteGuardar);
        infanteGuardar = new Infante();

    }

    public void configurarJuego() {

        List<Infante> listaTemporal = new ArrayList<>();

        if (cantidadInfantes > contarInfantes()) {

            JsfUtil.addErrorMessage("No tiene los suficientes niños para jugar ");

        } else {

            int cont = 0;
            int maximo = listadoInfantes.size();

            for (int i = 0; i < maximo; i++) {

                Random r = new Random();
                int inicio = 0;
                int fin = listadoInfantes.size();
                int result = r.nextInt(fin - inicio) + inicio;

                if (cantidadInfantes == cont) {
                    break;
                }

                System.out.println("SI ENTRA AGREGAR");

                niñoSeleccionado = new OportunidadNiño(listadoInfantes.get(result), (byte) numOportunidades);

                listaNiñosDEC.adicionarNiñoAlinicioDeCircular(niñoSeleccionado);
                listaTemporal.add(niñoSeleccionado.getInfante());
                listadoInfantes.remove(niñoSeleccionado.getInfante());
                cont++;

            }

            listadoInfantes.addAll(listaTemporal);

        }

        listaNiñosDEC.mostrarListaDEC();
        ayudanteColor = listaNiñosDEC.getCabezaInfante();
        modeloDerecha();

    }

    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }

    public void modeloDerecha() {

//Instanciar el modelo
        modeloGrafica = new DefaultDiagramModel();
        //Definir el modelo la cantidad de enlaces -1 (Infinito)
        modeloGrafica.setMaxConnections(-1);
        //Pregunto si hay datos

        //YA SE PINTARON TODOS LOS ELEMENTOS 
        //PROPIEDAD PARA EL CONECTOR
        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#092CB0',lineWidth:3}");
        modeloGrafica.setDefaultConnector(connector);

        int jugadorPorCuadrante = 0;
        int numeroCuadrante = 1;
        int residuoJugagores = listaNiñosDEC.contarNodosDEC() % 4;
        int cuadrantey = 0;
        String posFlecha = "";
        int cantJugadores = 0;

        if (listaNiñosDEC.getCabezaInfante() != null) {

            //Llamar ayudante y ubicar en el primero 
            NodoDEInfante ayudanteDos = listaNiñosDEC.getCabezaInfante();
            //Recorro mientras el ayudante tenga ddato 
            int posX = 13;
            int posY = 2;
            int contFila = 0;
            jugadorPorCuadrante = listaNiñosDEC.contarNodosDEC() / 4;

            if (residuoJugagores == 3) {
                jugadorPorCuadrante++;
                cuadrantey = 2;

            }
            do {

                if (contFila == jugadorPorCuadrante) {

                    numeroCuadrante++;
                    contFila = 0;
                    if (numeroCuadrante == 2 && contFila == 0) {

                        posX = posX + 8;
                        if (residuoJugagores == 2 || residuoJugagores == 1) {
                            jugadorPorCuadrante++;
                            cuadrantey = 3;

                        }

                    } else if (numeroCuadrante == 3 && contFila == 0) {
                        posY = posY + 4;

                    } else if (numeroCuadrante == 4 && contFila == 0) {

                        posX = posY - 8;
                    }

                }

                switch (numeroCuadrante) {

                    case 1:
                        posX = posX + 8;
                        posY = posY;
                        posFlecha = "LEFT";
                        break;
                    case 2:
                        posX = posX;
                        posY = posY + 5;
                        break;
                    case 3:
                        posX = posX - 8;
                        posY = posY;
                        break;
                    case 4:
                        posX = posX;
                        posY = posY - 5 - cuadrantey;
                        break;

                }

                Element niñoPintar = new Element(ayudanteDos.getDatoDos().getInfante().getNombre(), posX + "em", posY + "em");

                niñoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
                niñoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
                modeloGrafica.addElement(niñoPintar);

                if (ayudanteDos.getDatoDos().getInfante().getIdentificador()
                        == ayudanteColor.getDatoDos().getInfante().getIdentificador()) {

                    niñoPintar.setStyleClass("ui-diagram-success");

                }

                ayudanteDos = ayudanteDos.getSiguienteDE();
                contFila++;
                cantJugadores++;

            } while (ayudanteDos != listaNiñosDEC.getCabezaInfante());

            for (int i = 0; i < modeloGrafica.getElements().size() - 1; i++) {

                modeloGrafica.connect(createConnection(modeloGrafica.getElements().get(i).getEndPoints().get(0), modeloGrafica.getElements().get(i + 1).getEndPoints().get(1), null));
                modeloGrafica.connect(createConnection(modeloGrafica.getElements().get(modeloGrafica.getElements().size() - 1).getEndPoints().get(0), modeloGrafica.getElements().get(0).getEndPoints().get(1), null));
            }

        } else {

        }
    }

    public void modeloIzuierda() {

        //Instanciar el modelo
        modeloGrafica = new DefaultDiagramModel();
        //Definir el modelo la cantidad de enlaces -1 (Infinito)
        modeloGrafica.setMaxConnections(-1);
        //Pregunto si hay datos

        //YA SE PINTARON TODOS LOS ELEMENTOS 
        //PROPIEDAD PARA EL CONECTOR
        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#092CB0',lineWidth:3}");
        modeloGrafica.setDefaultConnector(connector);

        int jugadorPorCuadrante = 0;
        int numeroCuadrante = 1;
        int residuoJugagores = listaNiñosDEC.contarNodosDEC() % 4;
        int cuadrantey = 0;
        String posFlecha = "";
        int cantJugadores = 0;

        if (listaNiñosDEC.getCabezaInfante() != null) {

            //Llamar ayudante y ubicar en el primero 
            NodoDEInfante ayudanteDos = listaNiñosDEC.getCabezaInfante();
            //Recorro mientras el ayudante tenga ddato 
            int posX = 13;
            int posY = 2;
            int contFila = 0;
            jugadorPorCuadrante = listaNiñosDEC.contarNodosDEC() / 4;

            if (residuoJugagores == 3) {
                jugadorPorCuadrante++;
                cuadrantey = 2;

            }
            do {

                if (contFila == jugadorPorCuadrante) {

                    numeroCuadrante++;
                    contFila = 0;
                    if (numeroCuadrante == 2 && contFila == 0) {

                        posX = posX + 8;
                        if (residuoJugagores == 2 || residuoJugagores == 1) {

                            jugadorPorCuadrante++;
                            cuadrantey = 3;

                        }

                    } else if (numeroCuadrante == 3 && contFila == 0) {
                        posY = posY + 4;

                    } else if (numeroCuadrante == 4 && contFila == 0) {

                        posX = posY - 8;
                    }

                }

                switch (numeroCuadrante) {

                    case 1:
                        posX = posX + 8;
                        posY = posY;
                        posFlecha = "LEFT";
                        break;
                    case 2:
                        posX = posX;
                        posY = posY + 5;
                        break;
                    case 3:
                        posX = posX - 8;
                        posY = posY;
                        break;
                    case 4:
                        posX = posX;
                        posY = posY - 5 - cuadrantey;
                        break;

                }

                Element niñoPintar = new Element(ayudanteDos.getDatoDos().getInfante().getNombre(), posX + "em", posY + "em");

                niñoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
                niñoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
                modeloGrafica.addElement(niñoPintar);

                if (ayudanteDos.getDatoDos().getInfante().getIdentificador()
                        == ayudanteColor.getDatoDos().getInfante().getIdentificador()) {

                    niñoPintar.setStyleClass("ui-diagram-success");

                }

                ayudanteDos = ayudanteDos.getSiguienteDE();
                contFila++;
                cantJugadores++;

            } while (ayudanteDos != listaNiñosDEC.getCabezaInfante());

            for (int i = 0; i < modeloGrafica.getElements().size() - 1; i++) {

                modeloGrafica.connect(createConnection(modeloGrafica.getElements().get(i).getEndPoints().get(0), modeloGrafica.getElements().get(i + 1).getEndPoints().get(1), null));
                modeloGrafica.connect(createConnection(modeloGrafica.getElements().get(modeloGrafica.getElements().size() - 1).getEndPoints().get(0), modeloGrafica.getElements().get(0).getEndPoints().get(1), null));
            }

        } else {

        }

    }

}
