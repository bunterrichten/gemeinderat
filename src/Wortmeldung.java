public class Wortmeldung {
    String protocolTxt;
    String txt;

    public Wortmeldung(String pTxt, String actualTxt) {
        protocolTxt = pTxt;
        txt = actualTxt;
    }

    public String toString() {
        return protocolTxt + txt;
    }
}
