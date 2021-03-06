package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.parser;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DadosAdiantamentoParser {

    public List<DadosRecebimentoAdiantado> parse(final List<String> infoAdiantamentos) {

        List<DadosRecebimentoAdiantado> listaDadosRecebimentoAdiantado = new ArrayList<>();
        for (String infoAdiantamento : infoAdiantamentos) {
            final String[] adiantamento = infoAdiantamento.split(",");

            int idTransacao = Integer.parseInt(adiantamento[0]);
            BigDecimal taxa = new BigDecimal(adiantamento[1]);

            listaDadosRecebimentoAdiantado.add(
                    new DadosRecebimentoAdiantado(idTransacao, taxa)
            );
        }

        return listaDadosRecebimentoAdiantado;
    }

    public Map<Integer, DadosRecebimentoAdiantado> parseAndMapByIdTransacao(final List<String> infoAdiantamentos) {
        Map<Integer, DadosRecebimentoAdiantado> adiantamentoByIdTransacao = new HashMap<>();
        for (String infoAdiantamento : infoAdiantamentos) {
            final String[] adiantamento = infoAdiantamento.split(",");
            int idTransacao = Integer.parseInt(adiantamento[0]);
            BigDecimal taxa = new BigDecimal(adiantamento[1]);
            adiantamentoByIdTransacao.put(idTransacao, new DadosRecebimentoAdiantado(idTransacao, taxa));
        }

        return adiantamentoByIdTransacao;
    }
}
