package net.shoul.bookManager.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity

@Data
@ToString(exclude = "bookCategory")
@EqualsAndHashCode(exclude = "bookCategory")
@RequiredArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private @NonNull String name;
    private @NonNull String author;
    private @NonNull String language;
    private @NonNull String isbn;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss z")
    private @NonNull Date releaseDate;
    private @NonNull String publisher;
    private @NonNull Long length;
    private @NonNull Double Weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_category_id")
    private @NonNull BookCategory bookCategory;

}
