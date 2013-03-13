package beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Jogador;
import model.Partida;
import security.SecurityContext;
import utils.CriadorDeTimes;
import utils.JsfUtil;

@ManagedBean
@ViewScoped
public class JogadoresBean {
 
	private Long idJogador;
	private Jogador jogador;
	private Jogador[] selectedJogador;
	private List<Jogador> jogadoresFiltrados;
	
	public JogadoresBean(){
		this.setJogador(new Jogador());
	}
	
	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public Long getIdJogador() {
		return idJogador;
	}

	public void setIdJogador(Long idJogador) {
		this.idJogador = idJogador;
	}
	
	public JogadorDataModel getJogadoresDataModel(){
		Long idUsuario = SecurityContext.getIdUsuario();
		if (idUsuario == null){
			JsfUtil.addErrorMessage("Nenhum usuário logado");
			return null;
		}
		List<Jogador> jogadores = Jogador.dao().findByUsuario(idUsuario);
		
		return new JogadorDataModel(jogadores);
	}
	
	public String salvarJogador(){
		jogador.setIdUsuario(SecurityContext.getIdUsuario());
		Jogador.dao().save(jogador);
		JsfUtil.addSuccessMessage("Jogador " +jogador.getNome() + " adicionado");
		jogador = new Jogador();
		return null;
	}
	
	public String carregarJogador(){
		return carregarJogador(null);
	}
	
	public String carregarJogador(Long idJogador){
		if (idJogador == null)
			jogador = new Jogador();
		else
			jogador = Jogador.dao().load(idJogador);
		return null;
	}
	
	public String excluirJogador(Long idJogador){
		Jogador.dao().delete(idJogador);
		JsfUtil.addSuccessMessage("Jogador excluído.");
		return null;
	}

	public Jogador[] getSelectedJogador() {
		return selectedJogador;
	}

	public void setSelectedJogador(Jogador[] selectedJogador) {
		this.selectedJogador = selectedJogador;
	}
	
	public String separarTimes(){
		
		List<Jogador> jogadoresSelecionados = Arrays.asList(getSelectedJogador());
		
		CriadorDeTimes criadorDeTimes = new CriadorDeTimes(jogadoresSelecionados);
		
		Partida partidaGerada = criadorDeTimes.gerarPartida();
		
		JsfUtil.getSessionMap().put("ultimaPartidaGerada", partidaGerada);
		
		return "finalizarCriacao.jsf?faces-redirect=true";
	}

	public List<Jogador> getJogadoresFiltrados() {
		return jogadoresFiltrados;
	}

	public void setJogadoresFiltrados(List<Jogador> jogadoresFiltrados) {
		this.jogadoresFiltrados = jogadoresFiltrados;
	}
	
	public List<Partida> getPartidasGeradas(){ 
		Long idUsuario = SecurityContext.getIdUsuario();
		if (idUsuario == null){
			JsfUtil.addErrorMessage("Nenhum usuário logado");
			return null;
		}
		return Partida.dao().findByUsuario(idUsuario);
	}
	
	public Integer getTotalJogadoresSelecionados(){
		if (getSelectedJogador() == null)
			return 0;
		return getSelectedJogador().length;
	}

}
