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

    OrdemDeRecebimento calculaOrdemRecebimento(final DadosTransacao transacao, final DadosRecebimentoAdiantado adiantamento) {
        TransacaoCalculator calculator = calculators.get(transacao.metodo);
        BigDecimal valorTransacao = calculator.descontaTaxaTransacao(transacao.valor);
        LocalDate dataRecebimento = calculator.calculaDataRecebimento(LocalDate.now());
        StatusRecebimento status = calculator.statusRecebimento();

        if (adiantamento != null) {
            valorTransacao = valorTransacao.subtract(valorTransacao.multiply(adiantamento.taxa));
            dataRecebimento = LocalDate.now();
            status = StatusRecebimento.PAGO;
        }

        return new OrdemDeRecebimento(
                status,
                transacao.valor,
                valorTransacao,
                dataRecebimento
        );
    }
}
