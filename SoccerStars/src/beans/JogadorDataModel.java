package beans;

import java.util.List;

import javax.faces.model.ListDataModel;

import model.Jogador;

import org.primefaces.model.SelectableDataModel;

import security.SecurityContext;
import utils.JsfUtil;

public class JogadorDataModel extends ListDataModel<Jogador> implements SelectableDataModel<Jogador> {

	public JogadorDataModel(List<Jogador> jogadores){
		super(jogadores);
	}
	
	public List<Jogador> getJogadores(){
		Long idUsuario = SecurityContext.getIdUsuario();
		if (idUsuario == null){
			JsfUtil.addErrorMessage("Nenhum usuário logado");
			return null;
		}
		List<Jogador> jogadores = Jogador.dao().findByUsuario(idUsuario);
		return jogadores;
	}
	
	@Override
	public Jogador getRowData(String rowKey) {
		
		Long idJogador = Long.parseLong(rowKey);
		@SuppressWarnings("unchecked")
		List<Jogador> jogadores = (List<Jogador>) getWrappedData();  
        
        for(Jogador jogador : jogadores) {  
            if(idJogador.equals(jogador.getId()))  
                return jogador;  
        }
        return null;
	}
	

	@Override
	public Object getRowKey(Jogador jogador) {
		return jogador.getId();
	}
	
}
