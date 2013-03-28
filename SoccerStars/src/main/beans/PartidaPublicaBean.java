package main.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import main.model.Partida;

@ManagedBean
@ViewScoped
public class PartidaPublicaBean {

	private Partida partida;
	private Long idPartida;
	
	public Partida getPartida() {
		return partida;
	}
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	public Long getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(Long idPartida) {
		this.idPartida = idPartida;
		partida = Partida.dao().load(idPartida);
	}
	
	public void salvarPartida(){
		Partida.dao().save(partida);
	}
	 
}
