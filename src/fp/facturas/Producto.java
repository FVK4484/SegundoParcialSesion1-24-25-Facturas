package fp.facturas;

import fp.utiles.Checkers;

public record Producto(String nombre, Double precio, String categoria) {
	
	public Producto {
		
		Checkers.check("El precio no puede ser negativo", 
				precio >= 0.);
		Checkers.check("La categoría no puede tener más de 100 caracteres", 
				categoria.length() <= 100);
		
	}

}
