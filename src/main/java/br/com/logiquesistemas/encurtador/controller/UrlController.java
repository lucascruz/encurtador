package br.com.logiquesistemas.encurtador.controller;

import br.com.logiquesistemas.encurtador.model.Url;
import br.com.logiquesistemas.encurtador.model.UrlDTO;
import br.com.logiquesistemas.encurtador.service.UrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
@Api(value="API Url Encurta")
public class UrlController
{
    @Autowired
    private UrlService urlService;

    @PostMapping("/generate")
    @ApiOperation(value="Gerador de link encurtado")
    public UrlDTO generateShortLink(@RequestBody UrlDTO urlDto)
    {
        Url urlToReturn = urlService.createLink(urlDto);
        if(urlToReturn != null)
        {
            UrlDTO urlDtoSet = new UrlDTO();
            urlDtoSet.setOriginalUrl(urlToReturn.getOriginalUrl());
            urlDtoSet.setShortLink(urlToReturn.getShortLink());
            return urlDto;
        }
        return new UrlDTO();

    }

    @GetMapping ("/{shortLink}")
    @ApiOperation(value="Redireciona para o link encurtado")
    public UrlDTO redirectToOriginalUrl(@PathVariable String shortLink){

        if(StringUtils.isEmpty(shortLink))
        {
            return new UrlDTO();
        }
        Url urlToReturn = urlService.getLink(shortLink);
        if(urlToReturn == null) {
            return new UrlDTO();
        }
        return null;
    }
}
