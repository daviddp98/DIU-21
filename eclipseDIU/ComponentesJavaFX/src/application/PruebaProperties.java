package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

class Bill {

	// Definir una variable para almacenar la propiedad
	private DoubleProperty amountDue= new SimpleDoubleProperty();

	// Definir un captador para el valor de la propiedad
	public final double getAmountDue() {
		return amountDue.get();
	}

	// Definir un setter para el valor de la propiedad
	public final void setAmountDue(double value) {
		amountDue.set(value);
	}

	// Definir un captador para la propiedad en sí
	public DoubleProperty amountDueProperty() {
		return amountDue;
	}
}