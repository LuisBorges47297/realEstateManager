package com.example.realEstateManager.domain.inquiries;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Inquiry {

    private int id;
    private int propertyId;
    private String name;
    private String email;
    private String message;
    private Timestamp sentAt;

    // Construtor vazio
    public Inquiry() {
    }

    // Construtor completo
    public Inquiry(int id, int propertyId, String name, String email, String message, Timestamp sentAt) {
        this.id = id;
        this.propertyId = propertyId;
        this.name = name;
        this.email = email;
        this.message = message;
        this.sentAt = sentAt;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    // toString
    @Override
    public String toString() {
        return "Inquiry{" +
                "id=" + id +
                ", propertyId=" + propertyId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }

    // RowMapper interno
    public static class InquiryRowMapper implements RowMapper<Inquiry> {
        @Override
        public Inquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
            Inquiry inquiry = new Inquiry();
            inquiry.setId(rs.getInt("id"));
            inquiry.setPropertyId(rs.getInt("property_id"));
            inquiry.setName(rs.getString("name"));
            inquiry.setEmail(rs.getString("email"));
            inquiry.setMessage(rs.getString("message"));
            inquiry.setSentAt(rs.getTimestamp("sent_at"));
            return inquiry;
        }
    }
}