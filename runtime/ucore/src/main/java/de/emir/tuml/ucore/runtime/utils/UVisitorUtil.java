package de.emir.tuml.ucore.runtime.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;

public class UVisitorUtil {

    public static void visit(UObject root, UVisitor visitor) {
        visit(root, visitor, new HashSet<UObject>());
        // //don't check for null objects, they will throw an nullpointer anyway...
        // UClass cl = root.getUClassifier();
        // if (visitor.beginObject(root, cl)){
        // for (UStructuralFeature feature : cl.getAllStructuralFeatures()){
        // if (visitor.shouldVisit(root, feature)){
        // Object value = feature.get(root);
        // if (value != null){
        // if (feature.isMany() == false){
        // visitor.visit(root, feature, -1, value);
        // if (feature.isReference() && value instanceof UObject)
        // visit((UObject)value, visitor); //recursive visit the complex object
        // }else{
        // List list_value = (List)value;
        // visitor.beginList(root, feature);
        // for (int i = 0; i < list_value.size(); i++){
        // Object lv = list_value.get(i);
        // visitor.visit(root, feature, i, lv);
        // if (lv instanceof UObject){ //recursive visit this object
        // visit((UObject)lv, visitor);
        // }
        // }
        // visitor.endList(root, feature);
        // }
        // }
        // }
        //
        // }
        // visitor.endObject(root, cl);
        // }
    }

    public static void visit(UObject root, UVisitor visitor, HashSet<UObject> visited) {
        _visit(root, visitor, visited);
    }

    private static void _visit(UObject root, UVisitor visitor, HashSet<UObject> visited) {
        // check if we have already visited this node
        // don't check for null objects, they will throw an nullpointer anyway...

        ArrayList<UObject> openList = new ArrayList<>();

        openList.add(root);

        while (openList.isEmpty() == false) {
            root = openList.remove(0);
            if (!visited.add(root))
                continue;

            UClass cl = root.getUClassifier();
            if (visitor.beginObject(root, cl)) {
                for (UStructuralFeature feature : cl.getAllStructuralFeatures()) {
                    if (visitor.shouldVisit(root, feature)) {
                        Object value;
                        try{
                        	value = feature.get(root);
                        }catch(Exception e) {
                        	e.printStackTrace();
                        	continue;
                        }
                        if (value != null) {
                            if (feature.isMany() == false) {
                                visitor.visit(root, feature, -1, value);
                                if (feature.isReference() && value instanceof UObject) {
                                    if (!visited.contains(value))
                                        openList.add((UObject) value);
                                }
                            } else {
                                List list_value = (List) value;
                                visitor.beginList(root, feature);
                                for (int i = 0; i < list_value.size(); i++) {
                                    Object lv = list_value.get(i);
                                    visitor.visit(root, feature, i, lv);
                                    if (lv instanceof UObject) { // recursive visit this object
                                        if (!visited.contains(lv)) {
                                            openList.add((UObject) lv);
                                        }
                                    }
                                }
                                visitor.endList(root, feature);
                            }
                        }
                    }
                }
                visitor.endObject(root, cl);
            }
        }

        // UClass cl = root.getUClassifier();
        // if (visitor.beginObject(root, cl)){
        // for (UStructuralFeature feature : cl.getAllStructuralFeatures()){
        // if (visitor.shouldVisit(root, feature)){
        // Object value = feature.get(root);
        // if (value != null){
        // if (feature.isMany() == false){
        // visitor.visit(root, feature, -1, value);
        // if (feature.isReference() && value instanceof UObject)
        // visit((UObject)value, visitor, visited); //recursive visit the complex object
        // }else{
        // List list_value = (List)value;
        // visitor.beginList(root, feature);
        // for (int i = 0; i < list_value.size(); i++){
        // Object lv = list_value.get(i);
        // visitor.visit(root, feature, i, lv);
        // if (lv instanceof UObject){ //recursive visit this object
        // visit((UObject)lv, visitor, visited);
        // }
        // }
        // visitor.endList(root, feature);
        // }
        // }
        // }
        //
        // }
        // visitor.endObject(root, cl);
        // }
    }
}
