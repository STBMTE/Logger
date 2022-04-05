package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExceptionUtil {
    public static Throwable merge(Throwable throwable, StackTraceElement stackTraceElement[]){
        for (Throwable throwabl : getChainExceptionCalls(throwable)){
            updateStackTrace(throwabl, stackTraceElement);
        }
        return throwable;
    }

    private static ArrayList<Throwable> getChainExceptionCalls(Throwable throwable){
        ArrayList<Throwable> result = new ArrayList<Throwable>();
        while (throwable != null){
            result.add(throwable);
            throwable = throwable.getCause();
        }
        return result;
    }

    private static void updateStackTrace(Throwable throwable, StackTraceElement[] stackTrace){
        ArrayList<StackTraceElement> internalCallChain = new ArrayList<>();
        addChainToStackTrace(internalCallChain, throwable.getStackTrace());
        addChainToStackTrace(internalCallChain, stackTrace);
        throwable.setStackTrace(internalCallChain.toArray(new StackTraceElement[0]));
    }

    private static <T> void addChainToStackTrace(List<T> list, T... trace){
        for(T object : trace){
            list.add(object);
        }
    }
}
