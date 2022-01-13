package org.springframework.samples.petclinic.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_types")
@Getter 
@Setter
public class ProductType extends BaseEntity{
	
	@NotEmpty
	@Size(min=3, max=50)
	@Column(unique=true)
    String name;
}
