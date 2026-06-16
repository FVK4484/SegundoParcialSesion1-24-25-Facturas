package fp.facturas.test;

import java.time.LocalDate;
import java.util.List;

import fp.facturas.EstadisticasFacturas;
import fp.facturas.FactoriaFacturas;
import fp.facturas.Factura;

public class TestEstadisticasFacturas {

	public static void main(String[] args) {
		List<Factura> facturas = FactoriaFacturas.leeFacturas("./data/CSV de la sesión 1.csv"); 
		EstadisticasFacturas est = new EstadisticasFacturas(facturas.stream());
		System.out.println(" EJ4.1 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ="); 
		testGetPrecioTotalNFacturasMasRecientesCliente(est, "Gary Santiago", 3); 
		testGetPrecioTotalNFacturasMasRecientesCliente(est, "Lisa Byrd", 2);
		System.out.println(" EJ4.2 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		testGetProductoMasCaroPosteriorFecha(est, LocalDate.of(2025, 2, 1));
		System.out.println(" EJ4.3 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		testGetNumeroDeCategoriasDistintasPorCliente(est);
		System.out.println(" EJ4.4 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		testGetPorcentajeFacturasDePaisPorTipoCliente(est, "Estados Unidos");
		testGetPorcentajeFacturasDePaisPorTipoCliente(est, "España");
		System.out.println(" EJ4.5 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		testGetGastoTotalSuperiorUmbralPorClienteConCategoria(est, "Muebles", 8000.);
		testGetGastoTotalSuperiorUmbralPorClienteConCategoria(est, "Electrónica", 8000.);
	}
	
	private static void testGetPrecioTotalNFacturasMasRecientesCliente (EstadisticasFacturas est, String cliente, Integer n) {
		try {
			System.out.println(" Los precios totales de las " + n + 
					" últimas facturas del cliente " + cliente + 
					" son: " + est.getPrecioTotalNFacturasMasRecientesCliente(cliente, n));
		} catch (Exception e) {
			System.out.println(" Excepción capturada.");
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	private static void testGetProductoMasCaroPosteriorFecha(EstadisticasFacturas est, LocalDate fecha) {
		try {
			System.out.println(" El producto más caro de las facturas posteriores a " + fecha + 
					" es: ");
			System.out.println(" " + est.getProductoMasCaroPosteriorFecha(fecha));
		} catch (Exception e) {
			System.out.println(" Excepción capturada.");
			System.out.println(e.getLocalizedMessage());
		}
	}	
	
	private static void testGetNumeroDeCategoriasDistintasPorCliente(EstadisticasFacturas est) {
		try {
			System.out.println(" El número de categorías distintas por cliente es: ");
			System.out.println(" " + est.getNumeroDeCategoriasDistintasPorCliente());
		} catch (Exception e) {
			System.out.println(" Excepción capturada.");
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	private static void testGetPorcentajeFacturasDePaisPorTipoCliente(EstadisticasFacturas est, String pais) {
		try {
			System.out.println(" El porcentaje de facturas por tipo de cliente del país " + pais + " es: ");
			System.out.println(" " + est.getPorcentajeFacturasDePaisPorTipoCliente(pais));
		} catch (Exception e) {
			System.out.println(" Excepción capturada.");
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	private static void testGetGastoTotalSuperiorUmbralPorClienteConCategoria(EstadisticasFacturas est, String categoria, Double umbral) {
		try {
			System.out.println(" El gasto total de los clientes que superan el umbral " + umbral + " en las facturas con al menos un producto de la categoría " + categoria + " es: "
					+ est.getGastoTotalSuperiorUmbralPorClienteConCategoria(categoria, umbral));
		} catch (Exception e) {
			System.out.println(" Excepción capturada.");
			System.out.println(e.getLocalizedMessage());
		}
	}
	
}