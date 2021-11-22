package modelo;

import java.io.Serializable;
import java.util.List;

public class Cliente implements Serializable{
	
	private int idCliente;
	private String email;
	private String password;
	private String nombre;
	private double saldoDisponible;
	private List<Compra> misCompras;
	
	public Cliente() {

	}
	public Cliente(int idCliente, String email, String password, String nombre, double saldoDisponible) {
		this.idCliente = idCliente;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.saldoDisponible = saldoDisponible;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	public List<Compra> getMisCompras() {
		return misCompras;
	}
	public void setMisCompras(List<Compra> misCompras) {
		this.misCompras = misCompras;
	}
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", email=" + email + ", password=" + password + ", nombre=" + nombre
				+ ", saldoDisponible=" + saldoDisponible + "]";
	}
}
