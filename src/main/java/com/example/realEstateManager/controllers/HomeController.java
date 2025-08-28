package com.example.realEstateManager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.realEstateManager.domain.properties.Property;
import com.example.realEstateManager.domain.properties.Property.PropertyRowMapper;
import com.example.realEstateManager.domain.user.User;

@Controller
public class HomeController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @Autowired
    public HomeController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/")
    public String home() {
        return "index"; // nome do template index.html
    }

    @GetMapping("/index")
    public String index() {
        return "index"; // Vai procurar por src/main/resources/templates/index.html
    }

    @GetMapping("/add-property")
    public String add_property() {
        return "add-property"; 
    }

    @PostMapping("/add-property")
    public String addProperty(@ModelAttribute Property property,
                               @RequestParam("images") List<MultipartFile> images) throws Exception {

        // 1️⃣ Obter email do utilizador autenticado
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // 2️⃣ Buscar o user_id
        Integer userId = jdbcTemplate.queryForObject(
            "SELECT id FROM users WHERE email = ?",
            Integer.class,
            email
        );

        // 3️⃣ Inserir na tabela `properties`
        String insertPropertySql = """
            INSERT INTO properties (user_id, title, description, price, location, property_type, bedrooms, bathrooms, area)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id
        """;

        Integer propertyId = jdbcTemplate.queryForObject(insertPropertySql, Integer.class,
            userId,
            property.getTitle(),
            property.getDescription(),
            property.getPrice(),
            property.getLocation(),
            property.getPropertyType(),
            property.getBedrooms(),
            property.getBathrooms(),
            property.getArea()
        );

        // 4️⃣ Guardar imagens (supondo que vais salvar só o nome/URL)
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                String filePath = "uploads/" + fileName;
                image.transferTo(new java.io.File(filePath));

                jdbcTemplate.update(
                    "INSERT INTO property_images (property_id, image_url) VALUES (?, ?)",
                    propertyId, filePath
                );
            }
        }

        return "redirect:/properties"; // volta à lista de propriedades
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), encodedPassword);

        return "redirect:/login";
    }

    @GetMapping("/properties/filter")
    public String filtrarPropriedades(
        @RequestParam(required = false) String location,
        @RequestParam(required = false) Integer bedrooms,
        @RequestParam(required = false) Double maxPrice,
        Model model) {

        StringBuilder sql = new StringBuilder("SELECT * FROM properties WHERE 1=1");
        

        if (location != null && !location.isEmpty()) { 
            sql.append(" AND location ILIKE '%").append(location).append("%'");
        }
        if (bedrooms != null) {
            sql.append(" AND bedrooms >= ").append(bedrooms);
        }
        if (maxPrice != null) {
            sql.append(" AND price <= ").append(maxPrice);
        }

        List<Property> results = jdbcTemplate.query(sql.toString(), new PropertyRowMapper());

        if (results.isEmpty()) {
            model.addAttribute("message", "Nenhum imóvel encontrado com os critérios informados.");
            return "index";
        }

        model.addAttribute("properties", results);
        return "list"; // ex: página com a lista de imóveis filtrada
    }
}

