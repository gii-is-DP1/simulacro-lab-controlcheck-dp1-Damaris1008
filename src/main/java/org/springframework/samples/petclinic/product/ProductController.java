package org.springframework.samples.petclinic.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
	
	private static final String VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM = "products/createOrUpdateProductForm";
	
	private ProductService prService;
	
	@Autowired
	public ProductController(ProductService prService) {
		this.prService = prService;
	}
	
	/*@GetMapping(value = "/product/create")
	public String productCreate(ModelMap modelMap) {
		List<ProductType> types = prService.getAllProductsType();
		modelMap.addAttribute("product", new Product());
		modelMap.addAttribute("types", types);
		String vista = "product/create";
		return vista;
	}
    
	@PostMapping(value = "/product/create")
	public String productCreate(@Valid Product product, ModelMap modelMap) {
		String vista = "product/create";
		return "welcome";
	}*/
	
	@GetMapping(value = "/product/create")
	public String initCreationForm(ModelMap model) {
		Product product = new Product();
		model.put("product", product);
		List<ProductType> types = prService.getAllProductsType();
		model.addAttribute("types", types);
		return VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/product/create")
	public String processCreationForm(@Valid Product product, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("product", product);
			List<ProductType> types = prService.getAllProductsType();
			model.addAttribute("types", types);
			return VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM;
		}
		else {
            try{
            	this.prService.save(product);
            }catch(Exception ex){
                result.rejectValue("id", "duplicate", "already exists");
                return VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM;
            }
            return "welcome";
		}
		
	}
	
}

