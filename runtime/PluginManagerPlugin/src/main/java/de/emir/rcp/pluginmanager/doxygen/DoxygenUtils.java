package de.emir.rcp.pluginmanager.doxygen;

import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.runtime.epf.utils.WorkspaceResolver;
import org.apache.maven.model.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DoxygenUtils {
    private static final WorkspaceResolver workspaceResolver = new WorkspaceResolver(new MavenUtil(true));

    public static Collection<Model> getResolvedWorkspaces(ProductFile pf) {
        return getResolvedWorkspaces(pf.getWorkspaces());
    }

    public static Collection<Model> getResolvedWorkspaces(List<File> files) {
        return workspaceResolver.resolveWorkspaces(files);
    }

    public static boolean writeFile(File file, String content) {
        if (file.exists() == false) {

            File parent = file.getParentFile();
            if (parent.exists() == false) {
                boolean mkdir = parent.mkdirs();
                if (mkdir == false) {
                    return false;
                }
            }

            boolean create = false;
            try {
                create = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (create == false) {
                return false;
            }
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    public static List<DirectedGraph<Doxygen.MavenModel>> createGraph(List<List<Doxygen.MavenModel>> allWorkspaces) {
        List<DirectedGraph<Doxygen.MavenModel>> graphList = new ArrayList<>();

        for (List<Doxygen.MavenModel> models : allWorkspaces) {

            if (models.size() == 0) {
                continue;
            }

            DirectedGraph<Doxygen.MavenModel> graph = new DirectedGraph<>(models.get(0));
            DirectedGraph<Doxygen.MavenModel> last = graph;

            for (Doxygen.MavenModel mavenModel : models) {
                if (models.indexOf(mavenModel) != 0) {

                    DirectedGraph<Doxygen.MavenModel> current = new DirectedGraph<>(mavenModel);
                    last.appendChild(current);
                    last = current;
                }
            }

            graphList.add(graph);
        }

        return mergeGraphs(graphList);
    }

    private static <T> List<DirectedGraph<T>> mergeGraphs(List<DirectedGraph<T>> graphList) {

        List<DirectedGraph<T>> returnGraph = new ArrayList<>();

        for (DirectedGraph<T> graph : graphList) {

            if (graph.getElement() == null || graph.getChildren().size() == 0) {
                continue;
            }

            for (DirectedGraph<T> other : graphList) {
                if (graph.getElement().equals(other.getElement()) && (graph.equals(other) == false)) {
                    graph.getChildren().addAll(other.getChildren());
                    other.setChildren(null);
                    other.setElement(null);
                }
            }

            if (graph.getChildren().size() != 0 && graph.getElement() != null) {
                returnGraph.add(graph);
            }
        }

        Iterator<DirectedGraph<T>> iterator = returnGraph.iterator();
        while (iterator.hasNext()) {
            resolveGraph(iterator.next());
        }

        return returnGraph;
    }

    private static <T> void resolveGraph(DirectedGraph<T> graph) {
        if (graph.getChildren().size() == 0) {
            return;
        }

        Iterator<DirectedGraph<T>> iterator = graph.getChildren().iterator();

        while (iterator.hasNext()) {
            DirectedGraph<T> child = iterator.next();

            if (child.getElement() == null) {
                iterator.remove();
                continue;
            }

            Iterator<DirectedGraph<T>> otherIterator = new ArrayList<>(graph.getChildren()).iterator();
            while (otherIterator.hasNext()) {
                DirectedGraph<T> otherChild = otherIterator.next();
                if (child.getElement().equals(otherChild.getElement()) && (child.equals(otherChild) == false)) {
                    child.getChildren().addAll(otherChild.getChildren());
                    otherChild.setChildren(null);
                    otherChild.setElement(null);
                }
            }
        }
        iterator = graph.getChildren().iterator();

        while (iterator.hasNext()) {
            resolveGraph(iterator.next());
        }
    }
}
