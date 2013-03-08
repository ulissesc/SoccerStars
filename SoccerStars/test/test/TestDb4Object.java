package test;
import java.util.List;

import model.Atributos;
import model.Jogador;
import model.Partida;
import model.Time;

import org.junit.AfterClass;
import org.junit.Test;

import com.db4o.ObjectSet;

import db.DB;

public class TestDb4Object {

 	@AfterClass
	public static void close() {
		DB.getDb().close();
	}

	
	
	@Test
	public void testConection() {

		for (int i = 0; i < 20; i++){
		
			Jogador jogador = new Jogador(null, "JOGADOR 10", "D",
					Atributos.Habilidade.NIVEL_2.valor,
					Atributos.Condicionamento.NIVEL_1.valor);
	
			Jogador.dao().save(jogador);
	
			System.out.println("Jogador armazenado: " + jogador);
		}
		ObjectSet<Jogador> result = Jogador.dao().findAll();
		for (Jogador jogador2 : result) {
			System.out.println("JOG" + jogador2);
		}

	}

	@Test
	public void testDelete() {

		ObjectSet<Jogador> result = Jogador.dao().findAll();
		for (Jogador jogador2 : result) {
			Jogador.dao().delete(jogador2.getId());
			break;
		}

	}
	
	@Test
	public void testUpdate() {

		ObjectSet<Jogador> result = Jogador.dao().findAll();
		for (Jogador jogador2 : result) {
			jogador2.setNome(jogador2.getNome() + " ALTERADO");
			Jogador.dao().save(jogador2);
			break;
		}

	}
	
	@Test
	public void findPartidaPorUsuario() {
		List<Partida> findByUsuario = Partida.dao().findByUsuario(1L);

		for (Partida part : findByUsuario) {
			System.out.println("Partida: " + part);
		}
	}
	
	
	@Test
	public void testSavePartida() {

		Jogador jogador1 = new Jogador(null, "JOGADOR 1", "D",
				Atributos.Habilidade.NIVEL_2.valor,
				Atributos.Condicionamento.NIVEL_1.valor);
		Jogador jogador2 = new Jogador(null, "JOGADOR 2", "A",
				Atributos.Habilidade.NIVEL_2.valor,
				Atributos.Condicionamento.NIVEL_4.valor);
		Jogador jogador3 = new Jogador(null, "JOGADOR 3", "A",
				Atributos.Habilidade.NIVEL_2.valor,
				Atributos.Condicionamento.NIVEL_3.valor);
		Jogador jogador4 = new Jogador(null, "JOGADOR 4", "D",
				Atributos.Habilidade.NIVEL_2.valor,
				Atributos.Condicionamento.NIVEL_3.valor);

		Time timeA = new Time();
		timeA.addJogador(jogador1);
		timeA.addJogador(jogador2);
		
		Time timeB = new Time();
		timeB.addJogador(jogador3);
		timeB.addJogador(jogador4);
		
		Partida partida = new Partida();
		partida.setTimeA(timeA);
		partida.setTimeB(timeB);
		partida.setIdUsuario(1L);

		Partida.dao().save(partida);
		
		ObjectSet<Partida> allPartidas = Partida.dao().findAll();
		
		for (Partida part : allPartidas) {
			System.out.println("Partida: " + part);
		}
		
		
	
	}
	

}
