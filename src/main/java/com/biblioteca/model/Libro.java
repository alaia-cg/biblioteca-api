package com.biblioteca.model;

// Clase simple para representar un libro (sin base de datos)
public class Libro {
    
    // Atributos básicos
    private Long id;           // Identificador único
    private String titulo;     // Nombre del libro
    private String autor;      // Quien escribió el libro
    private int paginas;       // Número de páginas
    private boolean disponible; // ¿Está disponible para préstamo?
    
    // Constructor vacío (necesario para Spring)
    public Libro() {
    }
    
    // Constructor con todos los parámetros
    public Libro(Long id, String titulo, String autor, int paginas, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.disponible = disponible;
    }
    
    // Getters (para obtener los valores)
    public Long getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public int getPaginas() {
        return paginas;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    // Setters (para cambiar los valores)
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
