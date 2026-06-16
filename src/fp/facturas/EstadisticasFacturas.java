package fp.facturas;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
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
		return getFacturas().stream()
				.filter(f -> f.getCliente().equals(cliente))
				.sorted(Comparator.comparing(Factura::getFecha).reversed())
				.limit(n)
				.map(Factura::getPrecioTotal)
				.collect(Collectors.toList());
	}
	
//	2. getProductoMasCaroPosteriorFecha: Dada una fecha, devuelve el 
//	producto de mayor precio de las facturas que se hayan realizado 
//	con posterioridad a esa fecha. Si no se puede calcular, eleva 
//	NoSuchElementException. (1 pto)
	
	public Producto getProductoMasCaroPosteriorFecha(LocalDate fecha) {
		return getFacturas().stream()
				.filter(f -> f.getFecha().isAfter(fecha))
				.flatMap(f -> f.getProductos().stream())
				.collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Producto::precio)), 
						o -> o.get()));
	}
	
//	3. getNumeroDeCategoriasDistintasPorCliente: Devuelve un SortedMap en el 
//	que se asocia a cada cliente con el número de categorías distintas de productos 
//	compradas por ese cliente. Implemente este método con bucles. (1,5 ptos)
	
	public SortedMap<String, Integer> getNumeroDeCategoriasDistintasPorCliente() {
		
		SortedMap<String, Set<String>> dicc = new TreeMap<>();
		
		for (Factura f : getFacturas()) {
			for (Producto p : f.getProductos()) {
				String clave = f.getCliente();
				String valor = p.categoria();
				Set<String> conjunto = new HashSet<>();
				if (dicc.containsKey(clave)) {
					dicc.get(clave).add(valor);
				} else {
					conjunto.add(valor);
					dicc.put(clave, conjunto);
				}
			}
		}
		
		SortedMap<String, Integer> dicc2 = new TreeMap<>();
		
		for (Map.Entry<String, Set<String>> par : dicc.entrySet()) {
			dicc2.put(par.getKey(), par.getValue().size());
		}
		
		return dicc2;
	}
	
//	4. getPorcentajeFacturasDePaisPorTipoCliente: Dado un país, devuelve un SortedMap 
//	ordenado alfabéticamente que indique, para cada tipo de cliente (REGULAR, PREMIUM, VIP), 
//	qué porcentaje representan sus facturas respecto al total de facturas emitidas en ese país.(1,5 ptos)
	
	public SortedMap<TipoCliente, Double> getPorcentajeFacturasDePaisPorTipoCliente(String pais) {
		Long numFacturasPais = getFacturas().stream()
				.filter(f -> f.getPais().equals(pais))
				.count();
		
		return getFacturas().stream()
				.filter(f -> f.getPais().equals(pais))
				.collect(Collectors.groupingBy(Factura::getTipoCliente, 
						TreeMap::new,
						Collectors.collectingAndThen(Collectors.counting(), 
								f -> 100.0 * f / numFacturasPais)));
				
	}
	
//	5. getGastoTotalSuperiorUmbralPorClienteConCategoria: Dada una categoría y un umbral numérico, 
//	devuelve un Map en el que a cada cliente le hace corresponder su gasto total acumulado, contando 
//	solo aquellas facturas que incluyen al menos un producto de esa categoría. El Map resultado solo debe 
//	incluir aquellos clientes cuyo gasto total acumulado supere el umbral. (1,5 ptos)
	
	public Map<String, Double> getGastoTotalSuperiorUmbralPorClienteConCategoria(String categoria, Double umbral) {
		Map<String, Double> res = getFacturas().stream()
				.filter(f -> f.getProductos().stream()
	                    .anyMatch(p -> p.categoria().equals(categoria)))
				.collect(Collectors.groupingBy(Factura::getCliente, 
						Collectors.summingDouble(Factura::getPrecioTotal)));
		
		return res.entrySet().stream()
				.filter(f -> f.getValue() > umbral)
				.collect(Collectors.toMap(Map.Entry::getKey, 
						Map.Entry::getValue));
	}
	
}
