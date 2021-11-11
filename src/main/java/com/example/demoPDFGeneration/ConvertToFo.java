package com.example.demoPDFGeneration;

import org.apache.fop.apps.FOPException;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class ConvertToFo {

    public static final String RESOURCES_DIR;
    public static final String OUTPUT_DIR;

    static {
        RESOURCES_DIR = "src//main//resources//";
        OUTPUT_DIR = "src//main//resources//output//";
    }

    /**
     * This method will convert the given XML to XSL-FO
     *
     * @throws IOException
     * @throws FOPException
     * @throws TransformerException
     */
    public void convertToFO() throws IOException, FOPException, TransformerException {
        // the XSL FO file
        File xsltFile = new File(RESOURCES_DIR + "//template.xsl");

        /*
         * TransformerFactory factory = TransformerFactory.newInstance();
         * Transformer transformer = factory.newTransformer(new
         * StreamSource("F:\\Temp\\template.xsl"));
         */

        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File(RESOURCES_DIR + "//Employees.xml"));

        // a user agent is needed for transformation
        /* FOUserAgent foUserAgent = fopFactory.newFOUserAgent(); */
        // Setup output
        OutputStream out;

        out = new java.io.FileOutputStream(OUTPUT_DIR + "temp.fo");

        try {
            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Resulting SAX events (the generated FO) must be piped through to
            // FOP
            // Result res = new SAXResult(fop.getDefaultHandler());

            Result res = new StreamResult(out);

            // Start XSLT transformation and FOP processing
            transformer.transform(xmlSource, res);

            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then
            // PDF is created
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
}
