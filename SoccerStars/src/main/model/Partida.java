package main.model;

import java.util.Date;

import main.db.Entity;
import main.db.dao.PartidaDao;

public class Partida implements Entity{
	
	private Long id;	
	private String local;
	private Date dataHora;
	private Time timeA;
	private Time timeB;
	private Long idUsuario;
	private Integer resultadoTimeA;
	private Integer resultadoTimeB;
	
	public Time getTimeA() {
		return timeA;
	}
	public void setTimeA(Time timeA) {
		this.timeA = timeA;
	}
	public Time getTimeB() {
		return timeB;
	}
	public void setTimeB(Time timeB) {
		this.timeB = timeB;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public static PartidaDao dao(){
		return new PartidaDao();
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public Integer getResultadoTimeA() {
		return resultadoTimeA;
	}
	public void setResultadoTimeA(Integer resultadoTimeA) {
		this.resultadoTimeA = resultadoTimeA;
	}
	public Integer getResultadoTimeB() {
		return resultadoTimeB;
	}
	public void setResultadoTimeB(Integer resultadoTimeB) {
		this.resultadoTimeB = resultadoTimeB;
	}

}
