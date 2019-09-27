package quadrado;

public class AreaApp {
    
    public static void main(String[] args) {
        
        Quadrado quadrado = new Quadrado(4);
        Retangulo retangulo = new Retangulo(4, 8);
        Cubo cubo = new Cubo(5);
        
        System.out.println(quadrado.calcularArea());
        System.out.println(retangulo.calcularArea());
        System.out.println(cubo.calcularArea());
        
    }
    
}
