package com.company.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.dao.IProductDao;
import com.company.inventory.model.Category;
import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponseRest;

@Service
public class ProductServicesImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> search() {
        ProductResponseRest response = new ProductResponseRest();
        try {
            List<Product> products = (List<Product>) productDao.findAll();
            response.getProductResponse().setProduct(products);
            response.setMetadata("Respuesta OK", "00", "Consulta exitosa");
        } catch (Exception e) {
            response.setMetadata("Respuesta NO OK", "-1", "Error al consultar productos");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchById(Long id) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        try {
            Optional<Product> product = productDao.findById(id);
            if (product.isPresent()) {
                list.add(product.get());
                response.getProductResponse().setProduct(list);
                response.setMetadata("Respuesta OK", "00", "Producto encontrado");
            } else {
                response.setMetadata("Respuesta NO OK", "-1", "Producto no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta NO OK", "-1", "Error al consultar producto por id");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> save(Product product) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        try {
            if (product.getCategory() != null && product.getCategory().getId() != null) {
                Optional<Category> category = categoryDao.findById(product.getCategory().getId());
                if (category.isPresent()) {
                    product.setCategory(category.get());
                } else {
                    response.setMetadata("Respuesta NO OK", "-1", "Categoría no encontrada");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
            }

            Product productSaved = productDao.save(product);
            list.add(productSaved);
            response.getProductResponse().setProduct(list);
            response.setMetadata("Respuesta OK", "00", "Producto guardado");
        } catch (Exception e) {
            response.setMetadata("Respuesta NO OK", "-1", "Error al guardar producto");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> update(Product product, Long id) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        try {
            Optional<Product> productSearch = productDao.findById(id);
            if (productSearch.isEmpty()) {
                response.setMetadata("Respuesta NO OK", "-1", "Producto no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            Product productToUpdate = productSearch.get();
            productToUpdate.setName(product.getName());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setAccount(product.getAccount());
            productToUpdate.setPicture(product.getPicture());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setStock(product.getStock());

            if (product.getCategory() != null && product.getCategory().getId() != null) {
                Optional<Category> category = categoryDao.findById(product.getCategory().getId());
                if (category.isPresent()) {
                    productToUpdate.setCategory(category.get());
                } else {
                    response.setMetadata("Respuesta NO OK", "-1", "Categoría no encontrada");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
            }

            Product updated = productDao.save(productToUpdate);
            list.add(updated);
            response.getProductResponse().setProduct(list);
            response.setMetadata("Respuesta OK", "00", "Producto actualizado");
        } catch (Exception e) {
            response.setMetadata("Respuesta NO OK", "-1", "Error al actualizar producto");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> deleteById(Long id) {
        ProductResponseRest response = new ProductResponseRest();
        try {
            if (!productDao.existsById(id)) {
                response.setMetadata("Respuesta NO OK", "-1", "Producto no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            productDao.deleteById(id);
            response.setMetadata("Respuesta OK", "00", "Producto eliminado");
        } catch (Exception e) {
            response.setMetadata("Respuesta NO OK", "-1", "Error al eliminar producto");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
