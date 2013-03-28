package main.model;

import java.util.Comparator;

import com.sun.xml.internal.ws.util.StringUtils;

import main.db.Entity;
import main.db.dao.JogadorDao;

public class Jogador implements Comparator<Jogador>, Entity{
	
	private Long id;
	private String nome;
	private String posicao;
	private Integer habilidade;
	private Integer condicionamento;
	private Long idUsuario;

	
	public Jogador(Long id, String nome, String posicao, Integer habilidade,
			Integer condicionamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.posicao = posicao;
		this.habilidade = habilidade;
		this.condicionamento = condicionamento;
	}
	public Jogador(){}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public String getNomeCapitalizado() {
		return StringUtils.capitalize( nome );
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPosicao() {
		return posicao;
	}
	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}
	public Integer getHabilidade() {
		return habilidade;
	}
	public void setHabilidade(Integer habilidade) {
		this.habilidade = habilidade;
	}
	public Integer getCondicionamento() {
		return condicionamento;
	}
	public void setCondicionamento(Integer condicionamento) {
		this.condicionamento = condicionamento;
	}
	public Double getMedia(){
		return (double) (condicionamento + habilidade) / 2;
	}
	
	public Integer getMediaAproximada(){
		return (int) Math.round( getMedia() );
	}
	
	@Override
	public int compare(Jogador o1, Jogador o2) {
		return o1.getNome().compareTo(o2.getNome());
	}
	
	@Override
	public String toString() {
		return "Id: " + getId() + " Posicao: " + getPosicao() + " Media: " + getMedia() + " - " + getNome();
	}
	
	public static JogadorDao dao(){
		return new JogadorDao();
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
