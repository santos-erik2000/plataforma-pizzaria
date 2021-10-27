package com.system.application.pizzaria.external.database.entity.adapter;

import com.system.application.pizzaria.entity.Cozinheiro;
import com.system.application.pizzaria.entity.Ingrediente;
import com.system.application.pizzaria.entity.Pedido;
import com.system.application.pizzaria.entity.enums.ErrorType;
import com.system.application.pizzaria.exception.CozinheiroException;
import com.system.application.pizzaria.exception.IngredienteException;
import com.system.application.pizzaria.exception.PedidoException;
import com.system.application.pizzaria.external.database.entity.CozinheiroModel;
import com.system.application.pizzaria.external.database.entity.IngredienteModel;
import com.system.application.pizzaria.external.database.entity.PedidoModel;
import com.system.application.pizzaria.util.ConfigUtils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CozinheiroModelAdapter {

    public static Cozinheiro modelToEntity(CozinheiroModel cozinheiroModel) throws CozinheiroException {
        Cozinheiro cozinheiro = new Cozinheiro();
        List<Pedido> pedidoList = new ArrayList<>();
        List<Ingrediente> ingredienteList = new ArrayList<>();

        try {
            cozinheiro.setIdCozinheiro(cozinheiroModel.getIdCozinheiroModel());
            cozinheiro.setNome(cozinheiroModel.getNomeModel());
            cozinheiro.setCpf(cozinheiroModel.getCpfModel());
            cozinheiro.setApelido(cozinheiroModel.getApelidoModel());
            cozinheiro.setSenha(cozinheiroModel.getSenhaModel());
            cozinheiro.setTelefone(cozinheiroModel.getTelefoneModel());
            cozinheiro.setHorarioTrabalho(cozinheiroModel.getHorarioTrabalhoModel());
            cozinheiro.setSalario(cozinheiroModel.getSalarioModel());
            cozinheiro.setIdCozinheiro(cozinheiroModel.getIdCozinheiroModel());
            percorreListaPizzasModel(cozinheiroModel, pedidoList);
            percorreListaIngredientesModel(cozinheiroModel, ingredienteList);
            cozinheiro.setListaPedidoCozinheiro(pedidoList);
            cozinheiro.setListaIngredientesPizzaCozinheiro(ingredienteList);

            return cozinheiro;
        } catch (Exception e) {
            ConfigUtils.logger.warning("Error ao fazer adapter de CozinheiroModel para Cozinheiro");
            throw new CozinheiroException(ErrorType.VALIDATIONS, "Adapter modelToEntity Cozinheiro is Null", new Date(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static void percorreListaIngredientesModel(CozinheiroModel cozinheiroModel, List<Ingrediente> ingredienteList) {
        cozinheiroModel.getListaIngredientesPizzaModelCozinheiroModel().forEach(ingredienteModel -> {
            try {
                ingredienteList.add(IngredienteModelAdapter.modelToEntity(ingredienteModel));
            } catch (IngredienteException e) {
                e.printStackTrace();
            }
        });
    }

    private static void percorreListaPizzasModel(CozinheiroModel cozinheiroModel, List<Pedido> pedidoList) {
        cozinheiroModel.getListaPedidoModelCozinheiroModel().forEach(pedidoModel -> {
            try {
                pedidoList.add(PedidoModelAdapter.modelToEntity(pedidoModel));
            } catch (PedidoException e) {
                e.printStackTrace();
            }
        });
    }

    public static CozinheiroModel entityToModel(Cozinheiro cozinheiro) throws CozinheiroException {
        CozinheiroModel cozinheiroModel = new CozinheiroModel();
        List<PedidoModel> pedidoModelList = new ArrayList<>();
        List<IngredienteModel> ingredienteModelList = new ArrayList<>();

        try {
            cozinheiroModel.setIdCozinheiroModel(cozinheiro.getIdCozinheiro());
            cozinheiroModel.setNomeModel(cozinheiro.getNome());
            cozinheiroModel.setCpfModel(cozinheiro.getCpf());
            cozinheiroModel.setApelidoModel(cozinheiro.getApelido());
            cozinheiroModel.setSenhaModel(cozinheiro.getSenha());
            cozinheiroModel.setTelefoneModel(cozinheiro.getTelefone());
            cozinheiroModel.setHorarioTrabalhoModel(cozinheiro.getHorarioTrabalho());
            cozinheiroModel.setSalarioModel(cozinheiro.getSalario());
            cozinheiroModel.setIdCozinheiroModel(cozinheiro.getIdCozinheiro());
            percorreListaPedidosEntity(cozinheiro, pedidoModelList);
            percorreListaIngredientesEntity(cozinheiro, ingredienteModelList);
            cozinheiroModel.setListaPedidoModelCozinheiroModel(pedidoModelList);
            cozinheiroModel.setListaIngredientesPizzaModelCozinheiroModel(ingredienteModelList);
            return cozinheiroModel;
        } catch (Exception e) {
            ConfigUtils.logger.warning("Error ao fazer adapter de Cozinheiro para CozinheiroModel");
            throw new CozinheiroException(ErrorType.VALIDATIONS, "Adapter entityToModel Cozinheiro is Null", new Date(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static void percorreListaIngredientesEntity(Cozinheiro cozinheiro, List<IngredienteModel> ingredienteModelList) {
        cozinheiro.getListaIngredientesPizzaCozinheiro().forEach(ingrediente -> {
            try {
                ingredienteModelList.add(IngredienteModelAdapter.entityToModel(ingrediente));
            } catch (IngredienteException e) {
                e.printStackTrace();
            }
        });
    }

    private static void percorreListaPedidosEntity(Cozinheiro cozinheiro, List<PedidoModel> pedidoModelList) {
        cozinheiro.getListaPedidoCozinheiro().forEach(pedido -> {
            try {
                pedidoModelList.add(PedidoModelAdapter.entityToModel(pedido));
            } catch (PedidoException e) {
                e.printStackTrace();
            }
        });
    }

    public static List<CozinheiroModel> entityListToModelList(List<Cozinheiro> cozinheiroList) {
        List<CozinheiroModel> cozinheiroModelList = new ArrayList<>();
        cozinheiroList.forEach(cozinheiro -> {
            try {
                cozinheiroModelList.add(entityToModel(cozinheiro));
            } catch (CozinheiroException e) {
                e.printStackTrace();
            }
        });
        return cozinheiroModelList;
    }

    public static List<Cozinheiro> modelListToEntityList(List<CozinheiroModel> cozinheiroModelList) {
        List<Cozinheiro> cozinheiroList = new ArrayList<>();
        cozinheiroModelList.forEach(cozinheiroModel -> {
            try {
                cozinheiroList.add(modelToEntity(cozinheiroModel));
            } catch (CozinheiroException e) {
                e.printStackTrace();
            }
        });
        return cozinheiroList;
    }

}
