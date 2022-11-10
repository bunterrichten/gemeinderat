import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ConversionTools {
    public static Element getPageByNumber(Document doc, int pageNr) {

        Elements pages = doc.getElementsByClass("page");
        Element thisPage = pages.get(pageNr);

        Elements children = thisPage.select("*");
        for (Element one : children) {
            if ((!one.hasText() || one.text().matches("[0-9]+")) && one.isBlock())
                one.remove();
        }
        return thisPage;
    }

    public static SitzungsDaten getSitzungsDaten(String html) {
        Document doc = Jsoup.parse(html);

        String beforeDate = "über die am";
        String sitzungsBeginn = "Beginn der Sitzung:";
        String sitzungsEnde = "Ende der Sitzung:";
        String uhr = "Uhr";

        Elements children = doc.select("p");
        String content;
        String datum = "0000-01-01";
        String sitzungsName = "default";
        String beginnZeit = "00:00";
        String endZeit = "00:00";

        boolean dateFound = false;
        boolean startTimeFound = false;
        boolean endTimeFound = false;
        boolean nameFound = false;
        boolean nextOneData = false;

        int pos;
        for (Element one : children) {
            content = one.text();

            if (nextOneData) {
                // in this <p> there should be the rest of the data

                // find start time
                pos = content.indexOf(sitzungsBeginn);

                if (pos != -1) {
                    // found it
                    sitzungsName = content.substring(0, pos).trim();

                    int pos2 = content.indexOf(uhr, pos);
                    if (pos2 != -1) {
                        //System.out.println("pos: " + pos + ".." + pos2);
                        beginnZeit = content.substring(pos + sitzungsBeginn.length(), (pos2)).replace(".", ":").trim();
                    }
                }

                // find end time
                pos = content.indexOf(sitzungsEnde);

                if (pos != -1) {
                    // found it

                    int pos2 = content.indexOf(uhr, pos);
                    if (pos2 != -1) {
                        //System.out.println("pos: " + pos + ".." + pos2);
                        endZeit = content.substring(pos + sitzungsEnde.length(), (pos2)).replace(".", ":").trim();
                    }
                }

                nextOneData = false;
            }

            // date search until found
            if (! dateFound) {
                pos = content.indexOf(beforeDate);
                if (pos != -1) {
                    // found the correct line
                    // now search for date here
                    pos = content.indexOf("dem") + 3;
                    if (pos != -1) {
                        // found start of date
                        String[] words = content.substring(pos).trim().split(" ");
                        int monat = monatZuZahl(words[1]);
                        int tag = Integer.parseInt(words[0].substring(0, words[0].length() - 1));
                        if (monat != -1) {
                            // Monat korrekt gefunden
                            datum = words[2];
                            if (monat < 10)
                                datum += "-0" + monat;
                            else
                                datum += "-" + monat;

                            if (tag < 10)
                                datum += "-0" + tag;
                            else
                                datum += "-" + tag;

                            nextOneData = true;
                        }
                    }
                }
                // end of date search


            }


        }
        return new SitzungsDaten(sitzungsName, datum, beginnZeit, endZeit, "ANWESEND!");
    }

    public static Document removeEmpty(Document doc) {
        Elements all = doc.select("*");
        for (Element one : all) {
            if ((!one.hasText() || one.text().matches("[0-9]+")) && one.isBlock())
                one.remove();
        }
        return doc;
    }

    public static String getAnwesende(Document doc) {

        Elements all = doc.select("p");
        boolean useThis = false;

        Elements onlyAnwesende = new Elements();

        for (Element one : all) {
            if ((!one.hasText() || one.text().matches("[0-9]+")) && one.isBlock())
                one.remove();
            else if (one.text().trim().equalsIgnoreCase("a n w e s e n d e")) {
                //System.out.println("---------ANWESENHEIT---------------");
                useThis = true;
            }
            else if (one.text().indexOf("Verlauf der Sitzung") != -1) {
                //System.out.println("---------------------------------");
                useThis = false;
            }
            else if (useThis) {
                //System.out.println(one.html());
                onlyAnwesende.add(one);
            }
        }
        return onlyAnwesende.toString();
    }

    public static TOP[] getTOPs(Document doc) {
        Elements all = doc.select("p");

        int currentTOP = 1;
        boolean topOpen = false;
        String abstimmungsText = "";

        System.out.println("GET TOPS");

        for (Element one : all) {
            if (((one.text().matches("^[0-9]+\\.+\\)+.*"))) && one.isBlock()) {
                //start of new TOP, perhaps

                if (abstimmungsText.length() > 0)
                    System.out.println("Abstimmungstext: " + abstimmungsText);

                System.out.println("Start of TOP " + currentTOP + ": " + one.text());
                topOpen = true;
                currentTOP++;
                abstimmungsText = "";
            }
            else if (topOpen && (one.text().toLowerCase().matches(".*einstimmig angenommen.*")) && one.isBlock()) {
                System.out.println("Einstimmig angenommen");
                topOpen = false;
            }
            else if (topOpen && (one.text().toLowerCase().matches(".*(ja-stimmen|nein-stimmen|stimmenthaltun).*")) && one.isBlock()) {
                //System.out.println("FOUND");
                abstimmungsText += one.text();
            }
            else if ((!one.hasText() || one.text().matches("[0-9]+")) && one.isBlock()) {
                one.remove();
            }

            // TODO: "Dringlichkeitsantrag" mit Abstimmung
            /*else if (one.text().indexOf("Verlauf der Sitzung") != -1) {
                //System.out.println("---------------------------------");
                useThis = false;
            }
            else if (useThis) {
                //System.out.println(one.html());
                onlyAnwesende.add(one);
            }*/
        }
        if (abstimmungsText.length() > 0) {
            // add one last abstimmungsErgebnis
            System.out.println("Abstimmungstext: " + abstimmungsText);
        }
        return null;
    }
    public static int monatZuZahl(String monatsName) {
        switch (monatsName) {
            case "Jänner":
                return 1;
            case "Februar":
                return 2;
            case "März":
                return 3;
            case "April":
                return 4;
            case "Mai":
                return 5;
            case "Juni":
                return 6;
            case "Juli":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "Oktober":
                return 10;
            case "November":
                return 11;
            case "Dezember":
                return 12;
            default:
                return -1;
        }
    }



}
