package model;

public class Cliente {
	private int id = 0;
	private int idade;
	private String nome;

	public Cliente(int id, String nome, int idade) {
		super();
		this.id = id;
		this.idade = idade;
		this.nome = nome;
	}

	public Cliente(String nome, int idade) {
		super();
		this.nome = nome;
		this.idade = idade;
	}

	public String toString() {
		return "Cliente [id=" + id + ", idade=" + idade + ", nome=" + nome
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
