package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.transacoes;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.OrdemDeRecebimento;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrdemDeRecebimentoService {

    private final OrdemDeRecebimentoCalculator calculator = new OrdemDeRecebimentoCalculator();

    public List<OrdemDeRecebimento> calcula(final List<DadosTransacao> transacoes, final Map<Integer, DadosRecebimentoAdiantado> adiantamentos) {
        List<OrdemDeRecebimento> listaOrdemRecebimento = new ArrayList<>();

        for (DadosTransacao dadosTransacao : transacoes) {
            DadosRecebimentoAdiantado adiantamento = Optional.ofNullable(adiantamentos.get(dadosTransacao.id)).orElse(null);
            if (adiantamento == null) {
                listaOrdemRecebimento.add(calculator.calculaOrdemRecebimento(dadosTransacao));
            } else {
                listaOrdemRecebimento.add(calculator.calculaOrdemRecebimento(dadosTransacao, adiantamento));
            }
        }

        return listaOrdemRecebimento;
    }
}
