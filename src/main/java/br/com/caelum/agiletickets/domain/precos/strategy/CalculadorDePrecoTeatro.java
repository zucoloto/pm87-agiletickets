package br.com.caelum.agiletickets.domain.precos.strategy;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.domain.precos.CalculadorDePreco;
import br.com.caelum.agiletickets.models.Sessao;

public class CalculadorDePrecoTeatro implements CalculadorDePreco {

	@Override
	public BigDecimal calcula(Sessao sessao, Integer quantidade) {

		BigDecimal preco = sessao.getPreco();

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}
