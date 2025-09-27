package br.com.fiap.mottomap.model;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Schema(description = "Entidade que representa um usuário no sistema da Mottu")
@Table(name = "T_MM_USUARIO")
public class Usuario {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do usuário", example = "1", readOnly = true)
    @Column(name = "CD_USUARIO")
    private Long id;

    @NotBlank(message = "O nome do usuário é obrigatório")
    @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres")
    @Schema(description = "Nome do usuário", example = "Eduardo Silva", required = true)
    @Column(name = "NM_USUARIO")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    @Schema(description = "E-Mail do usuário", example = "edu@mottomap.com", required = true)
    @Column(name = "DS_EMAIL")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, max = 100, message = "A senha deve ter entre 8 e 100 caracteres")
    @Schema(description = "Senha do usuário", example = "Edu12345", required = true)
    @Column(name = "DS_SENHA")
    private String senha;

    @NotNull(message = "O cargo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Cargo do usuário", example = "COL_PATIO", required = true)
    @Column(name = "DS_CARGO")
    private CargoUsuario cargoUsuario;

    @NotNull(message = "campo obrigatório")
    @ManyToOne
    @JoinColumn(name = "CD_FILIAL")
    private Filial filial;
}
