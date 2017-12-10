package statistics.matcher;

import statistics.Player;

import java.lang.reflect.Method;

public class Not implements Matcher {

    private HasAtLeast matchers;

    public Not(HasAtLeast matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        return !matchers.matches(p);

    }
}
