package com.digimenu.main.domain.response;

import com.digimenu.main.domain.dto.PastOrdersResponseDto;

import java.util.List;
import java.util.Objects;

public class PastOrdersResponse {

    private List<PastOrdersResponseDto> pastOrders;

    public List<PastOrdersResponseDto> getPastOrders() {
        return pastOrders;
    }

    public void setPastOrders(List<PastOrdersResponseDto> pastOrders) {
        this.pastOrders = pastOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PastOrdersResponse that = (PastOrdersResponse) o;
        return Objects.equals(pastOrders, that.pastOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pastOrders);
    }

    @Override
    public String toString() {
        return "PastOrderResponse{" +
                "pastOrders=" + pastOrders +
                '}';
    }
}
