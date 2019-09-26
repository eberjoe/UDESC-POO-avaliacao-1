package quadrado;

public class Quadrado {
    
    protected double lado;
    
    public void Quadrado(double lado) {
        this.lado = lado;
    }
    
    public double calcularArea() {
        return lado * lado;
    }

    public double getLado() {
        return lado;
    }
    
}
