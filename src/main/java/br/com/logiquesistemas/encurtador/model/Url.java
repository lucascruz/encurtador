package br.com.logiquesistemas.encurtador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Url {
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String originalUrl;
    private String shortLink;
}
