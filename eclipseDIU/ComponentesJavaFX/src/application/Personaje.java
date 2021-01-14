package application;

public class Personaje {

	private String nombre;
	private int poder;
	private boolean superpoder;
	private Estrategia estrategia;

	public Personaje(String nombre, int poder, boolean superpoder, Estrategia estrategia) {
		this.nombre = nombre;
		this.poder = poder;
		this.superpoder = superpoder;
		this.estrategia = estrategia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPoder() {
		return poder;
	}

	public void setPoder(int poder) {
		this.poder = poder;
	}

	public boolean isSuperpoder() {
		return superpoder;
	}

	public void setSuperpoder(boolean superpoder) {
		this.superpoder = superpoder;
	}

	public Estrategia getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(Estrategia estrategia) {
		this.estrategia = estrategia;
	}

	public String toString() {
		String personajeStr = String.format("Nombre: %s, Poder: %d, Superpoder: %b, Estrategia: %s", nombre, poder,
				superpoder, estrategia);
		return personajeStr;
	}
}