import java.io.*;
import java.util.ArrayList;
import java.util.Random;

abstract class Task {

    double answer = 0;
    String type;
    String description;
    String answerstring = "";
    static Random rnd = new Random();

    public Task() {
    }

    public Task(String _type) {
        type = _type;
    }

    abstract String Display();

    abstract String GetAnswer();

    abstract String GetTitle();

    double generatekoef(int bound) {
        switch (type) {
            case ("int"):
                return rnd.nextInt(bound) + 1;
            case "double":
                return rnd.nextInt(bound) + (double) rnd.nextInt(100) / 100;
            default:
                throw new IllegalArgumentException();
        }
    }

    String setkoefstring(double val) {
        if (val - (int) val == 0)
            return "" + (int) val;
        else
            return String.format("%.2f", val);
    }

    public void toHTML() {
        String htmlString = readHTML("./template.html");
        String dscr = Display();
        String answ = GetAnswer();
        htmlString = htmlString.replace("$dscr", dscr);
        htmlString = htmlString.replace("$answ", answ);
        WriteHTML("./result.html", htmlString);
    }

    private static String readHTML(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    void WriteHTML(String name, String content) {
        try (
                FileWriter writer = new FileWriter(name);
                BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(content);

        } catch (
                IOException e) {
            System.err.format("IOException: %s%n", e);
        }
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

            texString += String.format("%d) %s\\newline\\newline\n",
                    i + 1, tasks.get(i).Display());

        texString += "    \\section*{Ответы: }\n";
        for (int i = 0; i < tasks.size(); i++)
            texString += String.format("\\[%d)\\: %s\\]\n", i + 1, tasks.get(i).GetAnswer());

        texString += "\\end{large}\n" + "\\end{document}\n";

        WriteTEX("result.tex", texString);
        WriteTEX("result.txt", texString); //String texString = readTEX("./template.tex");
    }

    private static String readTEX(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    static void WriteTEX(String name, String content) {
        try (
                FileWriter writer = new FileWriter(name);
                BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(content);
        } catch (
                IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}