package com.miraijr.karaoke.application.product;

import com.miraijr.karaoke.application.group_product.GroupProductEntity;
import com.miraijr.karaoke.application.group_product.GroupProductService;
import com.miraijr.karaoke.application.product.DTOs.ProductDTO;
import com.miraijr.karaoke.application.product.DTOs.UpdateProductDTO;
import com.miraijr.karaoke.application.product.types.ProductDetail;
import com.miraijr.karaoke.shared.utils.Converter;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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

        if (isUsedCode != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code [%s] is used".formatted(productDTO.getCode()));
        }

        ProductEntity newProduct = new ProductEntity(productDTO.getName(), productDTO.getCode(), productDTO.getPrice());
        newProduct.setGroupProduct(group);

        return productRepository.save(newProduct);
    }

    public List<ProductDetail> getProductsByGroup(String group) {
        List<ProductEntity> products = new ArrayList<>();
        if (group.equals("all")) {
            products = productRepository.findAll();
        } else {
            GroupProductEntity groupProduct = groupProductService.getGroupProductByCode(group);

            if (groupProduct == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code [%s] not found".formatted(group));
            }

            products = groupProduct.getProducts();
        }

        List<ProductDetail> productDetails = new ArrayList<>();
        for (ProductEntity product : products) {
            productDetails.add(Converter.convertProductToProductDetail(product));
        }

        return productDetails;
    }

    @Transactional
    public ProductEntity updateProduct(Integer productId, UpdateProductDTO productDTO) {
        ProductEntity product = getProductById(productId);
        GroupProductEntity group = groupProductService.getGroupProductByCode(productDTO.getGroupProduct());

        if (group == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code [%s] not found".formatted(productDTO.getGroupProduct()));
        }

        product.setGroupProduct(group);
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());

        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Integer productId) {
        ProductEntity product = getProductById(productId);

        productRepository.delete(product);
    }

    public ProductEntity getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product id [%s] not found".formatted(productId)));
    }
}
