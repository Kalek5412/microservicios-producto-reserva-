package com.kalek.productoservice.controller;

import com.kalek.productoservice.entity.Producto;
import com.kalek.productoservice.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/lista")
    public List<Producto> listaDeProductos(){
        return productoService.listarProductos();
    }
    @GetMapping("lista/{id}")
    public ResponseEntity<Producto> detalleDeProducto(@PathVariable Long id){
        if(!productoService.existeElId(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productoService.buscarPorId(id),HttpStatus.OK);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto){
        return new ResponseEntity<>(productoService.guardarProducto(producto),HttpStatus.CREATED);
    }
    @PutMapping("editar/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        if(!productoService.existeElId(id)){
            return new ResponseEntity("No existe el id",HttpStatus.NOT_FOUND);
        }
        Producto prodEdit =productoService.buscarPorId(id);
        prodEdit.setNombre(producto.getNombre());
        prodEdit.setDescripcion(producto.getDescripcion());
        prodEdit.setPrecio(producto.getPrecio());
        return new ResponseEntity<>(productoService.guardarProducto(prodEdit),HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        productoService.eliminar(id);
        return new ResponseEntity("producto Eliminado",HttpStatus.OK);
    }


}
