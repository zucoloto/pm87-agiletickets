package br.com.caelum.agiletickets.domain.precos.strategy;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.domain.precos.CalculadorDePreco;
import br.com.caelum.agiletickets.models.Sessao;

public class CalculadorDePrecoCinema implements CalculadorDePreco {

	public BigDecimal calcula(Sessao sessao, Integer quantidade) {
		
		BigDecimal preco;

		// quando estiver acabando os ingressos...
		if ((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.05) {
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		} else {
			preco = sessao.getPreco();
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
}
