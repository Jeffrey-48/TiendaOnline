package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Compra implements Serializable{
	
	private int idCompra;
	private Cliente clienteCompra;
	private List<Producto> productosComprados;
	private Date fechaCompra;
	
	public Compra() {

	}
	public Compra(int idCompra, Cliente clienteCompra, List<Producto> productosComprados, Date fechaCompra) {
		this.idCompra = idCompra;
		this.clienteCompra = clienteCompra;
		this.productosComprados = productosComprados;
		this.fechaCompra = fechaCompra;
	}
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public Cliente getClienteCompra() {
		return clienteCompra;
	}
	public void setClienteCompra(Cliente clienteCompra) {
		this.clienteCompra = clienteCompra;
	}
	public List<Producto> getProductosComprados() {
		return productosComprados;
	}
	public void setProductosComprados(List<Producto> productosComprados) {
		this.productosComprados = productosComprados;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", clienteCompra=" + clienteCompra + ", productosComprados="
				+ productosComprados + ", fechaCompra=" + fechaCompra + "]";
	}	
}
