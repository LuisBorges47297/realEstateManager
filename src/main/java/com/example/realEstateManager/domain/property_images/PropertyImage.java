package com.example.realEstateManager.domain.property_images;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PropertyImage {

    private int id;
    private int propertyId;
    private String imageUrl;
    private Timestamp uploadedAt;

    // Construtor vazio
    public PropertyImage() {
    }

    // Construtor completo
    public PropertyImage(int id, int propertyId, String imageUrl, Timestamp uploadedAt) {
        this.id = id;
        this.propertyId = propertyId;
        this.imageUrl = imageUrl;
        this.uploadedAt = uploadedAt;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Timestamp uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    // toString
    @Override
    public String toString() {
        return "PropertyImage{" +
                "id=" + id +
                ", propertyId=" + propertyId +
                ", imageUrl='" + imageUrl + '\'' +
                ", uploadedAt=" + uploadedAt +
                '}';
    }

    // RowMapper
    public static class PropertyImageRowMapper implements RowMapper<PropertyImage> {
        @Override
        public PropertyImage mapRow(ResultSet rs, int rowNum) throws SQLException {
            PropertyImage image = new PropertyImage();
            image.setId(rs.getInt("id"));
            image.setPropertyId(rs.getInt("property_id"));
            image.setImageUrl(rs.getString("image_url"));
            image.setUploadedAt(rs.getTimestamp("uploaded_at"));
            return image;
        }
    }
}

