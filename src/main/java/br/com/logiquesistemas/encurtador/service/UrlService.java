package br.com.logiquesistemas.encurtador.service;

import br.com.logiquesistemas.encurtador.model.Url;
import br.com.logiquesistemas.encurtador.model.UrlDTO;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {
    public Url persistLink(Url url);
    public Url getLink(String url);
    public Url createLink(UrlDTO urlDto);
    public  void  deleteLink(Url url);
}
