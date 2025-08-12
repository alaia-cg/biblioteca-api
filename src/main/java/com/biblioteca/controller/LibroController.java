package com.biblioteca.controller;

import com.biblioteca.model.Libro;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.ArrayList;
import java.util.List;

@RestController // Esta anotación dice: "Soy un controlador REST"
@RequestMapping("/api/libros") // Todas las URLs empezarán con "/api/libros"
public class LibroController {
    
    // Lista para guardar libros en memoria (sin base de datos por ahora)
    private List<Libro> libros = new ArrayList<>();
    private Long contadorId = 1L; // Para generar IDs únicos
    
    // CONSTRUCTOR: Agregar algunos libros de ejemplo
    public LibroController() {
        // Creamos algunos libros de prueba
        libros.add(new Libro(contadorId++, "Don Quijote", "Cervantes", 500, true));
        libros.add(new Libro(contadorId++, "Cien años de soledad", "García Márquez", 400, true));
        libros.add(new Libro(contadorId++, "El Principito", "Saint-Exupéry", 100, false));
    }
    
    // GET /api/libros - Obtener TODOS los libros
    @Operation(summary = "Obtiene todos los libros", description = "Devuelve la lista completa de libros registrados en la biblioteca")
    @ApiResponse(responseCode = "200", description = "Operación exitosa: lista de libros devuelta")
    @GetMapping // Responde a peticiones GET
    public List<Libro> obtenerTodosLosLibros() {
        return libros; // Devuelve la lista completa de libros
    }
    
    // GET /api/libros/1 - Obtener UN libro por ID
    @Operation(summary = "Obtiene un libro por ID", description = "Devuelve el libro correspondiente al ID registrado en la biblioteca")
    @ApiResponse(responseCode = "200", description = "Operación exitosa: libro con respectivo ID devuelto")
    @GetMapping("/{id}") // {id} es un parámetro variable en la URL
    public Libro obtenerLibroPorId(
        @Parameter(description = "ID del libro que se desea consultar") @PathVariable Long id) {
        // Buscar el libro por ID
        for (Libro libro : libros) {
            if (libro.getId().equals(id)) {
                return libro; // Encontrado, devolverlo
            }
        }
        return null; // No encontrado
    }
    
    // POST /api/libros - Crear un NUEVO libro
    @Operation(summary = "Crea un nuevo libro", description = "Crea un nuevo libro con todos sus parámetros")
    @ApiResponse(responseCode = "200", description = "Operación exitosa: nuevo libro creado")
    @PostMapping // Responde a peticiones POST
    public Libro crearLibro(@RequestBody Libro nuevoLibro) {
        // Asignar un ID único al nuevo libro
        nuevoLibro.setId(contadorId++);
        // Agregar a la lista
        libros.add(nuevoLibro);
        // Devolver el libro creado
        return nuevoLibro;
    }
    
    // PUT /api/libros/1 - ACTUALIZAR un libro existente
    @Operation(summary = "Actualiza un libro", description = "Actualiza un libro que ya se encuentra registrado en la biblioteca")
    @ApiResponse(responseCode = "200", description = "Operación exitosa: libro actualizado")
    @PutMapping("/{id}")
    public Libro actualizarLibro(@Parameter(description = "ID del libro que se desea actualizar") @PathVariable Long id, @RequestBody Libro libroActualizado) {
        // Buscar el libro por ID
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId().equals(id)) {
                // Mantener el mismo ID
                libroActualizado.setId(id);
                // Reemplazar el libro en la lista
                libros.set(i, libroActualizado);
                return libroActualizado;
            }
        }
        return null; // No encontrado
    }
    
    // DELETE /api/libros/1 - ELIMINAR un libro
    @Operation(summary = "Elimina un libro", description = "Elimina un libro que ya se encuentra registrado en la biblioteca")
    @ApiResponse(responseCode = "200", description = "Operación exitosa: libro eliminado")
    @DeleteMapping("/{id}")
    public String eliminarLibro(@Parameter(description = "ID del libro que se desea eliminar") @PathVariable Long id) {
        // Buscar y eliminar el libro
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId().equals(id)) {
                libros.remove(i);
                return "Libro eliminado correctamente";
            }
        }
        return "Libro no encontrado";
    }
    
    // GET /api/libros/disponibles - Obtener solo libros DISPONIBLES
    @Operation(summary = "Obtiene libros disponibles", description = "Devuelve una lista de libros que están disponibles para préstamo")
    @ApiResponse(responseCode = "200", description = "Operación exitosa: lista de libros disponibles devuelta")
    @GetMapping("/disponibles")
    public List<Libro> obtenerLibrosDisponibles() {
        List<Libro> disponibles = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                disponibles.add(libro);
            }
        }
        return disponibles;
    }
}
