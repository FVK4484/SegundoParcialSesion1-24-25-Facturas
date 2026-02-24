package fp.facturas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EstadisticasFacturas extends Facturas{

	public EstadisticasFacturas(Stream<Factura> facturas) {
		super(facturas);
	}

	public String toString() {
		return super.toString() + " Numero facturas: " + getFacturas().size();
	}
	
//	1. getPrecioTotalNFacturasMasRecientesCliente: Dado un nombre de 
//	un cliente y un número entero n, devuelve la lista con los precios 
//	totales de las n facturas más recientes de ese cliente. (1 pto)
	public List<Double> getPrecioTotalNFacturasMasRecientesCliente(String cliente, Integer n) {
		
		Comparator<Factura> cmp = Comparator.comparing(Factura::getFecha);
		
		return getFacturas().stream()
				.filter(factura -> factura.getCliente().equals(cliente))
				.sorted(cmp.reversed())
				.limit(n)
				.map(Factura::getPrecioTotal)
				.collect(Collectors.toList());
				
	}
	
//	2. getProductoMasCaroPosteriorFecha: Dada una fecha, devuelve el 
//	producto de mayor precio de las facturas que se hayan realizado 
//	con posterioridad a esa fecha. Si no se puede calcular, eleva 
//	NoSuchElementException. (1 pto)
	

}
