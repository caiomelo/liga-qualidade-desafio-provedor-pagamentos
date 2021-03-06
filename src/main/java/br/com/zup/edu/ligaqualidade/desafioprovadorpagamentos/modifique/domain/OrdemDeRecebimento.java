package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdemDeRecebimento {
    private final StatusRecebimento status;
    private final BigDecimal valorOriginal;
    private final BigDecimal valorFinal;
    private final LocalDate dataRecebimento;

    public OrdemDeRecebimento(StatusRecebimento status, BigDecimal valorOriginal, BigDecimal valorASerRecebidoDeFato, LocalDate dataEsperadoRecebimento) {
        this.status = status;
        this.valorOriginal = valorOriginal;
        this.valorFinal = valorASerRecebidoDeFato;
        this.dataRecebimento = dataEsperadoRecebimento;
    }

    public StatusRecebimento getStatus() {
        return status;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public LocalDate getDataRecebimento() {
        return dataRecebimento;
    }
}
