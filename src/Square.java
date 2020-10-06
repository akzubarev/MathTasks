import java.util.ArrayList;

public class Square extends Task {

    private int n, k,
            m, l,
            q, pack,
            r;


    public Square(String type, int subtype) {
        super(type);
        typify(subtype);
    }


    private void generateFirst() {
        switch (rnd.nextInt(4)) {
            case 0:
                n = 10;
                break;
            case 1:
                n = 20;
                break;
            case 2:
                n = 25;
                break;
            case 3:
                n = 50;
                break;
        }
        do
            switch (rnd.nextInt(4)) {
                case 0:
                    k = 10;
                    break;
                case 1:
                    k = 20;
                    break;
                case 2:
                    k = 25;
                    break;
                case 3:
                    k = 50;
                    break;
            }
        while (k == n);

        answer = 10000d / n / k;

        description = String.format("Сколько плиток %s нужно, " +
                        "чтобы заполнить квадратный участок площадью 1 кв. м.?",
                plitka(n, k));
    }

    String plitka(int n, int k) {

        String newn, newk;

        switch (rnd.nextInt(3)) {
            case 0:
                return String.format("%d$\\times$%d см.", n, k);
            case 1:
                if (n % 10 == 5)
                    newn = String.format("%.1f", n / 10d);
                else newn = String.format("%d", n / 10);
                if (k % 10 == 5)
                    newk = String.format("%.1f", k / 10d);
                else newk = String.format("%d", k / 10);

                return String.format("%s$\\times$%s дм.", newn, newk);
            case 2:
                if (n % 10 == 5)
                    newn = String.format("%.2f", n / 100d);
                else newn = String.format("%.1f", n / 100d);
                if (k % 10 == 5)
                    newk = String.format("%.2f", k / 100d);
                else newk = String.format("%.1f", k / 100d);
                return String.format("%s$\\times$%s м.", newn, newk);
            default:
                throw new IllegalArgumentException();
        }
    }

    private void generateSecond() {
        do n = (rnd.nextInt(7) + 3) * 5;
        while (n == 10 || n == 20 || n == 25 || n == 50);
        do k = (rnd.nextInt(9) + 2) * 5;
        while (k == n);

        m = n;
        if (n % 10 == 5) m /= 5;
        else m /= 10;
        m *= (1 + rnd.nextInt(50 / m));
        l = k;
        if (k % 10 == 5) l /= 5;
        else l /= 10;
        l *= (1 + rnd.nextInt(50 / l));

        if (rnd.nextBoolean()) {
            int tmp = m;
            m = l;
            l = tmp;
        }

        answer = (int) (m * 10000d / n * l / k);
        description = String.format("Сколько плиток %s нужно, " +
                        "чтобы заполнить прямоугольный участок размером %d$\\times$%d м.?",
                plitka(n, k), m, l);
    }

    private void generateThird() {
        generateSecond();
        pack = 10000 / n / k;
        double r = 10000d * m * l / n / k / pack;
        q = (int) r;
        if (q != r)
            q++;
        answer = q;
        description = String.format("В одной упаковке содержится %d плиток размером %s " +
                        "Сколько упаковок плитки потребуется купить, " +
                        "чтобы заполнить прямоугольный участок размером %d$\\times$%d м.? " +
                        "Плитка продается только целыми упаковками.",
                pack, plitka(n, k), m, l);
    }

    private void typify(int subtype) {
        if (subtype == -1)
            subtype = (int) generatekoef(6);
        switch (subtype) {
            case 0:
                generateFirst();
                break;
            case 1:
                generateSecond();
                break;
            case 2:
                generateThird();
                break;
            case 3:
                generateThird();
                do r = rnd.nextInt(78) + 21;
                while (r % 10 == 0);
                r *= 10;
                answer = q * r;
                description = String.format("Одна упаковка содержит %d плиток размером %s " +
                                "Цена упаковки - %d р. Сколько будет стоить покупка плитки, " +
                                "необходимой для заполнения прямоугольного участка размером %d$\\times$%d м.? " +
                                "Плитка продается только целыми упаковками. Ответ дайте в рублях.",
                        pack, plitka(n, k), r, m, l);
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
        return "Площадь: ";
    }

    public static void toTEX(ArrayList<Task> tasks) {
        //String texString = readTEX("./template.tex");
        String texString = "\\documentclass[fleqn]{article}\n" +
                "\\usepackage[T2A]{fontenc}\n" +
                "\\usepackage[utf8]{inputenc}\n" +
                "\\usepackage[russian]{babel}\n" +
                "\\pagenumbering{gobble}\n" +
                "\n" +
                "\\begin{document}\n" +
                "\\begin{large}\n";

        texString += String.format("    \\section*{%s}\n", tasks.get(0).GetTitle());
        for (int i = 0; i < tasks.size(); i++)
//            texString += String.format("\\[%d) %s\\]\n",
            //                  i + 1, tasks.get(i).Display());
            texString += String.format("%d) %s\\newline\\newline\n",
                    i + 1, tasks.get(i).Display());

        texString += "    \\section*{Ответы: }\n";

        for (int i = 0; i < tasks.size(); i++)
            texString += String.format("\\[%d)\\: %s\\]\n", i + 1, tasks.get(i).GetAnswer());

        texString += "\\end{large}\n" + "\\end{document}\n";

        WriteTEX("result.tex", texString);
        WriteTEX("result.txt", texString); //String texString = readTEX("./template.tex");
    }

    public static void main(String[] args) {
        int count = 4;
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < count; i++)
            tasks.add(new Square("int", i));
        Task.toTEX(tasks);
    }

}
