package com.gregbugaj.angularjetty;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ServiceRegistrationApplication extends javax.ws.rs.core.Application
{
    private static final Set<Class<?>> CLASSES;

    static
    {
        final Set<Class<?>> entrypoints = new HashSet<Class<?>>();
        entrypoints.add(ConsoleEntryPoint.class);

        CLASSES = Collections.unmodifiableSet(entrypoints);
    }

    @Override
    public Set<Class<?>> getClasses()
    {
        return CLASSES;
    }

}
