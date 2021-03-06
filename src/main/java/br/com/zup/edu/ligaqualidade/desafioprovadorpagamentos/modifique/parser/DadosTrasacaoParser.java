package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.parser;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DadosTrasacaoParser {

    public List<DadosTransacao> parse(final List<String> infoTransacoes) {

        List<DadosTransacao> listaDadosTransacao = new ArrayList<>();

        for (String infoTransacao : infoTransacoes) {
            final String[] transacao = infoTransacao.split(",");

            BigDecimal valor = new BigDecimal(transacao[0]);
            MetodoPagamento metodo = MetodoPagamento.valueOf(transacao[1]);
            String numero = transacao[2];
            String nome = transacao[3];
            final String[] date = transacao[4].split("/");
            LocalDate validade = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
            int cvv = Integer.parseInt(transacao[5]);
            int id = Integer.parseInt(transacao[6]);

            listaDadosTransacao.add(
                    new DadosTransacao(valor,
                            metodo,
                            numero,
                            nome,
                            validade,
                            cvv,
                            id
                    )
            );
        }


        return listaDadosTransacao;

    }
}
