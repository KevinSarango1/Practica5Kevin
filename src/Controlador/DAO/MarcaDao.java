/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.DAO;
import Modelo.Marca;

/**
 *
 * @author Kevin
 */
public class MarcaDao extends AdaptadorDAO<Marca> {

    private Marca marca;

    public MarcaDao() {
        super(Marca.class);
    }

    public Marca getMarca() {
        if (this.marca == null) {
            this.marca = new Marca();
        }
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void guardar() throws Exception {
        marca.setId(generateID());
        this.guardar(marca);
    }

    public void modificar(Integer pos) throws Exception {
        this.modificar(marca, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

}

