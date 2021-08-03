package br.com.logiquesistemas.encurtador.repository;

import br.com.logiquesistemas.encurtador.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
    public Url findByShortLink(String shortLink);
}
