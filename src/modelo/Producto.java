package modelo;

import java.io.Serializable;

public class Producto implements Serializable{
	
	private int idProducto;
	private String nombreProducto;
	private String descripcionProducto;
	private double precioProducto;
	private int cantidadDisponibleProducto;
	
	public Producto() {

	}
	public Producto(int idProducto, String nombreProducto, String descripcionProducto, double precioProducto,
			int cantidadDisponibleProducto) {
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.descripcionProducto = descripcionProducto;
		this.precioProducto = precioProducto;
		this.cantidadDisponibleProducto = cantidadDisponibleProducto;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public double getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}
	public int getCantidadDisponibleProducto() {
		return cantidadDisponibleProducto;
	}
	public void setCantidadDisponibleProducto(int cantidadDisponibleProducto) {
		this.cantidadDisponibleProducto = cantidadDisponibleProducto;
	}
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", descripcionProducto="
				+ descripcionProducto + ", precioProducto=" + precioProducto + ", cantidadDisponibleProducto="
				+ cantidadDisponibleProducto + "]";
	}
}
