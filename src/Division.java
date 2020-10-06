import java.util.ArrayList;
import java.util.Random;

public class Division extends Task {

    private String upper, lower,
            infoup, infolow, upnickname, botnickname;
    int q, t, c;

    public static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public Division(int uptype, int bottype, int[] koefs, int[] koefs1) {
        super("int");
        do
            q = rnd.nextBoolean() ? 1 + rnd.nextInt(9) : -1 - rnd.nextInt(9);
        while (koefs[2] != 0 && koefs[2] != 1 && gcd(koefs[2], q) != 1);
        t = rnd.nextBoolean() ? 1 + rnd.nextInt(9) : -1 - rnd.nextInt(9);
        c = rnd.nextBoolean() ? 1 + rnd.nextInt(9) : -1 - rnd.nextInt(9);

        upper = typi(uptype, koefs[0], koefs[1], koefs[2], koefs[3], koefs[4]);
        lower = typi(bottype, koefs1[0], koefs1[1], koefs1[2], koefs1[3], koefs1[4]);

        infoup = typify(uptype, koefs[0], koefs[1], koefs[2], koefs[3], koefs[4]);
        infolow = typify(bottype, koefs1[0], koefs1[1], koefs1[2], koefs1[3], koefs1[4]);

        upnickname = setNickname(uptype);
        botnickname = setNickname(bottype);
        form();
        CalculateAnswer(uptype, bottype, koefs, koefs1);
    }

    String typify(int type, int p, int r, int a, int s, int b) {//prasb

        switch (type) {
            case 0://p
                return String.format("$p=%d $|", p);

            case 1://px
                return String.format("$p=%d |$", p);

            case 2://p(rax+rq)
                return String.format("$p=%d ;\\:r=%d ;\\:a=%d |$", p, r, a);

            case 3://px^2
                return String.format("$p=%d |$", p);

            case 4://p(rax^2+rq)
                return String.format("$p=%d;\\: r=%d;\\: a=%d;\\: q=%d |$", p, r, a, q);

            case 5://px(rax+rq)
                return String.format("$p=%d ;\\: r=%d;\\:a=%d;\\: q=%d |$", p, r, a, q);

            case 6://p(rax^2+rqx)
                return String.format("$p=%d;\\:r=%d;\\: a=%d;\\: q=%d |$", p, r, a, q);

            case 7://p(rax+rq)(sax+sq)
                return String.format("$p=%d\\: r=%d;\\:a=%d;\\: s=%d;\\: q=%d |$", p, r, a, s, q);

            case 8://p(ra^2x^2+2raqx+rq^2)
                return String.format("$p=%d;\\:r=%d;\\:a=%d;\\:q=%d |$", p, r, a, q);

            case 9://p(rax-rq)(sax+sq)
                return String.format("$p=%d\\:r=%d;\\: a=%d;\\:s=%d ;\\:q=%d |$", p, r, a, s, q);


            case 10://p(rax ^ 2 - rq ^ 2)
                return String.format("$p=%d\\::r=%d;\\:a=%d;\\:q=%d |$", p, r, a, q);

            case 11://p(rax+rq)(sbx+st)
                return String.format("$p=%d\\:r=%d;\\:a=%d;\\:s=%d;\\:q=%d;\\:b = %d |$", p, r, a, s, q, b);


            case 12://p(rabx^2+r(at+bq)x+rqt)
                return String.format("$p=%d\\: r=%d;\\:a=%d;\\: t=%d;\\:b=%d;\\: q=%d |$", p, r, a, t, b, q);

            case 13://p(rax^2+rbx+rc)(D<0)
                return String.format("p=%d\\:r=%d;\\: a=%d;\\:b=%d;\\:c=%d: |$", p, r, a, b, c);

            default:
                throw new IllegalArgumentException();
        }
    }

    String typi(int type, int p, int r, int a, int s, int b) {//praq
        switch (type) {
            case 0://p
                return new Polynomial(p).show();

            case 1://px
                return new Polynomial(p, 0).show();

            case 2://p(rax+rq)
                return ((p != 1) ? ((p != -1) ? new Polynomial(p).show() + "(" : "-(") : "") +
                        new Polynomial(r * a, r * q).show() + ((p != 1) ? ")" : "");

            case 3://px^2
                return new Polynomial(p, 0, 0).show();

            case 4://p(rax^2+rq)
                return ((p != 1) ? ((p != -1) ? new Polynomial(p).show() + "(" : "-(") : "") +
                        new Polynomial(r * a, 0, r * q).show() + ((p != 1) ? ")" : "");

            case 5://px(rax+rq)
                return new Polynomial(p, 0).show() + "(" +
                        new Polynomial(r * a, r * q).show() + ")";

            case 6://p(rax^2+rqx)
                return ((p != 1) ? ((p != -1) ? new Polynomial(p).show() + "(" : "-(") : "") +
                        new Polynomial(r * a, r * q, 0).show() + ((p != 1) ? ")" : "");

            case 7://p(rax+rq)(sax+sq)
                return ((p != 1) ? ((p != -1) ? new Polynomial(p).show() : "-") : "") + "(" +
                        new Polynomial(r * a, r * q).show() + ")(" +
                        new Polynomial(s * a, s * q).show() + ")";

            case 8://p(ra^2x^2+2raqx+rq^2)
                return ((p != 1) ? ((p != -1) ? new Polynomial(p).show() + "(" : "-(") : "") +
                        new Polynomial(r * a * a, 2 * r * a * q, r * q * q).show() + ((p != 1) ? ")" : "");

            case 9://p(rax-rq)(sax+sq)
                return ((p != 1) ? ((p != -1) ? new Polynomial(p).show() : "-") : "") + "(" +
                        new Polynomial(r * a, -1 * r * q).show() + ")(" +
                        new Polynomial(s * a, s * q).show() + ")";


            case 10://p(rax ^ 2 - rq ^ 2)
                return ((p != 1) ? ((p != -1) ? new Polynomial(p).show() + "(" : "-(") : "")
                        + new Polynomial(r * a, 0, -r * q * q).show() + ((p != 1) ? ")" : "");

            case 11://p(rax+rq)(sbx+st)
                return ((p != 1) ? ((p != -1) ? new Polynomial(p).show() : "-") : "") + "(" +
                        new Polynomial(r * a, r * q).show() + ")(" +
                        new Polynomial(s * b, s * t).show() + ")";


            case 12://p(rabx^2+r(at+bq)x+rqt)
                return ((p != 1) ? ((p != -1) ? new Polynomial(p).show() + "(" : "-(") : "") +
                        new Polynomial(r * a * b, r * (a * t + b * q), r * q * t).show()
                        + ((p != 1) ? ")" : "");

            case 13://p(rax^2+rbx+rc)(D<0)
                return ((p != 1) ? ((p != -1) ? new Polynomial(p).show() + "(" : "-(") : "") +
                        new Polynomial(r * a, r * s, q).show() + ((p != 1) ? ")" : "");

            default:
                throw new IllegalArgumentException();
        }
    }

    public void form() {
        description = String.format("$\\frac{%s}{%s}$", upper, lower);
    }

    String setNickname(int bottype) {
        switch (bottype) {
            case 0:
                return "0.1 Одночлен вида p";
            case 1:
                return "1.1 Одночлен вида px";
            case 2:
                return "1.2 Двучлен вида p(rax+rq)";
            case 3:
                return "2.1 Одночлен вида p$x^2$";
            case 4:
                return "2.2 Нераскладываемый двучлен p(ra$x^2$+rq)";
            case 5:
                return "2.3 Двучлен с множителем х вынесенным px(rax+rq)";
            case 6:
                return "2.4 Двучлен с множителем х внесенным p(ra$x^2$+rqx)";
            case 7:
                return "2.5 Квадрат суммы/разности свернутый (rax+rq)(sax+sq)";
            case 8:
                return "2.6 Квадрат суммы/разности раскрытый p(r$a^2$$x^2$+2raqx+r$q^2$)";
            case 9:
                return "2.7 Разность квадратов свернутая p(rax-rq)(sax+sq)";
            case 10:
                return "2.8 Разность квадратов раскрытая p(r$a^2$$x^2$-r$q^2$)";
            case 11:
                return "2.9 Произведение скобок с разными корнями свернутое (rax+rq)(sbx+sq)";
            case 12:
                return "2.10 Произведение скобок с разными корнями раскрытое rab$x^2$+r(at+bq)x+rqt";
            case 13:
                return "2.11 Нераскладываемый трехчлен ra$x^2$+rbx+rc";
            default:
                return "";
        }

    }

    void CalculateAnswer(int uptype, int bottype, int[] koefs, int[] koefs1) {

        String top = "", bottom = "";

        switch (uptype) {
            case 0:
                break;
            case 1:
                switch (bottype) {
                    case 5:
                    case 6:
                        top = "" + koefs[0] * koefs[1];
                        bottom = typi(2, koefs1[0], koefs1[1], koefs1[2], koefs1[3], koefs1[4]);
                        break;
                }
                break;
            case 2:
                top = "" + koefs[0] * koefs[1];
                switch (bottype) {
                    case 2:
                        bottom = "" + koefs1[0] * koefs1[1];
                        break;
                    case 5:
                    case 6:
                        bottom = typi(1, koefs1[0] * koefs1[1], 1, koefs1[2], koefs1[3], koefs1[4]);
                        break;
                    case 7:
                    case 8:
                        bottom = typi(2, koefs1[0] * koefs1[1],
                                1, koefs1[2], koefs1[3], koefs1[4]);
                        break;
                    case 9:
                        q *= -1;
                        bottom = typi(2, koefs1[0] * koefs1[1] * koefs1[3],
                                1, koefs1[2], koefs1[3], koefs1[4]);
                        break;
                    case 10:
                        q *= -1;
                        bottom = typi(2, koefs1[0] * koefs1[1],
                                1, koefs1[2], koefs1[3], koefs1[4]);
                        break;
                    case 11:
                        q = t;
                        bottom = typi(2, koefs1[0] * koefs1[1] * koefs1[3],
                                1, koefs1[4], 1, 1);
                        break;
                    case 12:
                        q = t;
                        bottom = typi(2, koefs1[0] * koefs1[1],
                                1, koefs1[4], 1, 1);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                break;
            case 3:
                switch (bottype) {
                    case 5:
                    case 6:
                        top = "" + new Polynomial(0, koefs[0] * koefs[1], 0).show();
                        bottom = typi(2, koefs1[0] * koefs1[1], 1, koefs1[2], koefs1[3], koefs1[4]);
                        break;
                }
                break;
            case 5:
            case 6:
                switch (bottype) {
                    case 1:
                        top = "" + koefs[0] * koefs[1];
                        bottom = typi(2, koefs1[0] * koefs1[1], 1, koefs1[2], koefs1[3], koefs1[4]);
                        break;
                    case 3:
                        top = "" + new Polynomial(0, koefs[0] * koefs[1], 0).show();
                        bottom = typi(2, koefs1[0] * koefs1[1], 1, koefs1[2], koefs1[3], koefs1[4]);
                        break;
                    case 2:
                        top = "" + new Polynomial(0, koefs[0] * koefs[1], 0).show();
                        bottom = "" + koefs1[0];
                        break;
                    case 5:
                    case 6:
                        top = "" + koefs[0] * koefs[1];
                        bottom = "" + koefs1[0] * koefs1[1];
                        break;
                    case 7:
                    case 8:
                    case 11:
                        top = "" + koefs[0] * koefs[1];
                        bottom = typi(2, koefs1[0] * koefs1[3], 1, koefs1[4], koefs1[5], koefs1[4]);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                break;

            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                switch (bottype) {
                    case 2:
                        top = typi(2, koefs[0] * koefs[3], 1, koefs[2], koefs[3], koefs[4]);
                        bottom = "" + koefs1[0];
                        break;
                    case 5:
                    case 6:
                        top = typi(2, koefs[0] * koefs[3], 1, koefs[2], koefs[3], koefs[4]);
                        bottom = "" + new Polynomial(0, koefs1[0], 0).show();
                        break;
                    case 7:
                    case 11:
                    case 9:
                        top = typi(2, koefs[0] * koefs[3], 1, koefs[2], koefs[3], koefs[4]);
                        bottom = typi(5, koefs1[0] * koefs1[3], 1, koefs1[2], koefs1[3], koefs1[4]);
                        break;
                    case 8:
                    case 12:
                    case 10:
                        top = "" + koefs[0];
                        bottom = typi(5, koefs1[0] * koefs1[1] / koefs[1],
                                1, koefs1[2], koefs1[3], koefs1[4]);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                break;

        }
        answerstring = String.format("$\\frac{%s}{%s}$", top, bottom);
    }

    @Override
    public String Display() {
        return description;
    }

    @Override
    public String GetAnswer() {
        return answerstring;
    }

    @Override
    public String GetTitle() {
        return "Формулы сокращенного умножения: ";
    }

    public static ArrayList<int[]> genarrskoefs(int type) {
        int[] ps = {1, rnd.nextBoolean() ? 1 + rnd.nextInt(9) : -1 - rnd.nextInt(9)};
        int[] rs = {1, 1 + rnd.nextInt(9)};
        int[] as = {1, rnd.nextBoolean() ? 1 + rnd.nextInt(9) : -1 - rnd.nextInt(9)};
        int[] ss = {1, 1 + rnd.nextInt(9)};
        int[] bs = {1, rnd.nextBoolean() ? 1 + rnd.nextInt(9) : -1 - rnd.nextInt(9)};

        if (type == 10 || type == 8)
            rs[1] *= ss[1];

        ArrayList<int[]> result = new ArrayList<int[]>();
        switch (type) {
            case 0:
            case 1:
            case 3:
                for (int p : ps)
                    result.add(new int[]{p, 0, 0, 0, 0});
                break;

            case 2:
            case 4:
            case 5:
            case 6:
            case 8:
            case 10:
            case 13:
                for (int p : ps)
                    for (int r : rs)
                        for (int a : as)
                            result.add(new int[]{p, r, a, ss[1], 0});

                break;
            case 7:
            case 9:
                for (int p : ps)
                    for (int r : rs)
                        for (int a : as)
                            for (int s : ss)
                                result.add(new int[]{p, r, a, s, 0});
                break;
            case 11:
                for (int p : ps)
                    for (int r : rs)
                        for (int a : as)
                            for (int s : ss)
                                for (int b : bs)
                                    result.add(new int[]{p, r, a, s, b});
                break;


            case 12:
                for (int p : ps)
                    for (int r : rs)
                        for (int a : as)
                            for (int b : bs)
                                result.add(new int[]{p, r * ss[1], a, ss[1], b});
                break;

            default:
                throw new IllegalArgumentException();
        }
        return result;

    }

    public static ArrayList<int[]> genarrskoefs(int ttype, int btype, int[] topkoefs) {

        int[] ps = {1, rnd.nextBoolean() ? 1 + rnd.nextInt(9) : -1 - rnd.nextInt(9)};
        int[] rs = {1, 1 + rnd.nextInt(9)};
        int a = topkoefs[2];
        int[] ss = {1, 1 + rnd.nextInt(9)};
        int b = topkoefs[4];
        if (b == 0) b = rnd.nextBoolean() ? 1 + rnd.nextInt(9) : -1 - rnd.nextInt(9);

        while (ps[1] == topkoefs[0] && topkoefs[0] != 1)
            ps[1] = rnd.nextBoolean() ? 1 + rnd.nextInt(9) : -1 - rnd.nextInt(9);


        switch (ttype) {
            case 2:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                switch (btype) {
                    case 2:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        while (rs[1] == topkoefs[1] || rs[1] == topkoefs[3])
                            rs[1] = 1 + rnd.nextInt(9);

                        while (ss[1] == topkoefs[1] || ss[1] == topkoefs[3] || ss[1] == rs[1])
                            ss[1] = 1 + rnd.nextInt(9);

                        break;
                    default:
                        break;
                }//prasb
                break;

            default:
                break;
        }

        switch (btype) {
            case 8:
            case 10:
            case 12:
                rs[1] *= ss[1];
                break;
        }


        ArrayList<int[]> result = new ArrayList<int[]>();
        switch (btype) {
            case 0:
            case 1:
            case 3:
                for (int p : ps)
                    result.add(new int[]{p, 0, 0, 0, 0});
                break;

            case 2:
            case 4:
            case 5:
            case 6:
            case 8:
            case 10:
            case 13:
                for (int p : ps)
                    for (int r : rs)
                        result.add(new int[]{p, r, a, ss[1], 0});

                break;
            case 7:
            case 9:
                for (int p : ps)
                    for (int r : rs)
                        for (int s : ss)
                            result.add(new int[]{p, r, a, s, 0});
                break;
            case 11:
                for (int p : ps)
                    for (int r : rs)
                        for (int s : ss)
                            result.add(new int[]{p, r, a, s, b});
                break;


            case 12:
                for (int p : ps)
                    for (int r : rs)
                        result.add(new int[]{p, r, a, ss[1], b});
                break;

            default:
                throw new IllegalArgumentException();
        }
        return result;

    }

    static boolean checkeven(int[] arr1, int[] arr2) {
        if (arr1[1] == arr2[1]) return true;
        else return false;
    }

    public static ArrayList<Division> genfrac(int uppertype) {

        ArrayList<Division> result = new ArrayList<Division>();

        for (int[] koefst : genarrskoefs(uppertype))
            for (int bottomtype = 2; bottomtype < 13; bottomtype++) {
                if (bottomtype != 3 && bottomtype != 4)
                    for (int[] koefsb : genarrskoefs(uppertype, bottomtype, koefst))
                        if (!checkeven(koefst, koefsb))
                            result.add(new Division(uppertype, bottomtype, koefst, koefsb));
            }
        return result;
    }

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>(genfrac(2));
        Division.toTEX(tasks);
    }

    public static void toTEX(ArrayList<Task> tasks) {
        String texString = "\\documentclass[fleqn]{article}\n" +
                "\\usepackage[T2A]{fontenc}\n" +
                "\\usepackage[utf8]{inputenc}\n" +
                "\\usepackage[russian]{babel}\n" +
                "\\usepackage[left=1cm,right=1cm,\n" +
                "    top=2cm,bottom=2cm,bindingoffset=0cm]{geometry}\n" +
                "\\pagenumbering{gobble}\n" +
                "\n" +
                "\\begin{document}\n" +
                "\\begin{large}\n";

        texString += String.format("    \\section*{%s}\n", tasks.get(0).GetTitle());

        int num = 0;
        String botnick = "";
        String upinfo = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (!upinfo.equals(((Division) tasks.get(i)).infoup)) {
                upinfo = ((Division) tasks.get(i)).infoup;
                texString += String.format("\\textbf{Числитель - %s%s}\\newline\n", ((Division) tasks.get(i)).upnickname, upinfo);
            }

            if (!botnick.equals(((Division) tasks.get(i)).botnickname))
                num = i;

            botnick = ((Division) tasks.get(i)).botnickname;
            texString += String.format("Знаменатель - %s %s\\newline\n", botnick, ((Division) tasks.get(i)).infolow);

            texString += String.format("%d)$\\:$ %s Ответ: %s\\newline\n",///////////////////////////
                    i - num + 1, tasks.get(i).Display(), tasks.get(i).GetAnswer());
        }

//        texString += "    \\section*{Ответы: }\n";
//
//        for (int i = 0; i < tasks.size(); i++)
//            texString += String.format("\\[%d)\\: %s\\]\n", i + 1, tasks.get(i).GetAnswer());

        texString += "\\end{large}\n" + "\\end{document}\n";

        WriteTEX("result.tex", texString);
        WriteTEX("result.txt", texString);

    }

    class Polynomial {
        int a = 0, b = 0, c = 0;
        //  Random rnd = new Random();

        Polynomial(int _a, int _b, int _c) {
            a = _a;
            b = _b;
            c = _c;
        }

        Polynomial(int _b, int _c) {
            this(0, _b, _c);
        }

        Polynomial(int _c) {
            this(0, 0, _c);
        }

        String show() {
            String result = "";
            if (a > 1 || a < 0) result += a + "x^2";
            if (a == 1) result += "x^2";
            if (b > 1 && a != 0) result += "+" + b + "x";
            if (b == 1 && a != 0) result += "+x";
            if (b > 1 && a == 0) result += b + "x";
            if (b == 1 && a == 0) result += "x";
            if (b < -1) result += "-" + -b + "x";
            if (b == -1) result += "-x";
            if (c > 0 && (a != 0 || b != 0)) result += "+" + c;
            if (c < 0) result += "-" + -c;
            if (c > 0 && (a == 0 && b == 0)) result += c;


            return result;
        }

        Polynomial Sum(Polynomial p1, Polynomial p2) {
            return new Polynomial(p1.a + p2.a, p1.b + p2.b, p1.a + p2.b);
        }

    }
}
