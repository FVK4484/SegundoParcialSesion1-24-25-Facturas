package fp.facturas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Facturas {
	private List<Factura> facturas;

	public Facturas(Stream<Factura> facturas) {
		this.facturas = facturas.toList();
	}

	public List<Factura> getFacturas() {
		return new ArrayList<>(facturas);
	}

	public String toString() {
		return "Facturas [facturas=" + facturas + "]";
	}

	public int hashCode() {
		return Objects.hash(facturas);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Facturas other = (Facturas) obj;
		return Objects.equals(facturas, other.facturas);
	}
	
}
