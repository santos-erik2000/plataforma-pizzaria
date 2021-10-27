package com.system.application.pizzaria.api;

import com.system.application.pizzaria.entity.Atendente;
import com.system.application.pizzaria.exception.AtendenteException;
import com.system.application.pizzaria.usecase.atendente.GetAllAtendente;
import com.system.application.pizzaria.usecase.atendente.GetAtendenteById;
import com.system.application.pizzaria.usecase.atendente.SaveAtendente;
import com.system.application.pizzaria.usecase.atendente.ValidateAtendenteByCPF;
import com.system.application.pizzaria.viewmodel.AtendenteVM;
import com.system.application.pizzaria.viewmodel.adapter.AtendenteVMAdapter;
import com.system.application.pizzaria.viewmodel.adapter.cadastro.AtendenteCadastroVMAdapter;
import com.system.application.pizzaria.viewmodel.cadastro.AtendenteCadastroVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/atendente", produces = MediaType.APPLICATION_JSON_VALUE)
public class AtendenteController {

    @Autowired
    private SaveAtendente saveAtendente;

    @Autowired
    private GetAllAtendente getAllAtendente;

    @Autowired
    private GetAtendenteById getAtendenteById;

    @Autowired
    private ValidateAtendenteByCPF validateAtendenteByCPF;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AtendenteVM>> getAllAtendentesController() throws AtendenteException {
        List<Atendente> listaAtendenteEntity = getAllAtendente.getAllAtendentes();
        List<AtendenteVM> listAtendenteVM = AtendenteVMAdapter.entityListToViewModelList(listaAtendenteEntity);
        return ResponseEntity.ok().body(listAtendenteVM);
    }


    @GetMapping("/{idAtendente}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AtendenteVM> getAtendeteByIdController(@PathVariable Integer idAtendente) throws AtendenteException {
        Atendente atendenteEntity = getAtendenteById.getAtendeteById(idAtendente);
        AtendenteVM atendenteVM = AtendenteVMAdapter.entityToViewModel(atendenteEntity);
        return ResponseEntity.ok().body(atendenteVM);
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AtendenteCadastroVM> saveAtendente(@RequestBody AtendenteCadastroVM atendenteCadastroVM) throws AtendenteException {
        Atendente atendente = AtendenteCadastroVMAdapter.viewModelToEntity(atendenteCadastroVM);
        AtendenteCadastroVM atendenteCadastradoRetornado = AtendenteCadastroVMAdapter.entityToViewModel(saveAtendente.saveAtendente(atendente));
        return ResponseEntity.ok().body(atendenteCadastradoRetornado);

    }
}
