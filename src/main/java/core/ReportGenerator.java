package core;

import java.util.List;

public class ReportGenerator {

    public void printReport(List<String> warnings) {
        if (warnings.isEmpty()) {
            System.out.println("=== Lint Report ===");
            System.out.println("No issues found. ✓");
            System.out.println("==================\n");
            return;
        }
        System.out.println("=== Lint Report ===");
        System.out.println("Found " + warnings.size() + " issue(s):\n");

        for (String warning : warnings) {
            System.out.println("[Lint] " + warning);
        }

        System.out.println("\n==================\n");
    }
}

