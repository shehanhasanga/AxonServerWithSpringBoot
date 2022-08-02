package com.example.productService.core.data;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "productlookup")
public class ProductLookupEntity implements Serializable {
    private static final long serialVersionUID = 42L;
    @Id
    private String productId;
    @Column(unique = true)
    private String title;
}
