package test;
import java.util.ArrayList;
import java.util.List;

import model.Atributos;
import model.Jogador;
import model.Partida;

import org.junit.Test;

import utils.CriadorDeTimes;


public class TestSepararJogadores {

	List<Jogador> jogadores = new ArrayList<Jogador>();
	public TestSepararJogadores(){
		
		jogadores.add(new Jogador(1L, "ALESSON", "D", 
				Atributos.Habilidade.NIVEL_4.valor, 
				Atributos.Condicionamento.NIVEL_1.valor));
		
		jogadores.add(new Jogador(2L, "CASSIO", "A", 
				Atributos.Habilidade.NIVEL_5.valor, 
				Atributos.Condicionamento.NIVEL_3.valor));
		
		jogadores.add(new Jogador(3L, "KLEBER", "M", 
				Atributos.Habilidade.NIVEL_2.valor, 
				Atributos.Condicionamento.NIVEL_2.valor));
		
		jogadores.add(new Jogador(4L, "CRISTIAN", "A", 
				Atributos.Habilidade.NIVEL_2.valor, 
				Atributos.Condicionamento.NIVEL_2.valor));
		
		jogadores.add(new Jogador(5L, "EDER", "M", 
				Atributos.Habilidade.NIVEL_1.valor, 
				Atributos.Condicionamento.NIVEL_3.valor));
		
		jogadores.add(new Jogador(6L, "FABIO", "D", 
				Atributos.Habilidade.NIVEL_1.valor, 
				Atributos.Condicionamento.NIVEL_4.valor));
		
		jogadores.add(new Jogador(7L, "FELIPE", "M", 
				Atributos.Habilidade.NIVEL_3.valor, 
				Atributos.Condicionamento.NIVEL_3.valor));
		
		jogadores.add(new Jogador(8L, "HASSEL", "A", 
				Atributos.Habilidade.NIVEL_3.valor, 
				Atributos.Condicionamento.NIVEL_3.valor));
		
		
		jogadores.add(new Jogador(9L, "JEFE", "M", 
				Atributos.Habilidade.NIVEL_3.valor, 
				Atributos.Condicionamento.NIVEL_5.valor));
		
		jogadores.add(new Jogador(10L, "LUCAO", "A", 
				Atributos.Habilidade.NIVEL_2.valor, 
				Atributos.Condicionamento.NIVEL_1.valor));
		
		
		jogadores.add(new Jogador(11L, "MARLON", "D", 
				Atributos.Habilidade.NIVEL_4.valor, 
				Atributos.Condicionamento.NIVEL_4.valor));
		

		jogadores.add(new Jogador(12L, "PARPI", "M", 
				Atributos.Habilidade.NIVEL_3.valor, 
				Atributos.Condicionamento.NIVEL_2.valor));
		
		jogadores.add(new Jogador(13L, "ULISSES", "M", 
				Atributos.Habilidade.NIVEL_4.valor, 
				Atributos.Condicionamento.NIVEL_3.valor));
		
		jogadores.add(new Jogador(14L, "VAGNER", "M", 
				Atributos.Habilidade.NIVEL_4.valor, 
				Atributos.Condicionamento.NIVEL_4.valor));
	}
	
	@Test
	public void separar(){
		
		CriadorDeTimes criadorDeTimes = new CriadorDeTimes(jogadores);
		
		Partida partida = criadorDeTimes.gerarPartida();
		
		System.out.println(" === TIME A: " + partida.getTimeA().getJogadores().size() +" JOGADORES - MEDIA: " + partida.getTimeA().getMediaGeral());
		System.out.println("     SOMA DAS MEDIAS: " + partida.getTimeA().getSomaMedias());
		for(Jogador jogador : partida.getTimeA().getJogadores()){
			System.out.println(jogador);
		}
		
		System.out.println();
		System.out.println("\r\n === TIME B: " + partida.getTimeB().getJogadores().size() +" JOGADORES - MEDIA: " + partida.getTimeB().getMediaGeral());
		System.out.println("     SOMA DAS MEDIAS: " + partida.getTimeB().getSomaMedias());
		for(Jogador jogador : partida.getTimeB().getJogadores()){
			System.out.println(jogador);
		}
	}
	
}
