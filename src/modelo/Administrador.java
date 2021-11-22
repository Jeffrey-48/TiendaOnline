package modelo;

import java.io.Serializable;

public class Administrador implements Serializable{

	private int idAdministrador;
	private String email;
	private String password;
	private String nombre;
	
	public Administrador() {

	}
	public Administrador(int idAdministrador, String email, String password, String nombre) {
		this.idAdministrador = idAdministrador;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
	}
	public int getIdAdministrador() {
		return idAdministrador;
	}
	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
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
	@Override
	public String toString() {
		return "Administrador [idAdministrador=" + idAdministrador + ", email=" + email + ", password=" + password
				+ ", nombre=" + nombre + "]";
	}
}
