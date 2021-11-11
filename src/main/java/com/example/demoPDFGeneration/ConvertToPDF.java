package com.example.demoPDFGeneration;

import org.apache.fop.apps.*;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class ConvertToPDF {

    public static final String RESOURCES_DIR;
    public static final String OUTPUT_DIR;

    static {
        RESOURCES_DIR = "src//main//resources//";
        OUTPUT_DIR = "src//main//resources//output//";
    }

    /**
     * Method that will convert the given XML to PDF
     *
     * @throws IOException
     * @throws FOPException
     * @throws TransformerException
     * @param uri
     */
    public void convertToPDF(String uri) throws IOException, FOPException, TransformerException, URISyntaxException {
        // the XSL FO file
        File xsltFile = new File(RESOURCES_DIR + "//template.xsl");
        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File(RESOURCES_DIR + "//Employees.xml"));
        // create an instance of fop factory
        FopFactory fopFactory = FopFactory.newInstance(new File(uri).toURI());
        // a user agent is needed for transformation
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        // Setup output
        OutputStream out;
        out = new java.io.FileOutputStream(OUTPUT_DIR + "//employee.pdf");

        try {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Resulting SAX events (the generated FO) must be piped through to
            // FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then
            // PDF is created
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
}
