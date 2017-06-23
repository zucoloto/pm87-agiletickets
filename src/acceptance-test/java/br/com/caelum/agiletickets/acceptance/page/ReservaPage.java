package br.com.caelum.agiletickets.acceptance.page;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReservaPage {
	private static final String BASE_URL = "http://localhost:8080";
	private final WebDriver driver;

	public ReservaPage(WebDriver browser) {
		this.driver = browser;
	}

	public void abreListagem() {
		driver.get(BASE_URL);
	}

	public void abriReserva() {
		WebElement url = ultimaLinha().findElement(By.tagName("a"));
		url.click();
	}

	public void adicioneQuantidade(String quantidade) {
		WebElement form = form();
		form.findElement(By.id("qtde")).sendKeys(quantidade);

		System.out.println(driver.findElement(By.className("test_ingresso_disponivel")).getText());

		form.submit();
	}

	private WebElement form() {
		return driver.findElement(By.tagName("form"));
	}

	public void ultimaLinhaDeveConter(String nome) {
		WebElement ultimaLinha = ultimaLinha();
		assertThat(ultimaLinha.findElements(By.tagName("a")).get(0).getText(),
				is(nome));
	}

	private WebElement ultimaLinha() {
		List<WebElement> linhas = driver.findElements(By.tagName("li"));
		WebElement ultimaLinha = linhas.get(linhas.size() - 1);
		return ultimaLinha;
	}

}
