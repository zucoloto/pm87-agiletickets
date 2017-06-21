package br.com.caelum.agiletickets.models;

import br.com.caelum.agiletickets.domain.precos.CalculadorDePreco;
import br.com.caelum.agiletickets.domain.precos.strategy.CalculadorDePrecoBallet;
import br.com.caelum.agiletickets.domain.precos.strategy.CalculadorDePrecoCinema;
import br.com.caelum.agiletickets.domain.precos.strategy.CalculadorDePrecoOrquestra;
import br.com.caelum.agiletickets.domain.precos.strategy.CalculadorDePrecoShow;
import br.com.caelum.agiletickets.domain.precos.strategy.CalculadorDePrecoTeatro;

public enum TipoDeEspetaculo {

	CINEMA {
		@Override
		public CalculadorDePreco obterCalculo() {
			return new CalculadorDePrecoCinema();
		}
	},
	SHOW {
		@Override
		public CalculadorDePreco obterCalculo() {
			return new CalculadorDePrecoShow();
		}
	},
	TEATRO {
		@Override
		public CalculadorDePreco obterCalculo() {
			return new CalculadorDePrecoTeatro();
		}
	},
	BALLET {
		@Override
		public CalculadorDePreco obterCalculo() {
			return new CalculadorDePrecoBallet();
		}
	},
	ORQUESTRA {
		@Override
		public CalculadorDePreco obterCalculo() {
			return new CalculadorDePrecoOrquestra();
		}
	};

	public abstract CalculadorDePreco obterCalculo();

}
