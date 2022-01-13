package org.springframework.samples.petclinic.product;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeFormatter implements Formatter<ProductType>{
	
	private final ProductService prService;

	@Autowired
	public ProductTypeFormatter(ProductService prService) {
		this.prService = prService;
	}
    @Override
    public String print(ProductType object, Locale locale) {
    	return object.getName();
    }

    @Override
    public ProductType parse(String text, Locale locale) throws ParseException {
    	ProductType p = this.prService.getProductType(text);
    	if(p==null) {
    		throw new ParseException(text, 0);
    	}else {
    		return p;
    	}
    	/*Collection<ProductType> findPrTypes = this.prService.getAllProductsType();
		for (ProductType type : findPrTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);*/
    }
    
}
