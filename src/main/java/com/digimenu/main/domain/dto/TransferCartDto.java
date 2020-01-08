package com.digimenu.main.domain.dto;

import java.util.Objects;

public class TransferCartDto {
    private Long id;
    private Integer source;
    private Integer target;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferCartDto)) return false;
        TransferCartDto that = (TransferCartDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(source, that.source) &&
                Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, target);
    }

    @Override
    public String toString() {
        return "TransferCartDto{" +
                "id=" + id +
                ", source=" + source +
                ", target=" + target +
                '}';
    }
}
