package modelo;

import java.io.Serializable;

public class UsuarioReporte implements Serializable{
	
	private int idCliente;
	private String email;
	private String password;
	private String nombre;
	
	public UsuarioReporte() {
		
	}

	public UsuarioReporte(int idCliente, String email, String password, String nombre) {
		this.idCliente = idCliente;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "UsuarioReporte [idCliente=" + idCliente + ", email=" + email + ", password=" + password + ", nombre="
				+ nombre + "]";
	}
	
}
