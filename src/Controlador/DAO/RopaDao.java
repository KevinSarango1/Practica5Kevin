/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.DAO;
import Modelo.Ropa;

/**
 *
 * @author Kevin
 */
public class RopaDao extends AdaptadorDAO<Ropa> {

    private Ropa ropa;

    public RopaDao() {
        super(Ropa.class);
    }

    public Ropa getRopa() {
        if (this.ropa == null) {
            this.ropa = new Ropa();
        }
        return ropa;
    }

    public void setRopa(Ropa ropa) {
        this.ropa = ropa;
    }

    public void guardar() throws Exception {
        ropa.setId(generateID());
        this.guardar(ropa);
    }

    public void modificar(Integer pos) throws Exception {
        this.modificar(ropa, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }
}