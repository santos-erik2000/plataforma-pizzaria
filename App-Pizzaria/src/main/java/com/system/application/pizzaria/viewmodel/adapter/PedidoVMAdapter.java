package com.system.application.pizzaria.viewmodel.adapter;

import com.system.application.pizzaria.entity.Bebida;
import com.system.application.pizzaria.entity.Pedido;
import com.system.application.pizzaria.entity.Pizza;
import com.system.application.pizzaria.entity.enums.ErrorType;
import com.system.application.pizzaria.exception.BebidaException;
import com.system.application.pizzaria.exception.PedidoException;
import com.system.application.pizzaria.exception.PizzaException;
import com.system.application.pizzaria.util.ConfigUtils;
import com.system.application.pizzaria.viewmodel.PedidoVM;
import com.system.application.pizzaria.viewmodel.PizzaVM;
import com.system.application.pizzaria.viewmodel.BebidaVM;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoVMAdapter {

    public static Pedido viewModelToEntity(PedidoVM pedidoVM) throws PedidoException {
        Pedido pedido = new Pedido();
        List<Pizza> pizzaList = new ArrayList<>();
        List<Bebida> bebidaList = new ArrayList<>();

        try {
            pedido.setIdPedido(pedidoVM.getIdPedidoVM());
            pedido.setStatusPedido(pedidoVM.getStatusPedidoVM());
            pedidoVM.getListaPizzaVMPedidoVM().forEach(pizzaVM -> {
                try {
                    pizzaList.add(PizzaVMAdapter.viewModelToEntity(pizzaVM));
                } catch (PizzaException e) {
                    e.printStackTrace();
                }
            });
            pedidoVM.getListaBebidaVMPedidoVM().forEach(bebidaVM -> {
                try {
                    bebidaList.add(BebidaVMAdapter.viewModelToEntity(bebidaVM));
                } catch (BebidaException e) {
                    e.printStackTrace();
                }
            });
            pedido.setListaBebidaPedido(bebidaList);
            pedido.setListaPizzaPedido(pizzaList);
            pedido.setHorarioPedido(pedidoVM.getHorarioPedidoVM());
            pedido.setHorarioEstimadoPedido(pedidoVM.getHorarioEstimadoPedidoVM());
            pedido.setPrecoPedido(pedidoVM.getPrecoPedidoVM());
            pedido.setComentarioPedido(pedidoVM.getComentarioPedidoVM());
            return pedido;
        } catch (Exception e) {
            ConfigUtils.logger.warning("Error ao fazer adapter de PedidoModel para Pedido");
            throw new PedidoException(ErrorType.VALIDATIONS, "Adapter viewModelToEntity Pedido is Null", new Date(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static PedidoVM entityToViewModel(Pedido pedido) throws PedidoException {
        PedidoVM pedidoVM = new PedidoVM();
        List<PizzaVM> pizzaVMList = new ArrayList<>();
        List<BebidaVM> bebidaVMList = new ArrayList<>();

        try {
            pedidoVM.setIdPedidoVM(pedido.getIdPedido());
            pedidoVM.setStatusPedidoVM(pedido.getStatusPedido());
            pedido.getListaPizzaPedido().forEach(pizza -> {
                try {
                    pizzaVMList.add(PizzaVMAdapter.entityToViewModel(pizza));
                } catch (PizzaException e) {
                    e.printStackTrace();
                }
            });
            pedido.getListaBebidaPedido().forEach(bebida -> {
                try {
                    bebidaVMList.add(BebidaVMAdapter.entityToViewModel(bebida));
                } catch (BebidaException e) {
                    e.printStackTrace();
                }
            });
            pedidoVM.setListaPizzaVMPedidoVM(pizzaVMList);
            pedidoVM.setListaBebidaVMPedidoVM(bebidaVMList);
            pedidoVM.setHorarioPedidoVM(pedido.getHorarioPedido());
            pedidoVM.setHorarioEstimadoPedidoVM(pedido.getHorarioEstimadoPedido());
            pedidoVM.setPrecoPedidoVM(pedido.getPrecoPedido());
            pedidoVM.setComentarioPedidoVM(pedido.getComentarioPedido());
            return pedidoVM;
        } catch (Exception e) {
            ConfigUtils.logger.warning("Error ao fazer adapter de Pedido para PedidoViewModel");
            throw new PedidoException(ErrorType.VALIDATIONS, "Adapter entityToViewModel Pedido is Null", new Date(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static List<PedidoVM> entityListToViewModelList(List<Pedido> pedidoList) {
        List<PedidoVM> pedidoVMList = new ArrayList<>();
        pedidoList.forEach(pedido -> {
            try {
                pedidoVMList.add(entityToViewModel(pedido));
            } catch (PedidoException e) {
                e.printStackTrace();
            }
        });
        return pedidoVMList;
    }

    public static List<Pedido> viewModelListToEntityList(List<PedidoVM> pedidoVMList) {
        List<Pedido> pedidoList = new ArrayList<>();
        pedidoVMList.forEach(pedidoVM -> {
            try {
                pedidoList.add(viewModelToEntity(pedidoVM));
            } catch (PedidoException e) {
                e.printStackTrace();
            }
        });
        return pedidoList;
    }

}


