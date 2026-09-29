package br.com.api.habitFlow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "habitos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Habito {

    // @ID e @GeneratedValue(strategy = GenerationType.IDENTITY) -  servem para criar uma coluna que sera o ID do hábito, ligado diretamente a ele. Esse strategy é como sera que o spring vai gerar o id automaticamente, assim permitindo que nenhum ID seja igual ao outro.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    // @Enumerated - Isso diz ao spring que o tipo de variavel que estamos lidando apesar de ser um ENUM ela se comporta igual a uma STRING
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    private Integer target;

    private Unit unit;

    private Boolean active;

    private LocalDateTime createAt;

    //COLOCAR USUARIO AQUI....





}
