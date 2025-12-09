package core;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.ClassNode;

public class LinterCore {
    private final ASMHandler asmHandler;
    private final CheckManager checkManager;
    private final ReportGenerator reportGenerator;

    public LinterCore(ASMHandler asmHandler, CheckManager checkManager, ReportGenerator reportGenerator) {
        this.asmHandler = asmHandler;
        this.checkManager = checkManager;
        this.reportGenerator = reportGenerator;
    }

    public void run(String... inputPaths) throws IOException {
        List<String> allWarnings = new ArrayList<>();
        
        for (String inputPath : inputPaths) {
            System.out.println("Analyzing: " + inputPath);
            List<String> warnings = analyzePath(inputPath);
            allWarnings.addAll(warnings);
        }
        
        reportGenerator.printReport(allWarnings);
    }

    private List<String> analyzePath(String inputPath) throws IOException {
        List<String> allWarnings = new ArrayList<>();
        Path path = Path.of(inputPath);
        File file = path.toFile();

        if (!file.exists()) {
            try {
                ClassNode classNode = asmHandler.loadClass(inputPath);
                List<String> warnings = checkManager.runAll(classNode);
                allWarnings.addAll(warnings);
            } catch (IOException e) {
                throw new IOException("Path does not exist and cannot be loaded as class: " + inputPath, e);
            }
        } else if (file.isFile()) {
            if (file.getName().endsWith(".class")) {
                ClassNode classNode = asmHandler.loadClass(file);
                List<String> warnings = checkManager.runAll(classNode);
                allWarnings.addAll(warnings);
            } else {
                throw new IOException("File is not a .class file: " + inputPath);
            }
        } else if (file.isDirectory()) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path visitedFile, BasicFileAttributes attrs) throws IOException {
                    if (visitedFile.toString().endsWith(".class")) {
                        try {
                            ClassNode classNode = asmHandler.loadClass(visitedFile.toFile());
                            List<String> warnings = checkManager.runAll(classNode);
                            allWarnings.addAll(warnings);
                        } catch (IOException e) {
                            System.err.println("Error processing " + visitedFile + ": " + e.getMessage());
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }

        return allWarnings;
    }
}

