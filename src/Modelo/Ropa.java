/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Kevin
 */
public class Ropa {

    private Integer id;
    private String prenda;
    private String color;
    private String estado;
    private Integer marca_Id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenda() {
        return prenda;
    }

    public void setPrenda(String prenda) {
        this.prenda = prenda;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getMarca_Id() {
        return marca_Id;
    }

    public void setMarca_Id(Integer marca_Id) {
        this.marca_Id = marca_Id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ropa{" + "prenda=" + prenda + ", color=" + color + ", estado=" + estado + '}';
    }
    
}
