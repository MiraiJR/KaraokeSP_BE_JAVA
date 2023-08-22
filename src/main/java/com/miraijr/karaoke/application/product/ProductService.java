package com.miraijr.karaoke.application.product;

import com.miraijr.karaoke.application.group_product.GroupProductEntity;
import com.miraijr.karaoke.application.group_product.GroupProductService;
import com.miraijr.karaoke.application.product.DTOs.ProductDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final GroupProductService groupProductService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, GroupProductService groupProductService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.groupProductService = groupProductService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ProductEntity createNewProduct(ProductDTO productDTO) {
        GroupProductEntity group = groupProductService.getGroupProductByCode(productDTO.getGroupProduct());

        if (group == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code [%s] not found".formatted(productDTO.getGroupProduct()));
        }

        ProductEntity isUsedCode = productRepository.findProductByCode(productDTO.getCode());

        if(isUsedCode != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code [%s] is used".formatted(productDTO.getCode()));
        }

        ProductEntity newProduct = new ProductEntity(productDTO.getCode(), productDTO.getName(), productDTO.getPrice());
        newProduct.setGroupProduct(group);

        return productRepository.save(newProduct);
    }

    public List<ProductEntity> getProductsByGroup(String group) {
        if (group.equals("all")) {
            return productRepository.findAll();
        }

        GroupProductEntity groupProduct = groupProductService.getGroupProductByCode(group);

        if (groupProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code [%s] not found".formatted(group));
        }

        return groupProduct.getProducts();
    }

    @Transactional
    public ProductEntity updateProduct(Integer productId, ProductDTO productDTO) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product id [%s] not found".formatted(productId)));

        GroupProductEntity group = groupProductService.getGroupProductByCode(productDTO.getGroupProduct());

        if (group == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code [%s] not found".formatted(productDTO.getGroupProduct()));
        }

        ProductEntity isUsedCode = productRepository.findProductByCode(productDTO.getCode());

        if(isUsedCode != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code [%s] is used".formatted(productDTO.getCode()));
        }

        product.setGroupProduct(group);
        product.setCode(productDTO.getCode());
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());

        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Integer productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product id [%s] not found".formatted(productId)));

        productRepository.delete(product);
    }
}
