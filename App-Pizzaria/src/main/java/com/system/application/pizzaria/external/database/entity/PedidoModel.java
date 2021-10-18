package com.system.application.pizzaria.external.database.entity;

import com.system.application.pizzaria.entity.enums.StatusPedido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_PEDIDOS")
@Getter
@Setter
@EqualsAndHashCode
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idPedidoModel;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedidoModel = StatusPedido.AGUARDANDO;

    @Column(name = "PIZZAS")
    @OneToMany(mappedBy = "pedidoModel")
    private List<PizzaModel> listaPizzaModelPedidoModel;

    @Column(name = "BEBIDAS")
    @OneToMany(mappedBy = "pedidoModel")
    private List<BebidaModel> listaBebidaModelPedidoModel;

    @ManyToOne
    private AtendenteModel atendenteModel;

    @ManyToOne
    private CozinheiroModel cozinheiroModel;

    @Column(name = "HORARIO_PEDIDO", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime horarioPedidoModel;

    @Column(name = "HORARIO_ESTIMADO", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime horarioEstimadoPedidoModel;

    @Column(name = "PRECO", nullable = false, columnDefinition = "DOUBLE")
    private BigDecimal precoPedidoModel;

    @Column(name = "COMENTARIO", nullable = true, columnDefinition = "VARCHAR(300)")
    private String comentarioPedidoModel;

}
