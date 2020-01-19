package com.digimenu.main.domain.response;

import com.digimenu.main.domain.projection.ReportProjection;

import java.util.List;
import java.util.Objects;

public class ReportResponse {

    List<ReportProjection> reportList;


    public List<ReportProjection> getReportList() {
        return reportList;
    }

    public void setReportList(List<ReportProjection> reportList) {
        this.reportList = reportList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportResponse that = (ReportResponse) o;
        return Objects.equals(reportList, that.reportList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportList);
    }

    @Override
    public String toString() {
        return "ReportResponse{" +
                "reportList=" + reportList +
                '}';
    }
}
