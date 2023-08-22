package com.miraijr.karaoke.application.product;

import com.miraijr.karaoke.application.product.DTOs.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
@ResponseStatus(HttpStatus.OK)
public class ProductController {
    private final  ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public List<ProductEntity> handleGetProducts(@RequestParam(defaultValue = "all") String group) {
        List<ProductEntity> products = productService.getProductsByGroup(group);

        return products;
    }

    @PostMapping()
    public ProductEntity handleCreateNewProduct(@RequestBody @Valid ProductDTO productDTO) {
        ProductEntity newProduct = productService.createNewProduct(productDTO);

        return newProduct;
    }

    @PutMapping("/{productId}")
    public ProductEntity handleUpdateProduct(@RequestBody @Valid ProductDTO productDTO, @PathVariable Integer productId) {
        ProductEntity newProduct = productService.updateProduct(productId, productDTO);

        return newProduct;
    }

    @DeleteMapping("/{productId}")
    public String handleDeleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);

        return "Product id [%s] is deleted successfully!".formatted(productId);
    }
}
