package br.com.logiquesistemas.encurtador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UrlDTO {
    private String url;
    private String originalUrl;
    private String shortLink;
}
