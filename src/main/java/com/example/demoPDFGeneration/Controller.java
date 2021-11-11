package com.example.demoPDFGeneration;

import org.apache.fop.apps.FOPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class Controller {

    @Autowired
    private ConvertToFo convertToFo;
    @Autowired
    private ConvertToPDF convertToPDF;

    @GetMapping
    public String generate(HttpServletRequest httpRequest) throws FOPException, IOException, TransformerException, URISyntaxException {
        convertToFo.convertToFO();
        convertToPDF.convertToPDF(httpRequest.getRequestURI());
        return "PONG";
    }
}
