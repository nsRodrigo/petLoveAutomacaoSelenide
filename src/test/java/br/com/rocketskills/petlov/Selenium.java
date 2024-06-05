package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class PontoDoacao {
	String nome;
	String email;
	String CEP;
	Integer numero;
	String complemento;
	String pets;

	public PontoDoacao(String nome, String email, String CEP, Integer numero, String complemento, String pets) {
		this.nome = nome;
		this.email = email;
		this.CEP = CEP;
		this.numero = numero;
		this.complemento = complemento;
		this.pets = pets;
	}
}

class Selenium {

	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void createPoint() {

		PontoDoacao massa = new PontoDoacao(
				"Estaçao Pets",
				"estacao@pets.com",
				"06386000",
				804,
				"loja 1",
				"Cachorros");

		open("https://petlov.vercel.app/signup");
		$("h1").shouldHave(text("Cadastro de ponto de doação"));
		
		$("input[name='name']").setValue(massa.nome);
		$("input[name='email']").setValue(massa.email);
		$("input[name='cep']").setValue(massa.CEP);
		$("input[value='Buscar CEP']").click();
		$("input[name='addressNumber']").setValue(massa.numero.toString());
		$("input[name='addressDetails']").setValue(massa.complemento);
		$x("//span[text()='" + massa.pets + "']/..").click();
		$(".button-register").click();

		String texto = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		$("#success-page p").shouldHave(text(texto));
	}
}
