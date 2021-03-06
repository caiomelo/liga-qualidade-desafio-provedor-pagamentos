package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.transacoes;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.OrdemDeRecebimento;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.StatusRecebimento;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class OrdemDeRecebimentoCalculator {

    private final Map<MetodoPagamento, TransacaoCalculator> calculators;

    public OrdemDeRecebimentoCalculator() {
        this.calculators = Map.of(
                MetodoPagamento.DEBITO, new TransacaoDebitoCalculator(),
                MetodoPagamento.CREDITO, new TransacaoCreditoCalculator());
    }

    public OrdemDeRecebimento calculaOrdemRecebimento(final DadosTransacao transacao, final DadosRecebimentoAdiantado adiantamento) {
        TransacaoCalculator calculator = calculators.get(transacao.metodo);

        BigDecimal valorTransacao = calculator.descontaTaxaTransacao(transacao.valor);
        valorTransacao = valorTransacao.subtract(valorTransacao.multiply(adiantamento.taxa));

        return new OrdemDeRecebimento(
                StatusRecebimento.PAGO,
                transacao.valor,
                valorTransacao,
                LocalDate.now()
        );
    }

    public OrdemDeRecebimento calculaOrdemRecebimento(final DadosTransacao transacao) {
        TransacaoCalculator calculator = calculators.get(transacao.metodo);
        return new OrdemDeRecebimento(
                calculator.statusRecebimento(),
                transacao.valor,
                calculator.descontaTaxaTransacao(transacao.valor),
                calculator.calculaDataRecebimento(LocalDate.now())
        );
    }
}
