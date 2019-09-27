package quadrado;

public class Cubo extends Quadrado {
    
    public Cubo(double lado) {
        super(lado);
    }
    
    public double calcularArea() {
        return super.calcularArea() * 6;
    }
    
}
