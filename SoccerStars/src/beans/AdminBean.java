package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import utils.JsfUtil;
import db.DB;

@ManagedBean
@ViewScoped
public class AdminBean {

	public List<Usuario> getTodosOsUsuarios(){
		return Usuario.dao().findAll();
	}
	
	public String excluirUsuario(Usuario usuario){
		
		Usuario.dao().delete(usuario);
		JsfUtil.addSuccessMessage("Usuário excluído com sucesso");
		
		return null;
	}
	
	public void baixarBanco(){
		File file = new File(DB.DB_FILE);
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); // Context

		if (!file.exists())
			throw new IllegalArgumentException("Não foi possível encontrar o arquivo '"
					+ file.getName() 
					+ "' para realizar o download.");
		
		HttpServletResponse response = (HttpServletResponse) context.getResponse();
		response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\""); 
		
		response.setContentLength((int) file.length()); // O tamanho do arquivo
		
		try {
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();

			byte[] buf = new byte[(int) file.length()];
			int count;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
			in.close();
			out.flush();
			out.close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	
}
