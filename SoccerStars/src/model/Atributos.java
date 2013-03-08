package model;

public class Atributos {
	
	public enum Habilidade{
		NIVEL_1(1),
		NIVEL_2(2),
		NIVEL_3(3),
		NIVEL_4(4),
		NIVEL_5(5);
		
		private Habilidade(int valor){
			this.valor = valor;
		}
		public final int valor;
	}
	
	public enum Condicionamento{
		NIVEL_1(1),
		NIVEL_2(2),
		NIVEL_3(3),
		NIVEL_4(4),
		NIVEL_5(5);
		
		private Condicionamento(int valor){
			this.valor = valor;
		}
		public final int valor;
	}
	
	public enum Posicao{
		DEFESA("D"),
		MEIO("M"),
		ATAQUE("A");
		
		private Posicao(String valor){
			this.valor = valor;
		}
		final String valor;
	}
	
}
