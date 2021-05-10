package com.example.demo.controller;

import com.example.demo.model.UserLogin;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/logar")
    public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user){
        return usuarioService.logar(user).map(resp ->  ResponseEntity.ok (resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> GetById(@PathVariable long id){
    	return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.CadastrarUsuario(usuario));
    }
}
