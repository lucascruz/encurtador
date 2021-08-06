package br.com.logiquesistemas.encurtador.service;

import br.com.logiquesistemas.encurtador.model.Url;
import br.com.logiquesistemas.encurtador.model.UrlDTO;
import br.com.logiquesistemas.encurtador.repository.UrlRepository;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class UrlServiceImpl implements UrlService{
    @Autowired
    private UrlRepository urlRepository;


    @Override
    public Url createShortLink(UrlDTO urlDto) {
        if(StringUtils.isNotEmpty(urlDto.getUrl()))
        {
            String encodedUrl = encodeUrl(urlDto.getUrl());
            Url urlPersist = new Url();
            urlPersist.setOriginalUrl(urlDto.getUrl());
            urlPersist.setShortLink(encodedUrl);
            return persistShortLink(urlPersist);
        }
        return null;
    }


    private String encodeUrl(String url)
    {
        String encodedUrl;
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return  encodedUrl;
    }

    @Override
    public Url persistShortLink(Url url) {
        return urlRepository.save(url);
    }

    @Override
    public Url getShortLink(String url) {
        return urlRepository.findByShortLink(url);
    }

    @Override
    public void deleteShortLink(Url url) {
        urlRepository.delete(url);
    }

}
