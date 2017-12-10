package statistics.matcher;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import statistics.Player;

public class Or implements Matcher {

    private Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) throws NoSuchMethodException {
        for (Matcher matcher : matchers) {
            if (matcher.matches(p)) {
                return true;
            }

        }
        return false;
    }
}
