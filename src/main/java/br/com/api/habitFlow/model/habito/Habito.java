package br.com.api.habitFlow.model.habito;

import br.com.api.habitFlow.dto.DadosAtualizacaoHabito;
import br.com.api.habitFlow.dto.DadosCadastroHabito;
import br.com.api.habitFlow.model.user.User;
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

    // @ManyToOne() - Aqui eu quero dizer que varios Habitos podem ter 1 usuario e relacionando essas duas tabelas
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    //CONTRUTOR - Ele faz os dados coletados no dto se transformar em um objeto Habito
    public Habito(DadosCadastroHabito dados, User user){
        this.name = dados.name();
        this.description = dados.description();
        this.frequency = dados.frequency();
        this.target = dados.target();
        this.unit = dados.unit();
        this.active = true;
        this.createAt = LocalDateTime.now();
        this.user = user;
    }

    public void atualizarInformacoes(DadosAtualizacaoHabito dados){
        this.name = dados.name();
        this.description = dados.description();
        this.frequency = dados.frequency();
        this.target = dados.target();
        this.unit  = dados.unit();

    }


    public void desativarHabito() {
        this.active = false;
    }

    public void ativarHabito() {
        this.active = true;
    }
}
