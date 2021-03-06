package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.parser;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.OrdemDeRecebimento;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrdemDeRecebimentoMessageParser {

    public List<String[]> parse(List<OrdemDeRecebimento> ordensDeRecebimento) {
        List<String[]> parsed = new ArrayList<>();

        for (OrdemDeRecebimento ordemDeRecebimento : ordensDeRecebimento) {
            String[] pasredOrdemDeRecebimento = new String[]{
                    ordemDeRecebimento.getStatus().toString().toLowerCase(),
                    ordemDeRecebimento.getValorOriginal().toString(),
                    ordemDeRecebimento.getValorFinal().toString(),
                    ordemDeRecebimento.getDataRecebimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))};
            parsed.add(pasredOrdemDeRecebimento);
        }

        return parsed;
    }

}
