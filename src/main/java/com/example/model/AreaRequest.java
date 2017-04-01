package com.example.model;

public class AreaRequest {
    private String type;
    private Integer radius;
    private Integer width;
    private Integer height;

    public AreaRequest() {
        this.type = null;
        this.radius = null;
        this.width = null;
        this.height = null;
    }

    public AreaRequest(String type,
                       Integer radius,
                       Integer width,
                       Integer height) {
        this.type = type;
        this.radius = radius;
        this.width = width;
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public Integer getRadius() {
        return radius;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AreaRequest that = (AreaRequest) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (radius != null ? !radius.equals(that.radius) : that.radius != null) return false;
        if (width != null ? !width.equals(that.width) : that.width != null) return false;
        return height != null ? height.equals(that.height) : that.height == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (radius != null ? radius.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        return result;
    }
}
