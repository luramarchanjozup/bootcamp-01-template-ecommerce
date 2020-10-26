package com.zup.mercadolivre.model.products;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.zup.mercadolivre.controller.dto.OpinionListDto;
import com.zup.mercadolivre.controller.dto.ProductDTO;
import com.zup.mercadolivre.controller.dto.QuestionListDto;
import com.zup.mercadolivre.controller.form.CharacteristicsForm;
import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.model.User;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;
    @NotNull
    @Min(0)
    private Integer quantityInStock;
    @NotNull
    @Size(min = 3)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductCharacteristics> characteristics = new HashSet<>();
    @NotNull
    @Size(max = 1000)
    private String description;
    @NotNull
    @ManyToOne
    private Category category;
    @NotNull
    private LocalDateTime registrationTime;
    @NotNull
    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductOpinion> opinions = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductQuestions> questions = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductImages> images = new HashSet<>();

    @Deprecated
    public Product() {
    }

    public Product(String name, Double price, Integer quantityInStock, String description,
            Collection<CharacteristicsForm> characteristics, Category category, User owner) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.description = description;
        this.category = category;
        this.registrationTime = LocalDateTime.now();
        this.owner = owner;

        this.characteristics.addAll(characteristics.stream().map(c -> c.toCharacteristic(this))
        .collect(Collectors.toSet()));

        Assert.isTrue(this.characteristics.size() >= 3, "Every product must have at least 3 or more characteristics.");
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean subQuantityInStock(@Positive Integer amount) {
        Assert.isTrue(amount > 0, "The amount must be greater than 0");
        
        if (amount <= this.quantityInStock) {
            this.quantityInStock -= amount;
            return true;
        }

        return false;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setImages(ProductImages image) {
        this.images.add(image);
    }

    public void setOpinions(ProductOpinion opinions) {
        this.opinions.add(opinions);
    }

    public void setQuestions(ProductQuestions questions) {
        this.questions.add(questions);
    }

    public void checkOwnershipFalse(String email, String message) {
        if (!email.equals(this.owner.getEmail())) {
            throw new BadCredentialsException(message);
        }
    }

    public void checkOwnershipTrue(String email, String message) {
        if (email.equals(this.owner.getEmail())) {
            throw new BadCredentialsException(message);
        }
    }

    public boolean checkOwnership(String email) {
        return email.equals(this.owner.getEmail());
    }

    public ProductDTO toDto() {
        return new ProductDTO(this.id, this.name, this.price, this.quantityInStock, this.opinions.size(), 
            this.characteristics, this.description, this.category, this.registrationTime, this.owner.toDto(), 
            this.images.stream().map(x -> x.getImagePath()).collect(Collectors.toList()), this.opinions);
    }

    public QuestionListDto questionToDto() {
        return new QuestionListDto(this.questions.stream().map(q -> q.toDto()).collect(Collectors.toList()));
    }

    public OpinionListDto opinionsDto() {
        return new OpinionListDto(this.opinions.stream().map(o -> o.toDto()).collect(Collectors.toList()));
    }

    public boolean saveImages(List<MultipartFile> images) {
        String assetsPath = Paths.get("src\\main\\java\\com\\zup\\mercadolivre\\assets").toAbsolutePath().toString();
        File imgFolder = new File(assetsPath + "\\" + "product_" + this.id);
        imgFolder.mkdir();

        for (MultipartFile img : images) {
            try {
                File imgFile = new File(assetsPath + "\\" + "product_" + this.id + "\\" + this.id + "_img"
                        + (this.images.size() + 1) + "." + img.getOriginalFilename().split("\\.")[1]);
                img.transferTo(imgFile);
                this.images.add(new ProductImages(imgFile.toPath().toAbsolutePath().toString(), this));
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Product other = (Product)obj;
        if(name == null) {
            if(other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        return true;
    }
}
