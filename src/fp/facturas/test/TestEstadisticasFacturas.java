package fp.facturas.test;

import java.util.Collection;
import java.util.List;

import fp.facturas.EstadisticasFacturas;
import fp.facturas.FactoriaFacturas;
import fp.facturas.Factura;

public class TestEstadisticasFacturas {
	
	public static void mostrarFacturas(Collection<Factura> col) { 
		col.stream() 
		.forEach(System.out::println); 
	}

	public static void main(String[] args) {
		EstadisticasFacturas est = (EstadisticasFacturas) FactoriaFacturas.leeFacturas("./data/CSV de la sesión 1.csv"); 
		mostrarFacturas(est.getFacturas());
		System.out.println("EJERCICIO 1" + "=".repeat(70)); 
		testGetPrecioTotalNFacturasMasRecientesCliente(est, "Gary Santiago", 3); 
		testGetPrecioTotalNFacturasMasRecientesCliente(est, "Lisa Byrd", 2);
	}
	
	private static void testGetPrecioTotalNFacturasMasRecientesCliente (EstadisticasFacturas est, String cliente, Integer n) { 
		try { 
			List<Double> res = est 
					.getPrecioTotalNFacturasMasRecientesCliente (cliente, n); 
			String msg = String.format("Los precios de las %d facturas más recientes del cliente %s son:\n %s", n, cliente, res.toString()); 
			System.out.println(msg); 
			} catch (Exception e) { 
				System.out.println(">>>Capturada excepcion inesperada" + e.getMessage()); 
			} 
	}

}
