package com.gazelle.gazelle.Controllers;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.gazelle.gazelle.Models.ProductoModel;

import com.gazelle.gazelle.Services.ProductoService;


@RestController
@RequestMapping("/api")
public class ProductoController {

        @Autowired
        ProductoService productoService;

        @PostMapping("/productos")
        public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody ProductoModel producto, Errors error){

            Map<String, String> respuesta = new HashMap<>();

            ProductoModel u = this.productoService.buscarProducto(producto.getNombreProducto());
            if (u.getId() == null) {
                this.productoService.guardarProducto(producto);
                respuesta.put("mensaje", "Se agregó correctamente el producto");
    
            } else {
                respuesta.put("mensaje", "El producto ya esta registrado");
    
            }
    
            return ResponseEntity.ok(respuesta);

        }
    
}
