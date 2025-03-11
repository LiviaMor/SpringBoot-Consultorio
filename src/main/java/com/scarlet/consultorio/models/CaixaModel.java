package com.scarlet.consultorio.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@ToString
@Table (name = "caixa")
public class CaixaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCaixa;
    @Column (name = "dataEntrada")
    private LocalDate dataEntrada;
    // Valor total coletado do(s) atendimento(s)
    @Column (name = "valorEntrada")
    private double valorEntrada;
    // Valor de saída (se tiver alguma despesa, por exemplo)
    @Column (name = "valorSaida")
    private double valorSaida;
    // Você pode armazenar os valores calculados (opcional)...
    @Column(name = "valorDentista")
    private double valorDentista;

    @Column(name = "valorClinica")
    private double valorClinica;

    // Se o caixa estiver relacionado a várias consultas:
    @OneToMany(mappedBy = "caixa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConsultaModel> consultas;

    /**
     * Método auxiliar para calcular os percentuais.
     * Este método pode ser chamado a cada nova inclusão de consulta ou
     * durante um fechamento do caixa.
     */
    public void calcularRateio() {
        // Supondo que o valorEntrada seja o total das consultas já somadas,
        // aplica a divisão:
        this.valorDentista = this.valorEntrada * 0.60;
        this.valorClinica = this.valorEntrada * 0.40;
    }


}
