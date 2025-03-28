package lab.bookings.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Apartment apartment;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fromDate;
    private LocalDate toDate;
    @Min(value = 1)
    @Max(value = 366)
    private Integer numDays = 1;
    @Min(value = 1)
    private Integer numGuests = 1;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    @PrePersist
    private void onPrePersist() {
        toDate = fromDate.plusDays(numDays);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public @NotNull LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(@NotNull LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return fromDate.plusDays(numDays);
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public @Min(value = 1) @Max(value = 366) Integer getNumDays() {
        return numDays;
    }

    public void setNumDays(@Min(value = 1) @Max(value = 366) Integer numDays) {
        this.numDays = numDays;
    }

    public @Min(value = 1) Integer getNumGuests() {
        return numGuests;
    }

    public void setNumGuests(@Min(value = 1) Integer numGuests) {
        this.numGuests = numGuests;
    }

    public @NotEmpty String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty String lastName) {
        this.lastName = lastName;
    }
}

