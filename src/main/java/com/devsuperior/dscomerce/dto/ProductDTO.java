package com.devsuperior.dscomerce.dto;

import com.devsuperior.dscomerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
/**
 * DTO (Data Transfer Object) para transporte e validação de dados de Produto.
 * 
 * Esta classe contém anotações de validação que garantem que os dados recebidos
 * nas requisições POST atendam os seguintes critérios:
 * 
 * - name: obrigatório, entre 3 e 80 caracteres.
 * - description: obrigatória, no mínimo 10 caracteres.
 * - price: deve ser um número positivo.
 * - imgUrl: opcional.
 *
 * As validações são acionadas automaticamente quando usado com a anotação @Valid
 * no controller.
 */
public class ProductDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "O nome do produto deve ter entre 3 e 80 caracteres")
    @NotBlank(message = "Nome do produto é obrigatório")
    private String name;

    @NotBlank(message = "Descrição do produto é obrigatória")
    @Size(min = 10, message = "A descrição do produto deve ter no mínimo 10 caracteres")
    private String description;

    @Positive(message = "O preço do produto deve ser um valor positivo")
    private Double price;
    private String imgUrl;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

}
