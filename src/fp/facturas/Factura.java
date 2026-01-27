package fp.facturas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class Factura implements Comparable<Factura> {
	
	private String numero;
	private LocalDate fecha;
	private String cliente;
	private String pais; 
	private Double impuestos; 
	private TipoCliente tipoCliente; 
	private List<Producto> productos;
	
	public Factura(String numero, LocalDate fecha, String cliente, String pais, Double impuestos,
			TipoCliente tipoCliente, List<Producto> productos) {
		this.numero = numero;
		this.fecha = fecha;
		setCliente(cliente);
		setPais(pais);
		setImpuestos(impuestos); 
		setTipoCliente(tipoCliente);
		Checkers.check("La lista de productos no puede estar vacía",
				productos.size() >= 0);
		this.productos = new ArrayList<>(productos);
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Double getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(Double impuestos) {
			Checkers.check("El porcentaje de impuestos debe estar entre 0 y 100"
					, impuestos >= 0. && impuestos <= 100.);
		this.impuestos = impuestos;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getNumero() {
		return numero;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public List<Producto> getProductos() {
		return new ArrayList<>(productos);
	}
	
	public Double getPrecioTotal() { 
		Double precio = productos.stream()
				.mapToDouble(Producto::precio)
				.sum(); 
		return precio + precio * getImpuestos() / 100.;
	} 
	
//	También con bucles.
//	public Double getPrecioTotal2() { 
//		Double precio = 0.0; 
//		for (Producto producto: productos) {
//			precio += producto.precio();
//		} 
//		return precio + precio * getImpuestos() / 100.;
//	}
	
	public Integer getNumeroProductos() {
		return productos.size();
	}
	
	public Integer getNumeroCategorias() {
		Long res = productos.stream()
				.map(Producto::categoria)
				.distinct()
				.count();
		return res.intValue();
	}
	
//	También con bucles.
//	public Integer getNumeroCategorias2() { 
//		Set<String> res = new HashSet<>(); 
//		for (Producto producto: productos) { 
//			res.add(producto.categoria()); 
//		}
//		return res.size(); 
//	}
	
	public String toString() {
		return "Factura [numero=" + numero + ", fecha=" + fecha + ", cliente=" + cliente + ", pais=" + pais
				+ ", impuestos=" + impuestos + ", tipoCliente=" + tipoCliente + ", productos=" + productos + "]";
	}

	public int hashCode() {
		return Objects.hash(fecha, numero);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		return Objects.equals(fecha, other.fecha)
				&& Objects.equals(numero, other.numero);
	}
	
	public int compareTo(Factura factura) {
		int res = getFecha().compareTo(factura.getFecha());
		if (res == 0) { 
			res = getNumero().compareTo(factura.getNumero()); 
		} 
		return res; 
	}
	
}
