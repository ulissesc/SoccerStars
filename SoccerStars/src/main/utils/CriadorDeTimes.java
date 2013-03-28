package main.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import main.model.Jogador;
import main.model.Partida;
import main.model.Time;

public class CriadorDeTimes {
	
	private List<Jogador> jogadores;
	private Partida partida;
	
	private HashMap<String, List<Jogador>> gruposPorPosicao = new HashMap<String, List<Jogador>>();

	public CriadorDeTimes(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	
	
	public Partida gerarPartida(){
		
		separarEmGruposPorPosicao();
		embaralharGrupos();

		return separarEmTimes();
	}
	
	private void separarEmGruposPorPosicao(){
		for(Jogador jogador : jogadores){
			List<Jogador> list = gruposPorPosicao.get(jogador.getPosicao());
			if (list == null)
				list = new ArrayList<Jogador>();
			
			list.add(jogador);
			gruposPorPosicao.put(jogador.getPosicao(), list);
		}
	}
	
	private void embaralharGrupos(){
		for(String grupo : gruposPorPosicao.keySet()){
			Collections.shuffle(gruposPorPosicao.get(grupo));
		}
	}
	
	private Partida separarEmTimes(){
		
		Time timeA = new Time();
		timeA.setDescricao("time A");
		
		Time timeB = new Time();
		timeB.setDescricao("time B");
		
		boolean comecaTimeA = new Random().nextBoolean();
		String timeAtual = comecaTimeA ? "A" : "B";
		
		boolean primeiroGrupo = true;
		
		for(String grupo : gruposPorPosicao.keySet()){
			List<Jogador> jogadoresDoGrupo = gruposPorPosicao.get(grupo);
			
			if (!primeiroGrupo){
				if (timeA.getJogadores().size() == timeB.getJogadores().size()){
					if (timeA.getMediaGeral() > timeB.getMediaGeral() )
						timeAtual = "B";
					else
						timeAtual = "A";
				}
			}
			
			int numJogadoresGrupo = jogadoresDoGrupo.size();
			for(int i = 0; i < numJogadoresGrupo; i++){
				
				if (timeAtual.equals("A")){
					final Jogador melhorJogador = getMelhorJogador(jogadoresDoGrupo);
					timeA.addJogador(melhorJogador);
					jogadoresDoGrupo.remove(melhorJogador);
					timeAtual = "B";
				}else{
					final Jogador melhorJogador = getMelhorJogador(jogadoresDoGrupo);
					timeB.addJogador(melhorJogador);
					jogadoresDoGrupo.remove(melhorJogador);
					timeAtual = "A";
				}				
			}
			
			primeiroGrupo = false;
		}
		
		
		if (partida == null)
			partida = new Partida();
		
		
		partida.setTimeA(timeA);
		partida.setTimeB(timeB);
		
		return partida;
		
	}
	
	private Jogador getMelhorJogador(List<Jogador> jogadores){
		
		final List<Jogador> jogadesAux = new ArrayList<Jogador>();
		jogadesAux.addAll(jogadores);
		
		Collections.sort(jogadesAux, new Comparator<Jogador>() {
			@Override
			public int compare(Jogador o1, Jogador o2) {
				Long media2 = Math.round( o2.getMedia());
				Long media1 = Math.round( o2.getMedia());
				return media2.compareTo(media1) ;
			}
		} );
		
		if (jogadesAux.size() > 0)
			return jogadesAux.get(0);
		
		return null;
	}


	public Partida getPartida() {
		return partida;
	}


	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	
	
}
