/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.ModeloTabla;

import Controlador.DAO.MarcaDao;
import Controlador.ed.lista.ListaEnlazada;
import Modelo.Marca;
import Modelo.Ropa;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kevin
 */
public class ModeloTablaRopa extends AbstractTableModel {

    private ListaEnlazada<Ropa> lista = new ListaEnlazada<>();

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Ropa p = null;
        Marca m = null;
        try {
            p = lista.get(i);
            m = new MarcaDao().obtener(p.getMarca_Id());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        switch (i1) {
            case 0: return (p != null) ? p.getPrenda() : "No definido";
            case 1: return (p != null) ? p.getColor() : "No definido";
            case 2: return (p != null) ? p.getEstado() : "No definido";
            case 3: return (m != null) ? m.getNombre() : "No definido";
            case 4: return (m != null) ? m.getCalidad() : "No definido";    
            default: return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Prenda";
            case 1: return "Color";
            case 2: return "Estado";
            case 3: return "Marca";
            case 4: return "Calidad";
            default: return null;
        }
    }

    /**
     * @return the lista
     */
    public ListaEnlazada<Ropa> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Ropa> lista) {
        this.lista = lista;
    }
}
