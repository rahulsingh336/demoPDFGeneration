package com.example.demoPDFGeneration;

import org.apache.fop.apps.FOPException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.transform.TransformerException;
import java.io.IOException;

@SpringBootApplication
public class DemoPdfGenerationApplication {

	public static void main(String[] args) throws FOPException, IOException, TransformerException {

		SpringApplication.run(DemoPdfGenerationApplication.class, args);


	}

}
