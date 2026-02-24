package fp.facturas;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaFacturas {
	public static Factura parsearFactura(String lineaCSV) {
		Checkers.checkNoNull(lineaCSV);
		String [] trozos = lineaCSV.split(",");
		Checkers.check("Formato no válido.", trozos.length == 7);
		String numero = trozos[0].trim();
		LocalDate fecha = LocalDate.parse(trozos[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String cliente = trozos[2].trim();
		String pais = trozos[3].trim();
		Double impuestos = Double.valueOf(trozos[4].trim());
		TipoCliente tipoCliente = TipoCliente.valueOf(trozos[5].trim().toUpperCase());
		List<Producto> productos = parsearProductos(trozos[6].trim());
		Factura factura = new Factura(numero, fecha, cliente, pais, impuestos, tipoCliente, productos);
		return factura;
	}
	
	private static List<Producto> parsearProductos(String productosStr) {
		List<Producto> productos = new ArrayList<>();
		String productosStrNuevos = productosStr.replace("'", "").replace("'", "");
		String [] trozos = productosStrNuevos.split(";");
		for (String trozo : trozos)  {
			String trozoNuevo = trozo.replace("[", "").replace("]", "");
			String [] lineas = trozoNuevo.split("-");
			Checkers.check("Formato no válido.", lineas.length == 3);
			String nombre = lineas[0].trim();
			Double precio = Double.valueOf(lineas[1].trim());
			String categoria = lineas[2].trim();
			Producto producto = new Producto(nombre, precio, categoria);
			productos.add(producto);
		}
		return productos;
	}
	
	public static List<Factura> leeFacturas(String fichero) {
		Checkers.checkNoNull(fichero);
		String errMsg = String.format("Error leyendo fichero <%s>", fichero);
		List<String> lineas = Ficheros.leeFichero(errMsg, fichero, StandardCharsets.UTF_8);
		List<Factura> res = lineas.stream()
				                  .skip(1)
				                  .map(linea -> parsearFactura(linea))
				                  .collect(Collectors.toList());
		return res;
	}
	
}
