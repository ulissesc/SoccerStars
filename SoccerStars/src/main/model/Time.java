package main.model;

import java.util.ArrayList;
import java.util.List;

public class Time {
	
	private String descricao;
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	
	public void addJogador(Jogador jogador){
		getJogadores().add(jogador);
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}
	
	public Double getMediaGeral(){
		Double total = getSomaMedias();
		
		if (jogadores.size() == 0)
			return 0D;
		
		return total / (double) jogadores.size();
	}
	
	

	public Double getSomaMedias(){
		Double total = 0D;
		for(Jogador jogador : jogadores){
			total += jogador.getMedia();
		}
		
		return total ;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
