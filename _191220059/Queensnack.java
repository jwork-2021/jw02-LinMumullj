package _191220059;
public class Queensnack {

    private static Queensnack theQueensnack;

    public static Queensnack getTheQueensnack() {
        if (theQueensnack == null) {
            theQueensnack = new Queensnack();
        }
        return theQueensnack;
    }

    private Queensnack() {

    }

    private Sorter sorter;

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public String lineUp(Line line) {

        String log = new String();

        if (sorter == null) {
            return null;
        }

        Linable[] linables = line.toArray();
        int[] ranks = new int[linables.length];

        for (int i = 0; i < linables.length; i++) {
            ranks[i] = linables[i].getValue();
        }

        sorter.load(ranks);
        sorter.sort();

        System.out.println(ranks);
        String[] sortSteps = this.parsePlan(sorter.getPlan());

        for (String step : sortSteps) {
            this.execute(step);
            System.out.println(line.toString());
            log += line.toString();
            log += "\n[frame]\n";
        }

        return log;

    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(String step) {
        String[] couple = step.split("<->");
        Monster.getMonsterByRank(Integer.parseInt(couple[0]))
                .swapPosition(Monster.getMonsterByRank(Integer.parseInt(couple[1])));
    }

}
