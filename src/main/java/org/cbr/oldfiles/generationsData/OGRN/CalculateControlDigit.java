package org.cbr.oldfiles.generationsData.OGRN;

public class CalculateControlDigit {
    public static char calculateControlDigit(String base) {
        long number = Long.parseLong(base);
        int modValue = base.length();
        int remainder = (int) (number % (modValue - 1));
        return (char) ((remainder % 10) + '0');
    }
}
