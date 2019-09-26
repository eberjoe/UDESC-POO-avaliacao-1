package quadrado;

public class Retangulo extends Quadrado {
    
    private double comprimento;
    
    public void Retangulo(double lado, double comprimento) {
        super(lado);
        this.comprimento = comprimento;
    }

    public double getComprimento() {
        return comprimento;
    }
    
}
