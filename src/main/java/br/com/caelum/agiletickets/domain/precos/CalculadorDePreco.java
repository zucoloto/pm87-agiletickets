package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public interface CalculadorDePreco {

	public BigDecimal calcula(Sessao sessao, Integer quantidade);

}