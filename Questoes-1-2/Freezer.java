package freezer;

import java.util.Dictionary;
import java.util.Hashtable;

public class Freezer {
    
    private String selecaoTemperatura;
    
    Compressor compressor = new Compressor();
    Ventilador ventilador = new Ventilador();
    Resistencia resistencia = new Resistencia();
    Sensor sensor = new Sensor();
    
    public String getSelecaoTemperatura() {
        return selecaoTemperatura;
    }

    public void setSelecaoTemperatura(String selecaoTemperatura) {
        this.selecaoTemperatura = selecaoTemperatura;
    }

}
