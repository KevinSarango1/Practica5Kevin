/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.DAO;

import Controlador.DAO.Conexion.Conexion;
import Controlador.ed.lista.Exception.PosicionException;
import Controlador.ed.lista.ListaEnlazada;
import Controlador.ed.lista.NodoLista;
import Modelo.Ropa;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import org.jboss.logging.Logger;

/**
 *
 * @author Kevin
 */
public class AdaptadorDAO<T>{

    private Conexion conexion;
    private Class clazz;
    private String url;
    public static Integer Ascendente = 0;
    public static Integer Descendente = 1;

    public AdaptadorDAO(Class clazz) {
        this.conexion = new Conexion();
        this.clazz = clazz;
        this.url = Conexion.URL + clazz.getSimpleName().toLowerCase() + ".json";
    }

    public void guardar(T obj) throws Exception{
        ListaEnlazada<T> lista = listar();
        lista.insertarNodo(obj);
        conexion.getXstream().alias(lista.getClass().getName(), ListaEnlazada.class);
        conexion.getXstream().toXML(lista, new FileWriter(url));
    }

    public void modificar(T obj, Integer pos) throws Exception{
        ListaEnlazada<T> lista = listar();
        try {
            lista.modificar(obj, pos);
            conexion.getXstream().alias(lista.getClass().getName(), ListaEnlazada.class);
            conexion.getXstream().toXML(lista, new FileWriter(url));
        } catch (PosicionException | IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    public ListaEnlazada listar() {
        ListaEnlazada<T> lista = new ListaEnlazada<>();
        try {
            lista = (ListaEnlazada<T>) conexion.getXstream().fromXML(new File(url));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public T obtener(Integer id) {
        T obj = null;
        ListaEnlazada<T> lista = listar();
        // getValueField();
        for (int i = 0; i < lista.size(); i++) {
            try {
                T dato = lista.get(i);
                if (id.intValue() == ((Integer) getValueField(lista.get(i))).intValue()) {
                    obj = dato;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error en metodo " + e);
            }
        }
        return obj;

        //ListaEnlazada<E> lista = listar();
        //return (E) lista;
    }   

    private Object getValueField(T dato) throws Exception {
        Method metodo = null;
        for (Method aux : this.clazz.getDeclaredMethods()) {
            if (aux.getName().toLowerCase().equalsIgnoreCase("getId")) {
                metodo = aux;

            }
        }

        // para herencia
        if (metodo == null) {
            for (Method aux : this.clazz.getSuperclass().getDeclaredMethods()) {
                if (aux.getName().toLowerCase().equalsIgnoreCase("getId")) {
                    metodo = aux;
                }
            }

        }
        return metodo.invoke(dato);
    }

     public Integer generarId() {
        return listar().size() + 1;
    }
    public ListaEnlazada<Ropa> ordenarPrenda(ListaEnlazada<Ropa> lista) {
        try {
            Ropa[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Ropa key = lista.get(i);
                int j = i - 1;
                while (j >= 0 && matriz[j].getPrenda().compareToIgnoreCase(key.getPrenda()) > 0) {
                    matriz[j + 1] = matriz[j];
                    j = j - 1;
                }
                matriz[j + 1] = key;
            }
            lista.toList(matriz);
        } catch (Exception e) {
        }
        return lista;
    }

    public ListaEnlazada<Ropa> ordenarColor(ListaEnlazada<Ropa> lista) {
        try {
            Ropa[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Ropa key = lista.get(i);
                int j = i - 1;
                while (j >= 0 && matriz[j].getColor().compareToIgnoreCase(key.getColor()) > 0) {
                    matriz[j + 1] = matriz[j];
                    j = j - 1;
                }
                matriz[j + 1] = key;
            }
            lista.toList(matriz);
        } catch (Exception e) {
        }
        return lista;
    }

    public ListaEnlazada<Ropa> ordenarEstado(ListaEnlazada<Ropa> lista) {
        try {
            Ropa[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Ropa key = lista.get(i);
                int j = i - 1;
                while (j >= 0 && matriz[j].getEstado().compareToIgnoreCase(key.getEstado()) > 0) {
                    matriz[j + 1] = matriz[j];
                    j = j - 1;
                }
                matriz[j + 1] = key;
            }
            lista.toList(matriz);
        } catch (Exception e) {
        }
        return lista;
    }

    public ListaEnlazada<Ropa> busquedaPrendaBinaria(String clave) throws Exception {
    ListaEnlazada<Ropa> lista = listar();
    lista = ordenarPrenda(lista);
    ListaEnlazada<Ropa> resultado = new ListaEnlazada<>();

    NodoLista<Ropa> actual = lista.getCabecera();
    while (actual != null) {
        Ropa ropa = actual.getInfo();
        if (ropa.getPrenda().toLowerCase().startsWith(clave.toLowerCase())) {
            resultado.insertarNodo(ropa);
        }
        actual = actual.getSig();
    }

    return resultado;
}

public ListaEnlazada<Ropa> busquedaColorBinaria(String clave) throws Exception {
    ListaEnlazada<Ropa> lista = listar();
    lista = ordenarColor(lista);
    ListaEnlazada<Ropa> resultado = new ListaEnlazada<>();

    NodoLista<Ropa> actual = lista.getCabecera();
    while (actual != null) {
        Ropa ropa = actual.getInfo();
        if (ropa.getColor().toLowerCase().startsWith(clave.toLowerCase())) {
            resultado.insertarNodo(ropa);
        }
        actual = actual.getSig();
    }

    return resultado;
}

public ListaEnlazada<Ropa> busquedaEstadoBinaria(String clave) throws Exception {
    ListaEnlazada<Ropa> lista = listar();
    lista = ordenarEstado(lista);
    ListaEnlazada<Ropa> resultado = new ListaEnlazada<>();

    NodoLista<Ropa> actual = lista.getCabecera();
    while (actual != null) {
        Ropa ropa = actual.getInfo();
        if (ropa.getEstado().toLowerCase().startsWith(clave.toLowerCase())) {
            resultado.insertarNodo(ropa);
        }
        actual = actual.getSig();
    }

    return resultado;
}

public ListaEnlazada<Ropa> busquedaPrendaLinealBinaria(String clave) throws Exception {
    ListaEnlazada<Ropa> lista = listar();
    lista = ordenarPrenda(lista);
    ListaEnlazada<Ropa> resultado = new ListaEnlazada<>();

    NodoLista<Ropa> actual = lista.getCabecera();
    while (actual != null) {
        Ropa ropa = actual.getInfo();
        if (ropa.getPrenda().toLowerCase().startsWith(clave.toLowerCase())) {
            resultado.insertarNodo(ropa);
        }
        actual = actual.getSig();
    }

    return resultado;
}

public ListaEnlazada<Ropa> busquedaColorLinealBinaria(String clave) throws Exception {
    ListaEnlazada<Ropa> lista = listar();
    lista = ordenarColor(lista);
    ListaEnlazada<Ropa> resultado = new ListaEnlazada<>();

    NodoLista<Ropa> actual = lista.getCabecera();
    while (actual != null) {
        Ropa ropa = actual.getInfo();
        if (ropa.getColor().toLowerCase().startsWith(clave.toLowerCase())) {
            resultado.insertarNodo(ropa);
        }
        actual = actual.getSig();
    }

    return resultado;
}

public ListaEnlazada<Ropa> busquedaEstadoLinealBinaria(String clave) throws Exception {
    ListaEnlazada<Ropa> lista = listar();
    lista = ordenarEstado(lista);
    ListaEnlazada<Ropa> resultado = new ListaEnlazada<>();

    NodoLista<Ropa> actual = lista.getCabecera();
    while (actual != null) {
        Ropa ropa = actual.getInfo();
        if (ropa.getEstado().toLowerCase().startsWith(clave.toLowerCase())) {
            resultado.insertarNodo(ropa);
        }
        actual = actual.getSig();
    }

    return resultado;
}
}
