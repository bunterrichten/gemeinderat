import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import org.apache.tika.sax.ToTextContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import static com.google.common.base.Charsets.UTF_8;

public class Main {

    public static void main(final String[] args) throws IOException,TikaException {

        ArrayList<Partei> parteien = new ArrayList<Partei>();
        parteien.add(new Partei("FPÖ", "blau"));
        parteien.add(new Partei("SPÖ", "rot"));
        parteien.add(new Partei("ÖVP", "schwarz"));
        parteien.add(new Partei("GRÜNE", "grün"));
        parteien.add(new Partei("MFG", "lila"));
        parteien.add(new Partei("NEOS", "pink"));


        // create Politiker list
        ArrayList<Politiker> politiker = new ArrayList<Politiker>();
        politiker.add(new Politiker("Christoph Barth", Partei.getParteiByName(parteien, ""), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Peter Franzmayr", Partei.getParteiByName(parteien, "GRÜNE"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Ralf Drack", Partei.getParteiByName(parteien, "GRÜNE"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Johann Reindl-Schwaighofer", Partei.getParteiByName(parteien, "SPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Andreas Rabl", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat, Bürgermeister, Stadtsenat"}));
        politiker.add(new Politiker("Gerhard Kroiß", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat", "Vizebürgermeister", "Stadtsenat"}));
        politiker.add(new Politiker("Christa Raggl-Mühlberger", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat", "Vizebürgermeister", "Stadtsenat"}));
        politiker.add(new Politiker("Ralph Schäfer", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat", "Fraktionsvorsitz", "Stadtsenat"}));
        politiker.add(new Politiker("Stefan Ganzert", Partei.getParteiByName(parteien, "SPÖ"), new String[]{"Gemeinderat", "Stadtsenat"}));
        politiker.add(new Politiker("Klaus Schinninger", Partei.getParteiByName(parteien, "SPÖ"), new String[]{"Gemeinderat", "Vizebürgermeister", "Stadtsenat"}));
        politiker.add(new Politiker("Martin Oberndorfer", Partei.getParteiByName(parteien, "ÖVP"), new String[]{"Gemeinderat", "Stadtsenat"}));
        politiker.add(new Politiker("Thomas Rammertorfer", Partei.getParteiByName(parteien, "GRÜNE"), new String[]{"Gemeinderat", "Stadtsenat"}));
        politiker.add(new Politiker("Miriam Faber", Partei.getParteiByName(parteien, "GRÜNE"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Alessandro Schatzmann", Partei.getParteiByName(parteien, "GRÜNE"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Walter Teubl", Partei.getParteiByName(parteien, "GRÜNE"), new String[]{"Gemeinderat", "Fraktionsvorsitz"}));
        politiker.add(new Politiker("Gerhard Bruckner", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Olivera Stojanovic", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Ingo Spindler", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Christoph Angelo Rigotti", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Carmen Pühringer", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Christiane Kroiß", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Gunter Haydinger", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Fabian Bauer", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Ronald Schiefermayr", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Sandra Wohlschlager", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Thorsten Aspetzberger", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Silke Lackner", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Anna Maria Wippl", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Paul Hammerl", Partei.getParteiByName(parteien, "FPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Christian Kittenbaumer", Partei.getParteiByName(parteien, "SPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Bernhard Humer", Partei.getParteiByName(parteien, "SPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Hannah Stögermüller", Partei.getParteiByName(parteien, "SPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Gloria-Maria Umlauf", Partei.getParteiByName(parteien, "SPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Karl Schönberger", Partei.getParteiByName(parteien, "SPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Laurien Scheinecker", Partei.getParteiByName(parteien, "SPÖ"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Silvia Huber", Partei.getParteiByName(parteien, "SPÖ"), new String[]{"Gemeinderat", "Fraktionsvorsitz"}));
        politiker.add(new Politiker("Andreas Weidinger", Partei.getParteiByName(parteien, "ÖVP"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Markus Wiesinger", Partei.getParteiByName(parteien, "ÖVP"), new String[]{"Gemeinderat", "FFraktionsvorsitz"}));
        politiker.add(new Politiker("Ludwig Vogl", Partei.getParteiByName(parteien, "ÖVP"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Birgit Ebetshuber", Partei.getParteiByName(parteien, "ÖVP"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Markus Hufnagl", Partei.getParteiByName(parteien, "NEOS"), new String[]{"Gemeinderat"}));
        politiker.add(new Politiker("Jörg Wehofsich", Partei.getParteiByName(parteien, "MFG"), new String[]{"Gemeinderat"}));

        //String srcPath = "protocolSrc/verhandlungsschrift07032022.pdf";
        //String outputPath = "protocolTarget/output.html";

        String srcPath = "protocolSrc/8._Gemeinderatssitzung_20220704_Protokoll.pdf";
        //String srcPath = "protocolSrc/7._Gemeinderatssitzung_20220607_Protokoll.pdf";
        //String srcPath = "protocolSrc/6._Gemeinderatssitzung_20220502_Protokoll.pdf";
        //String srcPath = "protocolSrc/5._Gemeinderatssitzung_20220404_Protokoll.pdf";
            //String srcPath = "protocolSrc/2._Gemeinderatssitzung_20211220_Protokoll.pdf";
        //String srcPath = "protocolSrc/3._Gemeinderatssitzung_20220131_Protokoll.pdf";
        //String srcPath = "protocolSrc/2._Gemeinderatssitzung_20211220_Protokoll.pdf";
        //String srcPath = "protocolSrc/1._Gemeinderatssitzung_20211108_Protokoll.pdf";
        String outputPath = "protocolTarget/output2.html";


        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File(srcPath));
        ParseContext pcontext = new ParseContext();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ToTextContentHandler toTextContentHandler= new ToTextContentHandler(byteArrayOutputStream, "UTF-8");

        try {
            String htmlContent = parseBodyToHTML(srcPath);
            Document doc = Jsoup.parse(htmlContent);
            ConversionTools.removeEmpty(doc);


            Element page1 = ConversionTools.getPageByNumber(doc, 0);
            System.out.println(page1);

            SitzungsDaten sd = ConversionTools.getSitzungsDaten(page1.toString());
            System.out.println(sd);

            String anwesende = ConversionTools.getAnwesende(doc);
            System.out.println(anwesende);

            Anwesenheit anw = new Anwesenheit();
            System.out.println("-----");
            ArrayList<String[]>[] res = anw.getAnwesenheit(anwesende);
            System.out.println("[" + res[0].size() + "]");
            for (String[] polName : res[0]) {
                System.out.println("Anwesend: " + Anwesenheit.getPolName(polName));
            }
            System.out.println("___");
            System.out.println("[" + res[1].size() + "]");
            for (String[] polName : res[1]) {
                System.out.println("Entschuldigt: " + Anwesenheit.getPolName(polName));
            }
            System.out.println("[" + res[2].size() + "]");
            for (String[] polName : res[2]) {
                System.out.println("Rest: " + Anwesenheit.getPolName(polName));
            }
            //System.out.println(res[1]);


            TOP[] tops = ConversionTools.getTOPs(doc);

            UnicodeWrite.writeUnicodeJava11(outputPath, parseBodyToHTML(srcPath));
        }
        catch (Exception e) {
            e.printStackTrace();
        }





//        Path logFile = Paths.get(outputPath);
//        try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8)) {
//            writer.write("Äther");
//            // ...
//        }

//        FileOutputStream fos = new FileOutputStream(outputPath, true);
//        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
//        BufferedWriter writer = new BufferedWriter(osw);
//        writer.write(handler.toString());
//
//        writer.close();

        // Metadata of the PDF

        /*
        //getting metadata of the document
        System.out.println("Metadata of the PDF:");
        String[] metadataNames = metadata.names();

        for(String name : metadataNames) {
            System.out.println(name+ " : " + metadata.get(name));
        }*/
    }


    public static String parseBodyToHTML(String inputPath)
            throws IOException, SAXException, TikaException {

        ContentHandler handler = new ToXMLContentHandler();

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (FileInputStream stream = new FileInputStream(inputPath)) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }
}