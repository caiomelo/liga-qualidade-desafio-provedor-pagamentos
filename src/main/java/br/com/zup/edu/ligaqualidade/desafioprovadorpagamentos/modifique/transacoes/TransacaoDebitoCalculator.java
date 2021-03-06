package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.transacoes;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.StatusRecebimento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoDebitoCalculator implements TransacaoCalculator {

    private static final BigDecimal VALOR_TAXA = new BigDecimal("0.03");

    @Override
    public StatusRecebimento statusRecebimento() {
        return StatusRecebimento.PAGO;
    }

    @Override
    public LocalDate calculaDataRecebimento(LocalDate dataTransacao) {
        return dataTransacao;
    }

    @Override
    public BigDecimal descontaTaxaTransacao(BigDecimal valorCompra) {
        return valorCompra.subtract(valorCompra.multiply(VALOR_TAXA));
    }


}
