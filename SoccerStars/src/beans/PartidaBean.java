package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.DragDropEvent;

import security.SecurityContext;
import utils.CriadorDeTimes;
import utils.JsfUtil;

import model.Jogador;
import model.Partida;

@ManagedBean(name="partidaBean")
@ViewScoped
public class PartidaBean {

	private Partida partida;
	private String local;
	private Date dataHora;
	
	public Partida getPartida() {
		if (partida == null)
			partida = (Partida) JsfUtil.getSessionMap().get("ultimaPartidaGerada");
		return partida;
	}

	public void onTimeBDrop(DragDropEvent ddEvent) {  
        Jogador jogador = ((Jogador) ddEvent.getData());  
        getPartida().getTimeA().getJogadores().remove(jogador);
        getPartida().getTimeB().getJogadores().remove(jogador);
    }
	
	public String finalizar(){
		
		getPartida().setLocal(local);
		getPartida().setDataHora(dataHora);
		getPartida().setIdUsuario(SecurityContext.getIdUsuario());
		Partida.dao().save(getPartida());
		
		return "/partidaGerada.jsf?id="+getPartida().getId()+"&faces-redirect=true";
	}

	public String regerarPartida(){
		
		List<Jogador> jogadores = new ArrayList<Jogador>();
		jogadores.addAll( getPartida().getTimeA().getJogadores() );
		jogadores.addAll( getPartida().getTimeB().getJogadores() );
		
		CriadorDeTimes criadorDeTimes = new CriadorDeTimes(jogadores);
		criadorDeTimes.setPartida(partida);
		
		/* partida = */criadorDeTimes.gerarPartida();
		JsfUtil.getSessionMap().put("ultimaPartidaGerada", partida);
		
		return null;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	public void mudurDeTime(String time, Jogador jogador){
		if ("A".equals( time )){
			getPartida().getTimeA().getJogadores().remove(jogador);
			getPartida().getTimeB().getJogadores().add(jogador);
		}else{
			getPartida().getTimeB().getJogadores().remove(jogador);
			getPartida().getTimeA().getJogadores().add(jogador);
		}
		
	}
}
