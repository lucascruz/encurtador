package br.com.logiquesistemas.encurtador.controller;

import br.com.logiquesistemas.encurtador.model.Url;
import br.com.logiquesistemas.encurtador.model.UrlDTO;
import br.com.logiquesistemas.encurtador.service.UrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value="/url")
@Api(value="API Url Encurta")
public class UrlController
{
    @Autowired
    private UrlService urlService;

    @PostMapping("/generate")
    @ApiOperation(value="Gerador de link encurtado")
    public UrlDTO generateShortLink(@RequestBody UrlDTO urlDto)
    {
        Url urlToReturn = urlService.createShortLink(urlDto);
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

    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws IOException {
        if(StringUtils.isEmpty(shortLink))
        {
            UrlDTO urlDto = new UrlDTO();
            return new ResponseEntity<UrlDTO>(urlDto, HttpStatus.OK);
        }
        Url urlToReturn = urlService.getShortLink(shortLink);
        if(urlToReturn == null) {
            UrlDTO urlDto = new UrlDTO();
            return new ResponseEntity<UrlDTO>(urlDto, HttpStatus.OK);
        }
        response.sendRedirect(urlToReturn.getOriginalUrl());
        return null;
    }
}
