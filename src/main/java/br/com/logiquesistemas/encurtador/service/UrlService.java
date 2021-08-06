package br.com.logiquesistemas.encurtador.service;

import br.com.logiquesistemas.encurtador.model.Url;
import br.com.logiquesistemas.encurtador.model.UrlDTO;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {
    public Url persistShortLink(Url url);
    public Url getShortLink(String url);
    public Url createShortLink(UrlDTO urlDto);
    public  void  deleteShortLink(Url url);
}
