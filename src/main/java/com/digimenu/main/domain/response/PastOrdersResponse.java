package com.digimenu.main.domain.response;

import com.digimenu.main.domain.projection.PastOrdersProjection;

import java.util.List;
import java.util.Objects;

public class PastOrdersResponse {

    List<PastOrdersProjection> pastOders;

    public List<PastOrdersProjection> getPastOders() {
        return pastOders;
    }

    public void setPastOders(List<PastOrdersProjection> pastOders) {
        this.pastOders = pastOders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PastOrdersResponse that = (PastOrdersResponse) o;
        return Objects.equals(pastOders, that.pastOders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pastOders);
    }

    @Override
    public String toString() {
        return "PastOrdersResponse{" +
                "pastOders=" + pastOders +
                '}';
    }
}
