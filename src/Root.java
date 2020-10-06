import java.util.ArrayList;
import java.util.Arrays;

public class Root extends Task {

    int[] koefs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] toshow;
    private int rootdepth = 3;

    public Root() {

        boolean valid = false;
        while (!valid) {
            rootdepth = 3 + rnd.nextInt(3);

            for (int i = 0; i < rnd.nextInt(2) + 3; i++) {
                int temp = rnd.nextInt(7) + 2;
                koefs[temp] = rootdepth;
            }

            answer = 1;
            for (int i = 1; i < koefs.length; i++) {
                System.out.println(i + " " + koefs[i] + " " + Math.pow(i, koefs[i]));
                if (koefs[i] > 0)
                    answer *= Math.pow(i, koefs[i]);
            }

            toshow = new int[(int) (Math.log(answer) / Math.log(100)) + 3 + rnd.nextInt(1)];
            Arrays.fill(toshow, 1);

            int temp = 0;
            for (int i = 2; i < koefs.length; i++) {
                while (koefs[i] > 0) {
                    if (toshow[temp] == 1 || (rnd.nextBoolean() && toshow[temp] * i <= 100)) {
                        toshow[temp] *= i;
                        koefs[i]--;
                    }
                    temp++;
                    if (temp == toshow.length)
                        temp = 0;
                    for (int koef : toshow)
                        System.out.print(koef + " ");
                    System.out.println();
                }
            }

            description = String.format("\\sqrt[%d]{", rootdepth);
            for (int i = 0; i < toshow.length; i++)
                if (toshow[i] > 1)
                    description += "" + toshow[i] + "\\cdot";

            for (int i = 0; i < toshow.length; i++)
                if (toshow[i] != 1) {
                    temp = toshow[i];
                    break;
                }

            for (int i = 0; i < toshow.length; i++)
                if (toshow[i] != 1)
                    if (toshow[i] != temp) {
                        valid = true;
                        break;
                    }

            description = description.substring(0, description.length() - 5);
            description += "}";
            answer = Math.round(Math.pow(answer, (double) 1 / rootdepth));
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
        return "Корень: ";
    }

    public static void main(String[] args) {

        int count = 7;

        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println("Task " + (i + 1));
            tasks.add(new Root());
            System.out.println(tasks.get(i).Display());
            System.out.println(tasks.get(i).GetAnswer() + "\n");
        }
        Task.toTEX(tasks);
    }

}
