import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Tangent extends Task {

    static Random rnd = new Random();

    public Tangent(int type) {
        typify(type);

    }

    int k, l, m, n, t;

    private void typify(int type) {

        switch (type) {
            case 5:
                k = 1 + rnd.nextInt(5);
                if (rnd.nextBoolean())
                    k *= -1;
                l = 1 + rnd.nextInt(5);
                if (rnd.nextBoolean())
                    l *= -1;
                m = 1 + rnd.nextInt(5);
                if (rnd.nextBoolean())
                    m *= -1;
                n = rnd.nextInt(6);
                if (rnd.nextBoolean())
                    n *= -1;
                t = rnd.nextInt(6);
                if (rnd.nextBoolean())
                    t *= -1;


                break;
            default:
                break;

        }
    }

    static double[][] generatesystem(int startingpoint, int systemsize, ArrayList<Integer> extr) {
        double[][] systemkoefs = new double[systemsize + extr.size() + 1][];
//        double[][] systemkoefs = new double[systemsize][];
        for (int i = 0; i < systemsize; i++) {
            double[] equationkoefs = new double[systemsize + extr.size() + 2];
//            double[] equationkoefs = new double[systemsize + 1];
            equationkoefs[equationkoefs.length - 1] = ys.get(startingpoint + i);
            for (int j = 0; j < equationkoefs.length - 1; j++)
                equationkoefs[equationkoefs.length - 2 - j] = Math.pow(xs.get(startingpoint + i), j);
            systemkoefs[i] = equationkoefs;
        }
        if (extr.size() > 0)
            for (int i = systemsize; i < systemkoefs.length - 1; i++) {
                double[] equationkoefs = new double[systemsize + extr.size() + 1];
                equationkoefs[equationkoefs.length - 1] = 0;
                equationkoefs[equationkoefs.length - 2] = 0;
                for (int j = 1; j < equationkoefs.length - 1; j++)
                    equationkoefs[equationkoefs.length - 2 - j] = Math.pow(extr.get(i - systemsize), j - 1) * j;
                systemkoefs[i] = equationkoefs;
            }
        return systemkoefs;
    }

    static double[][] generatesystem(int startingpoint, int systemsize, ArrayList<Integer> extr, int middlepoint, int middlepointvalue) {
        double[][] systemkoefs = new double[systemsize + extr.size() + 1][];
//        double[][] systemkoefs = new double[systemsize][];
        for (int i = 0; i < systemsize; i++) {
            double[] equationkoefs = new double[systemsize + extr.size() + 2];
//            double[] equationkoefs = new double[systemsize + 1];
            equationkoefs[equationkoefs.length - 1] = ys.get(startingpoint + i);
            for (int j = 0; j < equationkoefs.length - 1; j++)
                equationkoefs[equationkoefs.length - 2 - j] = Math.pow(xs.get(startingpoint + i), j);
            systemkoefs[i] = equationkoefs;
        }
        if (extr.size() > 0)
            for (int i = systemsize; i < systemkoefs.length - 1; i++) {
                double[] equationkoefs = new double[systemsize + extr.size() + 2];
                equationkoefs[equationkoefs.length - 1] = 0;
                equationkoefs[equationkoefs.length - 2] = 0;
                for (int j = 1; j < equationkoefs.length - 1; j++)
                    equationkoefs[equationkoefs.length - 2 - j] = Math.pow(extr.get(i - systemsize), j - 1) * j;
                systemkoefs[i] = equationkoefs;
            }

        double[] equationkoefs = new double[systemsize + extr.size() + 2];
        equationkoefs[equationkoefs.length - 1] = middlepointvalue;
        equationkoefs[equationkoefs.length - 2] = 0;
        for (int j = 1; j < equationkoefs.length - 1; j++)
            equationkoefs[equationkoefs.length - 2 - j] = Math.pow(middlepoint, j - 1) * j;
        systemkoefs[systemkoefs.length - 1] = equationkoefs;

        return systemkoefs;
    }

    static double[][] generatesystem(int startingpoint, int systemsize, ArrayList<Integer> extr,
                                     int middlepoint1, int middlepointvalue1, int middlepoint2, int middlepointvalue2) {
        double[][] systemkoefs = new double[systemsize + extr.size() + 2][];
//        double[][] systemkoefs = new double[systemsize][];
        for (int i = 0; i < systemsize; i++) {
            double[] equationkoefs = new double[systemsize + extr.size() + 3];
//            double[] equationkoefs = new double[systemsize + 1];
            equationkoefs[equationkoefs.length - 1] = ys.get(startingpoint + i);
            for (int j = 0; j < equationkoefs.length - 1; j++)
                equationkoefs[equationkoefs.length - 2 - j] = Math.pow(xs.get(startingpoint + i), j);
            systemkoefs[i] = equationkoefs;
        }
        if (extr.size() > 0)
            for (int i = systemsize; i < systemkoefs.length - 1; i++) {
                double[] equationkoefs = new double[systemsize + extr.size() + 3];
                equationkoefs[equationkoefs.length - 1] = 0;
                equationkoefs[equationkoefs.length - 2] = 0;
                for (int j = 1; j < equationkoefs.length - 1; j++)
                    equationkoefs[equationkoefs.length - 2 - j] = Math.pow(extr.get(i - systemsize), j - 1) * j;
                systemkoefs[i] = equationkoefs;
            }

        double[] equationkoefs = new double[systemsize + extr.size() + 3];
        equationkoefs[equationkoefs.length - 1] = middlepointvalue1;
        equationkoefs[equationkoefs.length - 2] = 0;
        for (int j = 1; j < equationkoefs.length - 1; j++)
            equationkoefs[equationkoefs.length - 2 - j] = Math.pow(middlepoint1, j - 1) * j;
        systemkoefs[systemkoefs.length - 2] = equationkoefs;

        equationkoefs = new double[systemsize + extr.size() + 3];
        equationkoefs[equationkoefs.length - 1] = middlepointvalue2;
        equationkoefs[equationkoefs.length - 2] = 0;
        for (int j = 1; j < equationkoefs.length - 1; j++)
            equationkoefs[equationkoefs.length - 2 - j] = Math.pow(middlepoint2, j - 1) * j;
        systemkoefs[systemkoefs.length - 1] = equationkoefs;

        return systemkoefs;
    }

    static Plot.Data buildGraph() {
        Plot.Data d = Plot.data();
        Graph g;
        ArrayList<Integer> extr = new ArrayList<>();
        g = new Graph();

        for (int p : ps)
            if (p >= -10 && p <= 10) extr.add(p);


        g.koefs = Lup.calculate(generatesystem(0, 9, extr));
        for (double i = -10; i <= -9; i += 0.001)
            d = d.xy(i, g.getY(i));

//        extr = new ArrayList<>();
//        for (int p : ps)
//            if (p >= -9 && p <= -8) extr.add(p);
        g.koefs = Lup.calculate(generatesystem(0, 10, extr));
        for (double i = -9; i <= -8; i += 0.001)
            d = d.xy(i, g.getY(i));

//        extr = new ArrayList<>();
//        for (int p : ps)
//            if (p >= -8 && p <= -7) extr.add(p);
        g.koefs = Lup.calculate(generatesystem(0, 11, extr));
        for (double i = -8; i <= -7; i += 0.001)
            d = d.xy(i, g.getY(i));

//        extr = new ArrayList<>();
//        for (int p : ps)
//            if (p >= -7 && p <= -6) extr.add(p);
        g.koefs = Lup.calculate(generatesystem(0, 15, extr));
        for (double i = -7; i <= -6; i += 0.001)
            d = d.xy(i, g.getY(i));

//        extr = new ArrayList<>();
//        for (int p : ps)
//            if (p >= -6 && p <= -5) extr.add(p);
        g.koefs = Lup.calculate(generatesystem(0, 18, extr));
        for (double i = -6; i <= -5; i += 0.001)
            d = d.xy(i, g.getY(i));

//        extr = new ArrayList<>();
//        for (int p : ps)
//            if (p >= -5 && p <= 5) extr.add(p);
        g = new Graph();
        g.koefs = Lup.calculate(generatesystem(0, 21, extr));
        for (double i = -5; i <= 5; i += 0.001)
            d = d.xy(i, g.getY(i));

//        extr = new ArrayList<>();
//        for (int p : ps)
//            if (p >= 5 && p <= 6) extr.add(p);
        g = new Graph();
        g.koefs = Lup.calculate(generatesystem(3, 18, extr));
        for (double i = 5; i <= 6; i += 0.001)
            d = d.xy(i, g.getY(i));

//        extr = new ArrayList<>();
//        for (int p : ps)
//            if (p >= 6 && p <= 7) extr.add(p);
        g = new Graph();
        g.koefs = Lup.calculate(generatesystem(6, 15, extr));
        for (double i = 6; i <= 7; i += 0.001)
            d = d.xy(i, g.getY(i));

//        extr = new ArrayList<>();
//        for (int p : ps)
//            if (p >= 7 && p <= 8) extr.add(p);
        g = new Graph();
        g.koefs = Lup.calculate(generatesystem(10, 11, extr));
        for (double i = 7; i <= 8; i += 0.001)
            d = d.xy(i, g.getY(i));

//        extr = new ArrayList<>();
//        for (int p : ps)
//            if (p >= 8 && p <= 9) extr.add(p);
        g = new Graph();
        g.koefs = Lup.calculate(generatesystem(11, 10, extr));
        for (double i = 8; i <= 9; i += 0.001)
            d = d.xy(i, g.getY(i));

//        extr = new ArrayList<>();
//        for (int p : ps)
//            if (p >= 9 && p <= 10) extr.add(p);
        g = new Graph();
        g.koefs = Lup.calculate(generatesystem(12, 9, extr));
        for (double i = 9; i <= 10; i += 0.001)
            d = d.xy(i, g.getY(i));

        return d;
    }

    static ArrayList<Integer> xs = new ArrayList<>();
    static ArrayList<Integer> ys = new ArrayList<>();
    static ArrayList<Integer> ps = new ArrayList<>();

    public static void plot() {
        Plot pl = Plot.plot(Plot.plotOpts().gridColor(Color.gray).gridStroke(new BasicStroke(10))).
                xAxis("x", Plot.axisOpts().range(-10, 10)).
                yAxis("y", Plot.axisOpts().range(-10, 10));
        pl = new Plot(pl.opts().grids(20, 20));

        int limit = 9;
//region generatingcoordinates
        xs.add(-10);
        //  ys.add(-limit + rnd.nextInt(2 * limit + 1));
        ys.add(0);
        int currentx = 0;
        ArrayList<Integer> lengths = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            lengths.add(4);
//        for (int i = 0; i < 6; i++)
//            lengths.add(3);
        int step = 2;
        while (currentx < 20) {
//            ps.add(currentx - 10);
//            ys.add(ys.get(currentx) + step);
//            xs.add(++currentx - 10);
//            ys.add(ys.get(currentx) + 1);
//            xs.add(++currentx - 10);
//            ps.add(currentx - 10);
//            ys.add(ys.get(currentx) - 1);
//            xs.add(++currentx - 10);
//            ys.add(ys.get(currentx) - step);
//            xs.add(++currentx - 10);
            ps.add(currentx - 10);
            int index = rnd.nextInt(lengths.size());
            int length = lengths.get(index);
            lengths.remove(index);
            if (currentx + length >= 20)
                length = 20 - currentx;
            boolean goingup = rnd.nextBoolean();
            if (ys.get(currentx) >= limit - length) goingup = false;
            if (ys.get(currentx) <= -limit + length) goingup = true;
            int jumplimit;
            if (goingup) {
                jumplimit = Math.min(limit - ys.get(currentx), 4 * length);
            } else {
                jumplimit = Math.min(limit + ys.get(currentx), 4 * length);
            }
            int onejumplimit = Math.min(4, jumplimit - length + 1);

            for (int i = 0; i < length; i++) {
                int delta = 1 + (onejumplimit > 0 ? rnd.nextInt(onejumplimit) : 0);
                if (goingup)
                    ys.add(ys.get(currentx) + delta);
                else
                    ys.add(ys.get(currentx) - delta);
                currentx++;
                xs.add(currentx - 10);

                jumplimit -= delta;
                onejumplimit = Math.min(4, jumplimit);
            }

        }
        ps.add(10);
//endregion

        for (int i = 0; i < xs.size(); i++)
            System.out.print(String.format("(%d,%d) ", xs.get(i), ys.get(i)));
        System.out.println();
        for (int i = 0; i < ps.size(); i++)
            System.out.print(String.format("%d ", ps.get(i)));

        //   Plot.Data d = buildGraph();
        Plot.Data d = Plot.data();
        Graph g = new Graph();
        ArrayList<Integer> extr = new ArrayList<>();
        int middlepoint1 = 0, middlepoint2 = 0;
        int middlepointvalue1 = 0, middlepointvalue2 = 0;
        for (int i = 0; i < ps.size() - 1; i++)
            for (int t = 0; t < ps.get(i + 1) - ps.get(i); t++) {
                extr = new ArrayList<>();
                if (t == 0 && ps.get(i) != -10)
                    extr.add(ps.get(i));
                if (t == ps.get(i + 1) - ps.get(i) - 1 && ps.get(i + 1) != 10)
                    extr.add(ps.get(i + 1));

                middlepoint1 = middlepoint2;
                middlepointvalue1 = middlepointvalue2;

                middlepoint2 = ps.get(i) + t + 1;
                middlepointvalue2 = 2 + rnd.nextInt(2);

                if (t == ps.get(i + 1) - ps.get(i) - 1)
                    middlepointvalue2 = 0;

                if (ys.get(ps.get(i) + 11 + t) < ys.get(ps.get(i) + 10 + t)) middlepointvalue2 *= -1;
//                    double[][] koefs = generatesystem(ps.get(i) + 10 + t, 2,
//                        extr, middlepoint1, middlepointvalue1);
//
//                for (double[] koe : koefs) {
//                    for (double k : koe)
//                        System.out.print(k + " ");
//                    System.out.println();
                if (middlepointvalue1 == 0)
                    g.koefs = Lup.calculate(generatesystem(ps.get(i) + 10 + t, 2,
                            extr, middlepoint2, middlepointvalue2));
                else if (middlepointvalue2 == 0)
                    g.koefs = Lup.calculate(generatesystem(ps.get(i) + 10 + t, 2,
                            extr, middlepoint1, middlepointvalue1));
                else
                    g.koefs = Lup.calculate(generatesystem(ps.get(i) + 10 + t, 2,
                            extr, middlepoint1, middlepointvalue1, middlepoint2, middlepointvalue2));

                for (double j = ps.get(i) + t; j <= ps.get(i) + 1 + t; j += 0.0001)
                    d = d.xy(j, g.getY(j));
            }

//        for (double[] sys : systemkoefs) {
//            for (double s : sys)
//                System.out.print(String.format("%.2f ", s));
//            System.out.println();
//        }


        //        Plot.Data b = Plot.data();
//        b = b.xy(-3, 1).xy(1, 2);
//        Plot.DataSeriesOptions opts = Plot.seriesOpts().
//                marker(Plot.Marker.DIAMOND).
//                markerColor(Color.GREEN).
//                color(Color.BLACK);

        Plot plot = pl.series("Data", d, null);

        try {
            plot.save("Graph", "png");
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        ArrayList<Task> tasks = new ArrayList<>();
//        for (int i = 0; i < 5; i++)
//            tasks.add(new Tangent(5));
//        toTEX(tasks);
        plot();
    }


    @Override
    String Display() {
        return String.format("Касательная к графику функции $f(x)=%s$ параллельна прямой $y=%s$ и касается графика в точке $(1; %d)$,\\newline\n" +
                        "Определите значение производной функции $f(x)$ в точке касания.",
                new Polynomial(m, n, k - 3 * m - 2 * n, l).show(), new Polynomial(k, t).show(), k - 2 * m - n + l);
    }

    @Override
    String GetAnswer() {
        return "" + k;
    }

    @Override
    String GetTitle() {
        return "Касательная";
    }
}

class Graph {
    double[] koefs;

    double getY(double x) {
        double result = 0;
        for (int j = 0; j < koefs.length; j++)
            result = result + (Math.pow(x, j) * (koefs[koefs.length - 1 - j]));
        return result;
    }

}

class Parabola {
    double a, b, c;

    void interpolate(int x1, int x2, int x3, int y1, int y2, int y3) {
        a = ((y3 - (x3 * (y2 - y1) + x2 * y1 - x1 * y2)) / (double) (x2 - x1))
                / (x3 * (x3 - x1 - x2) + x1 * x2);
        b = ((double) (y2 - y1)) / (x2 - x1) - a * (x1 + x2);
        c = ((double) (x2 * y1 - x1 * y2)) / (x2 - x1) + a * x1 * x2;
    }

    double getY(double x) {
        return a * x * x + b * x + c;

    }
}

class Polynomial {
    int d = 0, a = 0, b = 0, c = 0;

    Polynomial(int _d, int _a, int _b, int _c) {
        d = _d;
        a = _a;
        b = _b;
        c = _c;
    }

    Polynomial(int _b, int _c) {
        this(0, 0, _b, _c);
    }

    Polynomial(int _c) {
        this(0, 0, 0, _c);
    }

    String show() {
        String result = "";
        if (d > 1 || d < -1) result += d + "x^3";
        if (d == 1) result += "x^3";
        if (d == -1) result += "-x^3";

        if (a > 1 && d != 0) result += "+" + a + "x^2";
        if (a == 1 && d != 0) result += "+x^2";
        if (a > 1 && d == 0) result += a + "x^2";
        if (a == 1 && d == 0) result += "x^2";
        if (a < -1) result += "-" + -a + "x^2";
        if (a == -1) result += "-x^2";

        if (b > 1 && (a != 0 || d != 0)) result += "+" + b + "x";
        if (b == 1 && (a != 0 || d != 0)) result += "+x";
        if (b > 1 && (a == 0 && d == 0)) result += b + "x";
        if (b == 1 && (a == 0 && d == 0)) result += "x";
        if (b < -1) result += "-" + -b + "x";
        if (b == -1) result += "-x";

        if (c > 0 && (a != 0 || b != 0 || d != 0)) result += "+" + c;
        if (c < 0) result += "-" + -c;
        if (c > 0 && (a == 0 && b == 0 && c == 0)) result += c;


        return result;
    }
}
