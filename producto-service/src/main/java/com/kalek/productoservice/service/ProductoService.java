package com.kalek.productoservice.service;

import com.kalek.productoservice.entity.Producto;
import com.kalek.productoservice.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public  Producto buscarPorId(Long id){
        return productoRepository.findById(id).get();
    }

    public Producto guardarProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    public boolean existeElId(Long id) {
        return productoRepository.existsById(id);
    }





}
