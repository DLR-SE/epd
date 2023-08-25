package de.emir.tuml.ucore.runtime.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class ReflectionUtil {

    public static <T> Collection<Class<? extends T>> collectClasses(Class<? extends T> clazz) {
        try {
            ClassPath cp = ClassPath.from(clazz.getClassLoader());
            ImmutableSet<ClassInfo> all_classes = cp.getAllClasses();
            HashSet<Class<? extends T>> out = new HashSet<>();
            for (ClassInfo ci : all_classes) {
                Class<?> cl = ci.load();
                if (TypeUtils.inherits(cl, clazz))
                    out.add((Class<? extends T>) cl);
            }
            return out;
        } catch (IOException e) {
            ULog.error(e);
        }
        return null;
    }

}
