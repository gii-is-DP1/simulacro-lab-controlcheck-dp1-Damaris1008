package org.springframework.samples.petclinic.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.pet.Visit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
	
	private ProductRepository productRepo;
	
	@Autowired
	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	@Transactional(readOnly = true)
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
	
	@Transactional(readOnly = true)
    public List<ProductType> getAllProductsType(){
        return productRepo.findAllProductTypes();
    }
	
	@Transactional(readOnly = true)
    public List<Product> getProductsCheaperThan(double price) {
        return productRepo.findByPriceLessThan(price);
    }
    
    @Transactional(readOnly = true)
    public ProductType getProductType(String typeName) {
        return productRepo.findProductTypeByName(typeName);
    }

	@Transactional
	public Product save(Product product) throws DataAccessException {
		return productRepo.save(product);
	}

    
}
