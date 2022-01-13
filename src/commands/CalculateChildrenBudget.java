package commands;


import builder.Child;
import reading.Children;

public class CalculateChildrenBudget implements AnnualUpdateCommand {
    private Children children;
    private double santaBudget;

    public CalculateChildrenBudget(final Children children, final double santaBudget) {
        this.children = children;
        this.santaBudget = santaBudget;
    }

    /**
     * calculates the budget each kid gets
     */
    public void execute() {
        double niceScoreSum = 0;

        for (int i = 0; i < children.getChildren().size(); i++) {
            Child child = children.getChildren().get(i);
            niceScoreSum = niceScoreSum + child.getAverageScore();
        }

        double budgetUnit = santaBudget / niceScoreSum;

        for (int i = 0; i < children.getChildren().size(); i++) {
            double averageScore = children.getChildren().get(i).getAverageScore();
            double assignedBudget = averageScore * budgetUnit;
            String elf = children.getChildren().get(i).getElf();
            if (elf.compareTo("black") == 0) {
                assignedBudget = assignedBudget - assignedBudget * 30 / 100;
            }
            if (elf.compareTo("pink") == 0) {
                assignedBudget = assignedBudget + assignedBudget * 30 / 100;
            }
            children.getChildren().get(i).setAssignedBudget(assignedBudget);
        }


    }
}
