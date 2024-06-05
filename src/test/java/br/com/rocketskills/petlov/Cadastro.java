package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

class Cadastro {

	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void createPoint() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://petlov.vercel.app/signup");

		WebElement title = driver.findElement(By.cssSelector("h1"));

		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(d -> title.isDisplayed());

		assertEquals("Cadastro de ponto de doação", title.getText(), "Verificando mensagem com sucesso");

		driver.findElement(By.cssSelector("input[name='name']")).sendKeys("Rodrigo Point");
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("rodrigo@teste.com");
		driver.findElement(By.cssSelector("input[name='cep']")).sendKeys("06386000");

		WebElement buttonCEP = driver.findElement(By.cssSelector("input[value='Buscar CEP']"));
		buttonCEP.click();
		wait.until(d -> buttonCEP.isDisplayed());
		
		driver.findElement(By.cssSelector("input[name='addressNumber']")).sendKeys("804");
		driver.findElement(By.cssSelector("input[name='addressDetails']")).sendKeys("Casa 2");
		driver.findElement(By.xpath("//span[text()='Cachorros']/..")).click();
		driver.findElement(By.className("button-register")).click();

		WebElement result = driver.findElement(By.cssSelector("#success-page p"));

		Wait<WebDriver> waitResult = new WebDriverWait(driver, Duration.ofSeconds(2));
		waitResult.until(d -> result.isDisplayed());

		String texto = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";

		assertEquals(texto, result.getText(), "Verificando mensagem com sucesso");

		driver.close();
	}
}
