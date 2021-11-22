package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Administrador;
import modelo.Cliente;
import modelo.Compra;
import modelo.Producto;
import modelo.UsuarioReporte;

public class App {
		
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Bienvenido a la tienda");
		String email = JOptionPane.showInputDialog("Ingrese su correo: ");
		String password = JOptionPane.showInputDialog("Ingrese su password: ");
		int tipo = login(email, password);
		switch (tipo) {
		case 1:
			JOptionPane.showMessageDialog(null, "Bienvenido administrador");
			String opcion = JOptionPane.showInputDialog(
					"Opciones: \n" + "Opcion 1 Registrar un cliente \n" + "Opcion 2 Registrar un administrador \n"
							+ "Opcion 3 Registrar un usuario de reportes \n" + "Opcion 4 Crear producto \n"
							+ "Opcion 5 eliminar producto \n" + "Porfavor ingrese la opción deseada: ");
			opcionesAdmin(Integer.parseInt(opcion));
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Bienvenido cliente");
			String opcion2 = JOptionPane.showInputDialog("Opciones: \n" + "Opcion 1 Agregar saldo \n"
					+ "Opcion 2 Consultar productos \n" + "Opcion 3 Comprar productos \n"
					+ "Opcion 4 Ver mis compras \n" + "Porfavor ingrese la opción deseada: ");
			Cliente cliente = obtenerCliente(email, password);
			opcionesCliente(Integer.parseInt(opcion2), cliente);
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "Bienvenido usuario de reportes");
			String opcion3 = JOptionPane.showInputDialog("Opciones: \n" + "Opcion 1 Ver el total de usuarios \n"
					+ "Opcion 2 Ver el total de ventas \n" + "Opcion 3 Desglose de ventas \n"
					+ "Opcion 4 Top de productos vendidos \n" + "Porfavor ingrese la opción deseada: ");
			opcionesUsuario(Integer.parseInt(opcion3));
			break;

		default:
			JOptionPane.showMessageDialog(null, "Email o contraseña erradas");
			break;
		}

	}

	private static void opcionesUsuario(int opcion) {
		List<Administrador> admins = obtenerAdmins();
		List<Cliente> clientes = obtenerClientes();
		List<UsuarioReporte> usuarios  = obtenerUsuarioReporte();
		String mensajeVentas = "";
		int ventasTotal = 0;
		for (Cliente cliente : clientes) {
			mensajeVentas += "El cliente " + cliente.getNombre() + " realizo las compras: " + "\n";  
			List<Compra> compras = cliente.getMisCompras();
			for (int i = 0; i < compras.size(); i++) {
				ventasTotal++;
				mensajeVentas += "Id de la compra: " + compras.get(i).getIdCompra() + "\n";
				List<Producto> productos = compras.get(i).getProductosComprados();
				mensajeVentas += "Productos comprados: " + "\n";
				for (int j = 0; j < productos.size(); j++) {
					mensajeVentas +=productos.get(j).getNombreProducto() + "\n";
				}
			}
		}
		switch (opcion) {
		case 1:
			String usuariosTotales = "";
			int total = 0;
			for (Administrador administrador : admins) {
				usuariosTotales += "Administrador: "+administrador.getNombre() + "\n";
				total++;
			}
			for (Cliente cliente : clientes) {
				usuariosTotales += "Cliente: "+cliente.getNombre() + "\n";
				total++;
			}
			for (UsuarioReporte usuarioReporte : usuarios) {
				usuariosTotales += "Usuario reporte: "+usuarioReporte.getNombre() + "\n";
				total++;
			}
			JOptionPane.showMessageDialog(null, "Usuarios: " + "\n" + usuariosTotales + "Total de usuarios: " + total);
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Ventas totales: " + ventasTotal);
			break;
		case 3:
			JOptionPane.showMessageDialog(null, mensajeVentas);
			break;
		case 4:
			JOptionPane.showMessageDialog(null, mensajeVentas);
			break;
		default:
			break;
		}

	}

	private static Cliente obtenerCliente(String email, String password) {
		List<Cliente> clientes = obtenerClientes();
		for (Cliente cliente : clientes) {
			if (cliente.getEmail().equals(email) && cliente.getPassword().equals(password)) {
				return cliente;
			}
		}
		return null;

	}

	private static void opcionesCliente(int opcion, Cliente cliente) {
		String mensaje = "";
		List<Producto> productos = obtenerProductos();
		for (Producto producto : productos) {
			mensaje += "Nombre: " + producto.getNombreProducto() + " Descripcion: " + producto.getDescripcionProducto()
					+ " Precio: " + producto.getPrecioProducto() + "\n";
		}
		switch (opcion) {
		case 1:
			double saldo = Double
					.parseDouble(JOptionPane.showInputDialog("Ingrese el valor que desea adicionar a su cuenta"));
			List<Cliente> clientes = obtenerClientes();
			cliente.setSaldoDisponible(cliente.getSaldoDisponible() + saldo);
			int pos = 0;
			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getIdCliente() == cliente.getIdCliente()) {
					pos = i;
				}
			}
			clientes.set(pos, cliente);
			actualizarListaClientes(clientes);
			JOptionPane.showMessageDialog(null, "Saldo actualizado con exito");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, mensaje);
			break;
		case 3:
			comprarProductos(mensaje, cliente);
			break;
		case 4:
			if (cliente.getMisCompras() != null) {
				List<Compra> compras = cliente.getMisCompras();
				String mensajeC = "";
				for (Compra compra : compras) {
					String fecha = compra.getFechaCompra().getDay() + "/" + compra.getFechaCompra().getMonth() + "/"
							+ compra.getFechaCompra().getYear();
					String productosC = "";
					List<Producto> productosCompra = compra.getProductosComprados();
					for (Producto pro : productosCompra) {
						productosC += "Nombre: " + pro.getNombreProducto() + " Precio: " + pro.getPrecioProducto()
								+ "\n";
					}
					mensajeC += "Fecha: " + fecha + "\n" + "Productos comprados: " + "\n" + productosC + "\n";
				}
				JOptionPane.showMessageDialog(null, mensajeC);
			} else {
				JOptionPane.showMessageDialog(null, "No tiene compras");
			}
			break;
		default:
			JOptionPane.showMessageDialog(null, "La opcion ingresada no es valida");
		}

	}

	private static void comprarProductos(String mensaje, Cliente cliente) {
		String cadena = JOptionPane
				.showInputDialog(mensaje + "\n" + "Ingrese el nombre del producto que desea comprar, "
						+ "si desea comprar más productos separelos por una coma, ejemplo (mouse,teclado,pantalla)");
		String nombres[] = cadena.split(",");
		List<Producto> productosCompra = busquedaPorNombreProducto(nombres);
		double valor = 0;
		for (Producto producto : productosCompra) {
			valor += producto.getPrecioProducto();
		}
		if (cliente.getSaldoDisponible() - valor > 0) {
			int idCompra = 1;
			if (cliente.getMisCompras() != null) {
				idCompra = cliente.getMisCompras().size();

			}
			Date fecha = new Date();
			Compra compra = new Compra(idCompra + 1, cliente, productosCompra, fecha);
			if (cliente.getMisCompras() != null) {
				cliente.getMisCompras().add(compra);
			}
			cliente.setSaldoDisponible(cliente.getSaldoDisponible() - valor);
			List<Cliente> clientes = obtenerClientes();
			for (int i = 0; i < clientes.size(); i++) {
				if (cliente.getIdCliente() == clientes.get(i).getIdCliente()) {
					clientes.set(i, cliente);
				}
			}
			actualizarListaClientes(clientes);
			JOptionPane.showMessageDialog(null, "Compra realizada con exito");
		} else {
			JOptionPane.showMessageDialog(null, "Saldo insuficiente");
		}
	}

	private static List<Producto> busquedaPorNombreProducto(String[] nombres) {
		List<Producto> productosComprados = new ArrayList<>();
		List<Producto> productos = obtenerProductos();
		for (Producto producto : productos) {
			for (int i = 0; i < nombres.length; i++) {
				if (producto.getNombreProducto().equals(nombres[i])) {
					productosComprados.add(producto);
				}
			}
		}
		return productosComprados;
	}

	private static void actualizarListaClientes(List<Cliente> clientes) {
		File archivoClientes = new File("info/clientes.txt");
		ObjectOutputStream oos;
		boolean centinela = true;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(archivoClientes));
			oos.reset();
			oos.writeObject(clientes);
			oos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error en el archivo clientes.txt");
		}

	}

	private static void opcionesAdmin(int opcion) {
		switch (opcion) {
		case 1:
			crearCliente();
			break;
		case 2:
			crearAdmin();
			break;
		case 3:
			crearUsuarioReporte();
			break;
		case 4:
			crearProducto();
			break;
		case 5:
			eliminarProducto();
			break;
		default:
			JOptionPane.showMessageDialog(null, "La opcion ingresada no es valida");
		}

	}

	public static void crearCliente() {
		try {
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
			String email = JOptionPane.showInputDialog("Ingrese el email del cliente");
			String password = JOptionPane.showInputDialog("Ingrese la contraseña del cliente");
			int id = obtenerClientes().size() + 1;
			double saldo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el saldo inicial del cliente"));
			Cliente cliente = new Cliente(id, email, password, nombre, saldo);
			guardarCliente(cliente);
			JOptionPane.showMessageDialog(null, "Cliente creado con exito");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Los valores ingresados son erroneos");
		}
	}

	public static void crearAdmin() {
		try {
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre del administrador");
			String email = JOptionPane.showInputDialog("Ingrese el email del administrador");
			String password = JOptionPane.showInputDialog("Ingrese la contraseña del administrador");
			int id = obtenerAdmins().size() + 1;
			Administrador admin = new Administrador(id, email, password, nombre);
			guardarClienteAdmin(admin);
			JOptionPane.showMessageDialog(null, "Admin creado con exito");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Los valores ingresados son erroneos");
		}
	}

	public static void crearUsuarioReporte() {
		try {
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario");
			String email = JOptionPane.showInputDialog("Ingrese el email del usuario");
			String password = JOptionPane.showInputDialog("Ingrese la contraseña del usuario");
			int id = obtenerUsuarioReporte().size() + 1;
			UsuarioReporte user = new UsuarioReporte(id, email, password, nombre);
			guardarUsuarioReporte(user);
			JOptionPane.showMessageDialog(null, "Usuario creado con exito");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Los valores ingresados son erroneos");
		}
	}

	public static void crearProducto() {
		try {
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto");
			String descripcion = JOptionPane.showInputDialog("Ingrese una descripcion para el producto");
			int id = obtenerProductos().size() + 1;
			int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad disponible"));
			double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto"));
			Producto producto = new Producto(id, nombre, descripcion, precio, cantidad);
			guardarProducto(producto);
			JOptionPane.showMessageDialog(null, "Producto creado con exito");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Los valores ingresados son erroneos");
		}
	}

	public static void eliminarProducto() {
		File archivoProductos = new File("info/productos.txt");
		ObjectOutputStream oos;
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de el producto a eliminar"));
			List<Producto> productos = obtenerProductos();
			List<Producto> productosDelete = new ArrayList<>();
			for (Producto producto : productos) {
				if (producto.getIdProducto() != id) {
					productosDelete.add(producto);
				}
			}

			oos = new ObjectOutputStream(new FileOutputStream(archivoProductos));
			oos.reset();
			oos.writeObject(productosDelete);
			oos.close();
			JOptionPane.showMessageDialog(null, "El producto fue eliminado");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Los valores ingresados son erroneos");
		}
	}

	public static int login(String email, String password) {
		List<Administrador> admins = obtenerAdmins();
		for (Administrador administrador : admins) {
			if (administrador.getEmail().equals(email) && administrador.getPassword().equals(password)) {
				return 1;
			}
		}
		List<Cliente> clientes = obtenerClientes();
		for (Cliente cliente : clientes) {
			if (cliente.getEmail().equals(email) && cliente.getPassword().equals(password)) {
				return 2;
			}
		}
		List<UsuarioReporte> usuarios = obtenerUsuarioReporte();
		for (UsuarioReporte usuarioReporte : usuarios) {
			if (usuarioReporte.getEmail().equals(email) && usuarioReporte.getPassword().equals(password)) {
				return 3;
			}
		}
		return 0;
	}

	public static void guardarClienteAdmin(Administrador administrador) {
		File archivoAdministrador = new File("info/administradores.txt");
		ObjectOutputStream oos;
		List<Administrador> administradores = obtenerAdmins();
		boolean centinela = true;
		try {
			for (Administrador administrador2 : administradores) {
				if (administrador.getIdAdministrador() == administrador2.getIdAdministrador()) {
					centinela = false;
				}
			}
			if (centinela == true) {
				administradores.add(administrador);
				oos = new ObjectOutputStream(new FileOutputStream(archivoAdministrador));
				oos.reset();
				oos.writeObject(administradores);
				oos.close();
			} else {
				JOptionPane.showMessageDialog(null, "El administrador ya existe");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Se creo el archivo administradores.txt");
		}
	}

	public static List<Administrador> obtenerAdmins() {
		File archivoAdministrador = new File("info/administradores.txt");
		ObjectInputStream ois;
		List<Administrador> administradores = new ArrayList<>();
		try {
			ois = new ObjectInputStream(new FileInputStream(archivoAdministrador));
			administradores = (List<Administrador>) ois.readObject();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error en el archivo administradores.txt");
		}
		return administradores;
	}

	public static void guardarProducto(Producto producto) {
		File archivoProductos = new File("info/productos.txt");
		ObjectOutputStream oos;
		List<Producto> productos = obtenerProductos();
		boolean centinela = true;
		try {
			for (Producto producto2 : productos) {
				if (producto.getIdProducto() == producto2.getIdProducto()) {
					centinela = false;
				}
			}
			if (centinela == true) {
				productos.add(producto);
				oos = new ObjectOutputStream(new FileOutputStream(archivoProductos));
				oos.reset();
				oos.writeObject(productos);
				oos.close();
			} else {
				JOptionPane.showMessageDialog(null, "El producto ya existe");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Se creo el archivo productos.txt");
		}
	}

	public static List<Producto> obtenerProductos() {
		File archivoProductos = new File("info/productos.txt");
		ObjectInputStream ois;
		List<Producto> productos = new ArrayList<>();
		try {
			ois = new ObjectInputStream(new FileInputStream(archivoProductos));
			productos = (List<Producto>) ois.readObject();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error en el archivo productos.txt");
		}
		return productos;
	}

	public static void guardarCliente(Cliente cliente) {
		File archivoClientes = new File("info/clientes.txt");
		ObjectOutputStream oos;
		List<Cliente> clientes = obtenerClientes();
		boolean centinela = true;
		try {
			for (Cliente cliente2 : clientes) {
				if (cliente.getIdCliente() == cliente2.getIdCliente()) {
					centinela = false;
					System.out.println(cliente.getIdCliente() + "  " + cliente2.getIdCliente());
				}
			}
			if (centinela == true) {
				clientes.add(cliente);
				oos = new ObjectOutputStream(new FileOutputStream(archivoClientes));
				oos.reset();
				oos.writeObject(clientes);
				oos.close();
			} else {
				JOptionPane.showMessageDialog(null, "El Cliente ya existe");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Se creo el archivo clientes.txt");
		}
	}

	public static List<Cliente> obtenerClientes() {
		File archivoClientes = new File("info/clientes.txt");
		ObjectInputStream ois;
		List<Cliente> clientes = new ArrayList<>();
		try {
			ois = new ObjectInputStream(new FileInputStream(archivoClientes));
			clientes = (List<Cliente>) ois.readObject();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error en el archivo clientes.txt");
		}
		return clientes;
	}

	public static void guardarUsuarioReporte(UsuarioReporte usuarioReporte) {
		File archivoUsuarioReporte = new File("info/usuarioReporte.txt");
		ObjectOutputStream oos;
		List<UsuarioReporte> usuariosReportes = obtenerUsuarioReporte();
		boolean centinela = true;
		try {
			for (UsuarioReporte usuarioReporte2 : usuariosReportes) {
				if (usuarioReporte.getIdCliente() == usuarioReporte2.getIdCliente()) {
					centinela = false;
				}
			}
			if (centinela == true) {
				usuariosReportes.add(usuarioReporte);
				oos = new ObjectOutputStream(new FileOutputStream(archivoUsuarioReporte));
				oos.reset();
				oos.writeObject(usuariosReportes);
				oos.close();
			} else {
				JOptionPane.showMessageDialog(null, "El Usuario ya existe");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Se creo el archivo usuarioReporte.txt");
		}
	}

	public static List<UsuarioReporte> obtenerUsuarioReporte() {
		File archivoUsuarioReporte = new File("info/usuarioReporte.txt");
		ObjectInputStream ois;
		List<UsuarioReporte> usuariosReportes = new ArrayList<>();
		try {
			ois = new ObjectInputStream(new FileInputStream(archivoUsuarioReporte));
			usuariosReportes = (List<UsuarioReporte>) ois.readObject();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error en el archivo clientes.txt");
		}
		return usuariosReportes;
	}
}
