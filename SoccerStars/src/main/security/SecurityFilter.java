package main.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.Usuario;
import main.db.DB;


@WebFilter(value="*" )
public class SecurityFilter implements Filter {

	private boolean ENABLE = true;

	public void init(FilterConfig arg0) throws ServletException {}

	public void destroy() {
		DB.getDb().close();
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		final String uri = httpServletRequest.getRequestURI();
		final Long idUsuarioLogado = (Long) httpServletRequest.getSession().getAttribute(SecurityContext.ID_USUARIO_LOGADO);
		final Usuario usuario = idUsuarioLogado != null ? Usuario.dao().load(idUsuarioLogado) : null;
		
		if (ENABLE) {
			
			if (isSessionInvalid(httpServletRequest)){
				permNegada(httpServletRequest, httpServletResponse);
				return;
			}
				
			
			if (isPrivatedUrl(uri) && idUsuarioLogado == null){
				permNegada(httpServletRequest, httpServletResponse);
				return;
			}
			if (isAdminUrl(uri) && !SecurityContext.isUsuarioAdmin( usuario )){
				permNegada(httpServletRequest, httpServletResponse);
				return;
			}
			
		}

		chain.doFilter(request, response);
		try{
			DB.getDb().commit();
		}catch(Throwable e){
			DB.getDb().rollback();
		}
	}

	private void permNegada(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		final String timeoutUrl = httpServletRequest.getContextPath() + "/";
		httpServletResponse.sendRedirect(timeoutUrl);
		return;
	}

	private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {
		boolean sessionInValid = (httpServletRequest.getRequestedSessionId() != null)
				&& !httpServletRequest.isRequestedSessionIdValid();
		return sessionInValid;
	}

	
	private boolean isAdminUrl(String url){
		String privateUrls[] = new String[]{
			"admin.jsf",
			"admin"
		};
		
		for(String urlPrivate  : privateUrls){
			if (url.contains(urlPrivate))
				return true;
		}
		
		return false;
	}
	
	private boolean isPrivatedUrl(String url){
		String privateUrls[] = new String[]{
			"criarPartida.jsf",
			"criarPartida",
			"template.jsf",
			"finalizarCriacao.jsf",
			"finalizarCriacao"
		};
		
		for(String urlPrivate  : privateUrls){
			if (url.contains(urlPrivate))
				return true;
		}
		
		return false;
	}
}
