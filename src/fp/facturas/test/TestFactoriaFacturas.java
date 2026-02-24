package fp.facturas.test;

import java.util.List;

import fp.facturas.FactoriaFacturas;
import fp.facturas.Factura;

public class TestFactoriaFacturas {
	public static void testFactoria() {
		List<Factura> facturas = FactoriaFacturas.leeFacturas("./data/CSV de la sesión 1.csv");
		for (Factura factura : facturas) {
			System.out.println(factura);
		}
	}

	public static void main(String[] args) {
		try {
			testFactoria();
		} catch (Exception e) {
			System.out.println("Excepción capturada.");
			System.out.println(e.getMessage());
		}

	}

}
