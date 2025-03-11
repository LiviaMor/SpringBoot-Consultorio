package com.scarlet.consultorio.service;
import com.scarlet.consultorio.models.CaixaModel;
import com.scarlet.consultorio.models.ConsultaModel;
import com.scarlet.consultorio.repositories.CaixaRepository;
import com.scarlet.consultorio.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private CaixaRepository caixaRepository;

    @Transactional
    public ConsultaModel cadastrarConsulta(ConsultaModel consulta) {
        // Supondo que o caixa já existe e foi atribuído à consulta.
        CaixaModel caixa = consulta.getCaixa();
        if (caixa != null) {
            // Atualize o valor do caixa com o valor da consulta.
            caixa.setValorEntrada(caixa.getValorEntrada() + consulta.getValor());
            // Recalcula os rateios.
            caixa.calcularRateio();
            caixaRepository.save(caixa);
        }
        return consultaRepository.save(consulta);
    }

}
