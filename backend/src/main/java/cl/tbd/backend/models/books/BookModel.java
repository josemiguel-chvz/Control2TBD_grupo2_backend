package cl.tbd.backend.models.books;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class BookModel {
    private Integer id; 
    @NotNull(message = "Debe indicar código SKU")
    private String sku;
    @NotNull(message = "Debe indicar nombre del libro")
    @Size(min=1, max=200, message = "El nombre del libro debe tener al menos un caracter")
    private String title;
    @NotNull(message = "Debe indicar el autor del libro")
    @Size(min=1, max=200, message = "El nombre del autor debe tener al menos un caracter")
    private String author;
    @NotNull(message = "Debe indicar la cantidad de paginas")
    @Min(value=2, message="El libro debe tener al menos 2 paginas")
    private Integer pages;
    @NotNull(message = "Debe indicar el idioma")
    @Size(min=1, max=200, message = "El idioma debe tener al menos un caracter")
    private String language;
    private String created_at;
    private String updated_at;
    private String deleted_at;
    private Boolean is_deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCreated_at() {
        return created_at;
    }
    
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

}
