public class SitzungsDaten {
    public String name;
    public String datum;
    public String startZeit;
    public String endZeit;
    public int dauer;
    public String anwesende;

    public SitzungsDaten(String Iname, String Idatum, String IstartZeit, String IendZeit, String Ianwesende) {
        name = Iname;
        datum = Idatum;
        startZeit = IstartZeit;
        endZeit = IendZeit;
        anwesende = Ianwesende;
        dauer = getDauer(startZeit, endZeit);
    }

    public String toString() {
        return "\nName: " + name +
                "\nDatum: " + datum +
                "\nStart: " + startZeit +
                "\nEnde: " + endZeit +
                "\nDauer: " + dauer +
                "\nAnwesend: " + anwesende;

    }

    public static int getDauer(String startTime, String endTime) {
        String[] parts = startTime.split(":");
        int startHrs = Integer.parseInt(parts[0]);
        int startMins = Integer.parseInt(parts[1]);

        parts = endTime.split(":");
        int endHrs = Integer.parseInt(parts[0]);
        int endMins = Integer.parseInt(parts[1]);

        return endHrs*60 + endMins - (startHrs*60 + startMins);
    }

}
