package com.miraijr.karaoke.application.ordered_product;

import com.miraijr.karaoke.application.order.OrderEntity;
import com.miraijr.karaoke.application.ordered_product.DTOs.OrderedProductDTO;
import com.miraijr.karaoke.application.product.ProductEntity;
import com.miraijr.karaoke.application.product.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedProductService {
    private final OrderedProductRepository orderedProductRepository;
    private final ProductService productService;

    @Autowired
    public OrderedProductService(OrderedProductRepository orderedProductRepository, ProductService productService) {
        this.orderedProductRepository = orderedProductRepository;
        this.productService = productService;
    }

    public OrderedProductEntity createOrderedProduct(OrderedProductEntity orderedProduct) {
        return orderedProductRepository.save(orderedProduct);
    }

    @Transactional
    public void deleteOrderedProduct(Integer orderId, Integer productId) {
        orderedProductRepository.deleteByOrder_IdAndProduct_Id(orderId, productId);
    }
    public OrderEntity orderProduct(OrderEntity order, OrderedProductDTO orderedProductDTO) {
        ProductEntity product =  productService.getProductById(orderedProductDTO.getProductId());

        List<OrderedProductEntity> orderedProducts = order.getProducts();

        for(int index = 0; index < orderedProducts.size(); index++) {
            if(orderedProducts.get(index).getProduct().getId() == orderedProductDTO.getProductId()) {
                OrderedProductEntity updateOrderedProduct = orderedProducts.get(index);
                updateOrderedProduct.setQuantity(updateOrderedProduct.getQuantity() + orderedProductDTO.getQuantity());
                updateOrderedProduct = createOrderedProduct(updateOrderedProduct);
                orderedProducts.set(index, updateOrderedProduct);
                order.setProducts(orderedProducts);

                return order;
            }
        }

        OrderedProductEntity newOrderedProduct = new OrderedProductEntity();
        newOrderedProduct.setQuantity(orderedProductDTO.getQuantity());
        newOrderedProduct.setProduct(product);
        newOrderedProduct.setOrder(order);
        newOrderedProduct = createOrderedProduct(newOrderedProduct);
        orderedProducts.add(newOrderedProduct);

        return order;
    }

    @Transactional
    public OrderEntity updateOrderedProduct(OrderEntity order, OrderedProductDTO orderedProductDTO) {
        ProductEntity product = productService.getProductById(orderedProductDTO.getProductId());
        List<OrderedProductEntity> orderedProducts = order.getProducts();

        for(int index = 0; index < orderedProducts.size(); index++) {
            if(orderedProducts.get(index).getProduct().getId() == orderedProductDTO.getProductId()) {
                if(orderedProductDTO.getQuantity() == 0) {
                    deleteOrderedProduct(order.getId(), product.getId());
                    orderedProducts.remove(index);
                    break;
                }

                OrderedProductEntity updateOrderedProduct = orderedProducts.get(index);
                updateOrderedProduct.setQuantity(orderedProductDTO.getQuantity());
                updateOrderedProduct = createOrderedProduct(updateOrderedProduct);
                orderedProducts.set(index, updateOrderedProduct);
                break;
            }
        }

        order.setProducts(orderedProducts);
        return order;
    }
}
