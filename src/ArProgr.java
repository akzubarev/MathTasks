import java.util.ArrayList;

public class ArProgr extends Task {

    private int n = 0, k = 0;
    private double a1 = 0, d = 0, an = 0, ak = 0;

    public ArProgr(String type, int subtype) {
        super(type);

        d = generatekoef(6) + 3;
        if (rnd.nextBoolean()) d = -d;

        typify(subtype);
    }

    private void typify(int subtype) {
        if (subtype == -1)
            subtype = (int) generatekoef(6);
        switch (subtype) {
            case 0:
                n = (int) generatekoef(8) + 11;
                a1 = generatekoef(50);
                if (rnd.nextBoolean()) a1 = -a1;
                an = (a1 + (n - 1) * d);

                description = String.format("a_1 = %s;\\: d= %s;\\: a_{%s} = \\:?",
                        setkoefstring(a1), setkoefstring(d), setkoefstring(n));
                answer = an;
                break;
            case 1:
                n = (int) generatekoef(8) + 11;
                an = generatekoef(50);
                if (rnd.nextBoolean()) an = -an;
                a1 = (an - (n - 1) * d);

                description = String.format("a_{%s} = %s;\\: d= %s;\\: a_1 = \\:?",
                        setkoefstring(n), setkoefstring(an), setkoefstring(d));
                answer = a1;
                break;
            case 2:
                k = (int) generatekoef(50);
                ak = generatekoef(50);
                if (rnd.nextBoolean()) ak = -ak;
                n = (int) generatekoef(8) + 11;
                if (k > n)
                    if (rnd.nextBoolean()) n = -n;
                n += k;
                an = (ak + (n - k) * d);

                description = String.format("a_{%s} = %s;\\: d= %s;\\: a_{%s} = \\:?",
                        setkoefstring(k), setkoefstring(ak), setkoefstring(d), setkoefstring(n));
                answer = an;
                break;
            case 3:
                n = (int) generatekoef(8) + 11;
                a1 = generatekoef(50);
                if (rnd.nextBoolean()) a1 = -a1;
                an = (a1 + (n - 1) * d);

                description = String.format("a_1 = %s;\\: a_{%s}= %s;\\: d = \\:?",
                        setkoefstring(a1), setkoefstring(n), setkoefstring(an));
                answer = d;
                break;
            case 4:
                k = (int) generatekoef(50);
                ak = generatekoef(50);
                if (rnd.nextBoolean()) ak = -ak;
                n = (int) generatekoef(8) + 11;
                if (k > n)
                    if (rnd.nextBoolean()) n = -n;
                n += k;
                an = (ak + (n - k) * d);

                description = String.format("a_{%s} = %s;\\: a_{%s}= %s;\\: d = \\:?",
                        setkoefstring(k), setkoefstring(ak), setkoefstring(n), setkoefstring(an));
                answer = d;
                break;
            case 5:
                n = (int) generatekoef(8) + 11;
                a1 = generatekoef(50);
                if (rnd.nextBoolean()) a1 = -a1;
                an = (a1 + (n - 1) * d);

                description = String.format("a_1 = %s;\\: d= %s;\\: a_n= %s;\\: n = \\:?",
                        setkoefstring(a1), setkoefstring(d), setkoefstring(an));
                answer = n;
                break;
            case 6:
                k = (int) generatekoef(50);
                ak = generatekoef(50);
                if (rnd.nextBoolean()) ak = -ak;
                n = (int) generatekoef(8) + 11;
                if (k > n)
                    if (rnd.nextBoolean()) n = -n;
                n += k;
                an = (ak + (n - k) * d);

                description = String.format("a_{%s} = %s;\\: a_n= %s;\\: d= %s;\\: n = \\:?",
                        setkoefstring(k), setkoefstring(ak), setkoefstring(an), setkoefstring(d));
                answer = n;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String Display() {
        return description;
    }

    @Override
    public String GetAnswer() {
        return setkoefstring(answer);
    }

    @Override
    public String GetTitle() {
        return "Арифметическая прогрессия: ";
    }

    public static void main(String[] args) {
        int count = 7;
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < count; i++)
            tasks.add(new ArProgr("int", i));
        Task.toTEX(tasks);
    }

}
