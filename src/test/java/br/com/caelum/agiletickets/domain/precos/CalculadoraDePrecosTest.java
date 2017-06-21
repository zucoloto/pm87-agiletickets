package br.com.caelum.agiletickets.domain.precos;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecosTest {
	
	@Test
	public void deveConsiderarQuantidadeAoCalcularPrecoTotal(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.TEATRO;
		
		Sessao sessao =	SessaoTestDataBuilder
			.umaSessao()
			.deUmEspetaculoDoTipo(tipoDeEspetaculo)
			.comOPreco(10.0)
			.build();
		
		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();
		
		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 9);
		
		assertEquals(0, BigDecimal.valueOf(90.0).compareTo(precoTotal));
	}
	
	@Test
	public void deveAplicar10PorCentoAMaisNosUltimosIngressosQuandoForCinema(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.CINEMA;
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(tipoDeEspetaculo)
				.comTotalIngressos(100)
				.comIngressoReservados(95)
				.comOPreco(20.0)
				.build();
		
		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();

		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(22.0).compareTo(precoTotal));
	}
	
	@Test
	public void naoDeveAplicarAcrescimoNosPrimeirosIngressosQuandoForCinema(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.CINEMA;
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.CINEMA)
				.comTotalIngressos(100)
				.comIngressoReservados(10)
				.comOPreco(20.0)
				.build();
		
		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();

		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(20.0).compareTo(precoTotal));
	}

	@Test
	public void deveAplicar10PorCentoAMaisNosUltimosIngressosQuandoForShow(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.SHOW;
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(tipoDeEspetaculo)
				.comTotalIngressos(200)
				.comIngressoReservados(195)
				.comOPreco(100.0)
				.build();
		
		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();

		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(110.0).compareTo(precoTotal));
	}
	
	@Test
	public void naoDeveAplicarAcrescimoNosPrimeirosIngressosQuandoForShow(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.SHOW;
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(tipoDeEspetaculo)
				.comTotalIngressos(200)
				.comIngressoReservados(15)
				.comOPreco(100.0)
				.build();

		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();
		
		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(100.0).compareTo(precoTotal));
	}
	
	@Test
	public void deveAplicar20PorCentoAMaisNosUltimosIngressosQuandoForBallet(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.BALLET;
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(tipoDeEspetaculo)
				.comTotalIngressos(50)
				.comIngressoReservados(25)
				.comOPreco(500.0)
				.comDuracaoEmMinutos(50)
				.build();
		
		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();
		
		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(600.0).compareTo(precoTotal));
	}

	@Test
	public void naoDeveAplicarAcrescimoNosPrimeirosIngressosQuandoForBallet(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.BALLET;
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(tipoDeEspetaculo)
				.comTotalIngressos(50)
				.comIngressoReservados(5)
				.comOPreco(500.0)
				.comDuracaoEmMinutos(50)
				.build();
		
		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();

		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(500.0).compareTo(precoTotal));
	}

	@Test
	public void deveAplicar10AMaisSeDurarMaisDeUmaHoraQuandoForBallet(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.BALLET;
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(tipoDeEspetaculo)
				.comTotalIngressos(50)
				.comIngressoReservados(5)
				.comOPreco(500.0)
				.comDuracaoEmMinutos(100)
				.build();
		
		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();

		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(550.0).compareTo(precoTotal));
	}
	
	@Test
	public void deveAplicar20PorCentoAMaisNosUltimosIngressosQuandoForOrquestra(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.ORQUESTRA;
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(tipoDeEspetaculo)
				.comTotalIngressos(70)
				.comIngressoReservados(40)
				.comOPreco(1000.0)
				.comDuracaoEmMinutos(60)
				.build();
		
		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();
		
		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(1200.0).compareTo(precoTotal));
	}

	@Test
	public void naoDeveAplicarAcrescimoNosPrimeirosIngressosQuandoForOrquestra(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.ORQUESTRA;
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(tipoDeEspetaculo)
				.comTotalIngressos(70)
				.comIngressoReservados(10)
				.comOPreco(1000.0)
				.comDuracaoEmMinutos(60)
				.build();
		
		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();

		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(1000.0).compareTo(precoTotal));
	}

	@Test
	public void deveAplicar10AMaisSeDurarMaisDeUmaHoraQuandoForOrquestra(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.ORQUESTRA;
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(tipoDeEspetaculo)
				.comTotalIngressos(70)
				.comIngressoReservados(65)
				.comOPreco(1000.0)
				.comDuracaoEmMinutos(120)
				.build();
		
		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();

		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(1300.0).compareTo(precoTotal));
	}
	
	@Test
	public void naoDeveAplicarAcrescimoQuandoForTeatro(){
		
		TipoDeEspetaculo tipoDeEspetaculo = TipoDeEspetaculo.TEATRO;
		
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(tipoDeEspetaculo)
				.comOPreco(10.0)
				.build();
		
		CalculadorDePreco calculadoraDePrecos = tipoDeEspetaculo.obterCalculo();
		
		BigDecimal precoTotal = calculadoraDePrecos.calcula(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(10.0).compareTo(precoTotal));
	}

}
