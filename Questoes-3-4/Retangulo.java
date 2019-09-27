package quadrado;

public class Retangulo extends Quadrado {
    
    private double comprimento;
    
    public Retangulo(double lado, double comprimento) {
        super(lado);
        this.comprimento = comprimento;
    }
    
    public double calcularArea() {
        return lado * comprimento;
    }

    public double getComprimento() {
        return comprimento;
    }
    
}