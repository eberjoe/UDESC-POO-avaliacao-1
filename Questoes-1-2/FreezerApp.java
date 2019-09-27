package freezer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class FreezerApp {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		// Tabela de conversão da seleção de temperatura para valores em graus C
		Hashtable<String, Float> selecao = new Hashtable<String, Float>();
		selecao.put("mínima", (float) -10.5);
		selecao.put("média", (float) -3);
		selecao.put("máxima", (float) 3.6);
				
		Freezer freezer = new Freezer();
		
		freezer.sensor.setTemperatura(25); // coloca a temperatura inicial do freezer a 25 graus
		
		BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		
		System.out.print("Selecione a temperatura (mínima, média ou máxima): ");
		freezer.setSelecaoTemperatura(reader.readLine().toLowerCase());
		
		System.out.println("A temperatura " + freezer.getSelecaoTemperatura() + " é de " + selecao.get(freezer.getSelecaoTemperatura()) + "graus C.");
		
		float vent, comp, res;
		long start = 0, end = 0, startRes = 0, endRes = 0;
		
		do {
			// incremento ou decremento da temperatura de acordo com o dispositivo ligado
			vent = freezer.ventilador.isLigado() ? (float) 0.1: (float) -0.05;
			comp = freezer.compressor.isLigado() ? (float) 0.21: (float) -0.3;
			res = freezer.resistencia.isLigado() ? (float) -1: 0;

			freezer.sensor.setTemperatura(freezer.sensor.getTemperatura() - (vent + comp + res));
			System.out.println("O sensor está medindo " + freezer.sensor.getTemperatura() + " graus.");
			
			if (freezer.sensor.getTemperatura() > selecao.get(freezer.getSelecaoTemperatura()) && !freezer.ventilador.isLigado() && !freezer.compressor.isLigado() && !freezer.resistencia.isLigado()) {
				freezer.ventilador.setLigado(true); // liga ventilador
				freezer.compressor.setLigado(true); // liga compressor
				start = System.currentTimeMillis(); // marca a hora em que o compressor foi ligado
				System.out.println("O ventilador e o compressor foram acionados.");
			}
			
			if (freezer.sensor.getTemperatura() < selecao.get(freezer.getSelecaoTemperatura()) && freezer.ventilador.isLigado() && freezer.compressor.isLigado() && !freezer.resistencia.isLigado()) {
				freezer.ventilador.setLigado(false);
				freezer.compressor.setLigado(false);
				end = System.currentTimeMillis(); // marca a hora em que o compressor foi desligado
				System.out.println("O ventilador e o compressor foram desligados.");
			}
			
			// se a resistencia está ligada por 10 minutos ininterruptos:
			if (freezer.resistencia.isLigado() && System.currentTimeMillis() - startRes >= TimeUnit.MINUTES.toMillis(10) && endRes < startRes) {
				freezer.resistencia.setLigado(false);
				endRes = System.currentTimeMillis(); // marca a hora em que a resistência foi desligada
				System.out.println("A resistência de degelo foi desligada.");
			}
			
			System.out.println();

			Thread.sleep(1000);
			
			// se o compressor está ligado por 8 horas ininterruptas:
			if (freezer.compressor.isLigado() && System.currentTimeMillis() - start >= TimeUnit.HOURS.toMillis(8) && end < start) {
				freezer.ventilador.setLigado(false);
				freezer.compressor.setLigado(false);
				freezer.resistencia.setLigado(true);
				startRes = System.currentTimeMillis(); // marca a hora em que a resistência foi acionada
				end = System.currentTimeMillis(); // marca a hora em que o compressor foi desligado
				System.out.println("O ventilador e o compressor foram desligados pelo temporizador, e a resistência foi acionada.");
			}
		} while (freezer.sensor.getTemperatura() < 100); // o loop se repete enquanto a temperatura for menor que 100 graus
		
	}

}
