package monopoly.model;

public enum Strassentyp {

    STRAssE(0),
    BAHNHOF(1),
    WERK(2),
    EREIGNISFELD(3),
    GEMEINSCHAFTSFELD(4),
    LOS(5),
    GEFAeNGNIS(6),
    PARKEN(7),
    GEHE_INS_GEFAeNGNIS(8),
    EINKOMENSTEUER(9),
    ZUSATZSTEUER(10);

    private final int value;

    Strassentyp(int value) {
        this.value = value;
    }

    public static Strassentyp getStrassentyp(int wert) {
        for (Strassentyp s : Strassentyp.values()) {
            if (s.getValue() == wert)
                return s;
        }

        return null;
    }

    public static int getint(Strassentyp typ) {
        if (typ.equals(Strassentyp.STRAssE)) {
            return 0;
        } else if (typ.equals(Strassentyp.BAHNHOF)) {
            return 1;
        } else if (typ.equals(Strassentyp.EREIGNISFELD)) {
            return 3;
        } else if (typ.equals(Strassentyp.GEHE_INS_GEFAeNGNIS)) {
            return 8;
        } else if (typ.equals(Strassentyp.GEFAeNGNIS)) {
            return 6;
        } else if (typ.equals(Strassentyp.LOS)) {
            return 5;
        } else if (typ.equals(Strassentyp.PARKEN)) {
            return 7;
        } else if (typ.equals(Strassentyp.GEMEINSCHAFTSFELD)) {
            return 4;
        } else if (typ.equals(Strassentyp.WERK)) {
            return 2;
        } else if (typ.equals(Strassentyp.EINKOMENSTEUER)) {
            return 9;
        } else {
            return 10;
        }
    }

    public int getValue() {
        return value;
    }

}
