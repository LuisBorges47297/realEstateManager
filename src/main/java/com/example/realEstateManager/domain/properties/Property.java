package com.example.realEstateManager.domain.properties;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Property {

    private int id;
    private int userId;
    private String title;
    private String description;
    private BigDecimal price;
    private String location;
    private String propertyType;
    private int bedrooms;
    private int bathrooms;
    private int area;
    private Timestamp createdAt;

    // Construtor vazio (necessário para JavaBeans)
    public Property() {
    }

    // Construtor completo
    public Property(int id, int userId, String title, String description, BigDecimal price, String location,
                    String propertyType, int bedrooms, int bathrooms, int area, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
        this.propertyType = propertyType;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.area = area;
        this.createdAt = createdAt;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // toString
    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", area=" + area +
                ", createdAt=" + createdAt +
                '}';
    }

    // RowMapper para JDBC
    public static class PropertyRowMapper implements RowMapper<Property> {
        @Override
        public Property mapRow(ResultSet rs, int rowNum) throws SQLException {
            Property property = new Property();
            property.setId(rs.getInt("id"));
            property.setUserId(rs.getInt("user_id"));
            property.setTitle(rs.getString("title"));
            property.setDescription(rs.getString("description"));
            property.setPrice(rs.getBigDecimal("price"));
            property.setLocation(rs.getString("location"));
            property.setPropertyType(rs.getString("property_type"));
            property.setBedrooms(rs.getInt("bedrooms"));
            property.setBathrooms(rs.getInt("bathrooms"));
            property.setArea(rs.getInt("area"));
            property.setCreatedAt(rs.getTimestamp("created_at"));
            return property;
        }
    }
}