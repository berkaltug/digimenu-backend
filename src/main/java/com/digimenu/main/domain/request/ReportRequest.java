package com.digimenu.main.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Objects;

public class ReportRequest {


    private Timestamp startDate;

    private Timestamp endDate;

    public Timestamp getStartDate() {
        return startDate;
    }

    @JsonSetter("startDate")
    public void setStartDate(String startDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(/* Here put your timestamp format, e.g.*/ "dd-MM-yyyy HH:mm");
        Date parsedDate = dateFormat.parse(startDate);
        this.startDate = new Timestamp(parsedDate.getTime());
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(/* Here put your timestamp format, e.g.*/ "dd-MM-yyyy HH:mm");
        Date parsedDate = dateFormat.parse(endDate);
        this.endDate = new Timestamp(parsedDate.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportRequest that = (ReportRequest) o;
        return Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    @Override
    public String toString() {
        return "ReportRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
