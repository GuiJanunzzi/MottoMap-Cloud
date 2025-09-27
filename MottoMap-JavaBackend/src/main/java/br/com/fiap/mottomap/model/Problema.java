package br.com.fiap.mottomap.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um problema de uma moto no sistema da Mottu")
@Table(name = "T_MM_PROBLEMA")
public class Problema {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do usuário", example = "1", readOnly = true)
    @Column(name = "CD_PROBLEMA")
    private Long id;

    @NotNull(message = "O tipo do problema é obrigatório")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo do problema da moto", example = "MECANICO", required = true)
    @Column(name = "DS_TIPO")
    private TipoProblema tipoProblema;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 10, max = 255, message = "A descrição deve ter entre 10 e 255 caracteres")
    @Schema(description = "Descrição do problema", example = "MECANICO", required = true)
    @Column(name = "DS_DESCRICAO")
    private String descricao;

    @NotNull(message = "A data é obrigatória")
    @PastOrPresent(message = "A data não pode ser no futuro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data do registro do problema", example = "17/05/2025 10:45:33", required = true)
    @Column(name = "DT_REGISTRO")
    private LocalDate dataRegistro;

    @NotNull(message = "O status de resolução é obrigatório")
    @Schema(description = "Indica se o probelma foi resolvido (true) ou não (false)", example = "false", required = true)
    @Column(name = "DS_RESOLVIDO")
    private Boolean resolvido;

    @NotNull(message = "A moto é obrigatória")
    @ManyToOne
    @JoinColumn(name = "CD_MOTO")
    private Moto moto;

    @NotNull(message = "O usuário que reportou é obrigatório")
    @ManyToOne
    @JoinColumn(name = "CD_USUARIO")
    private Usuario usuario;
}
