package Model;

import java.util.Objects;
import java.sql.Date;


public class Libros {
    
    private int libroId;
    private String tituloLibro;
    private Date fechaPublicacion;
    private int editorialId;
    private int categoriaId;
    private String nombreEditorial;
    private String nombreCategoria;


    public Libros(int libroId, String tituloLibro, Date fechaPublicacion, int editorialId, int categoriaId) {
        this.libroId = libroId;
        this.tituloLibro = tituloLibro;
        this.fechaPublicacion = fechaPublicacion;
        this.editorialId = editorialId;
        this.categoriaId = categoriaId;
    }
    
    public Libros(int libroId, String tituloLibro, Date fechaPublicacion, int editorialId, String nombreEditorial, int categoriaId, String nombreCategoria) {
    this.libroId = libroId;
    this.tituloLibro = tituloLibro;
    this.fechaPublicacion = fechaPublicacion;
    this.editorialId = editorialId;
    this.nombreEditorial = nombreEditorial;
    this.categoriaId = categoriaId;
    this.nombreCategoria = nombreCategoria;
}

    
    public Libros() {
    // Constructor vacío necesario para setear manualmente campos
    }
    
    public Libros(String tituloLibro, Date fechaPublicacion, int editorialId, int categoriaId) {
    this.tituloLibro = tituloLibro;
    this.fechaPublicacion = fechaPublicacion;
    this.editorialId = editorialId;
    this.categoriaId = categoriaId;
}    

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(int editorialId) {
        this.editorialId = editorialId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
    
    public String getNombreEditorial() {
    return nombreEditorial;
}

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.libroId;
        hash = 97 * hash + Objects.hashCode(this.tituloLibro);
        hash = 97 * hash + Objects.hashCode(this.fechaPublicacion);
        hash = 97 * hash + this.editorialId;
        hash = 97 * hash + this.categoriaId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libros other = (Libros) obj;
        if (this.libroId != other.libroId) {
            return false;
        }
        if (this.editorialId != other.editorialId) {
            return false;
        }
        if (this.categoriaId != other.categoriaId) {
            return false;
        }
        if (!Objects.equals(this.tituloLibro, other.tituloLibro)) {
            return false;
        }
        if (!Objects.equals(this.fechaPublicacion, other.fechaPublicacion)) {
            return false;
        }
        return true;
    }
    
}

    
