package sample.btrace.trace;

import static com.sun.btrace.BTraceUtils.strcat;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.Self;

/**
 * This Debug Sample shows How to print out the target instance fields
 * This is useful when debug a program
 *
 */
@BTrace
public class DebugSample {
     
    /*
     * ================================================
     * Note: This is very tricky this method can NOT have parameter AnyType[] args, 
     * Otherwise nothing will be triggered without any errors 
     * 
     * ================================================
     */
    @OnMethod(clazz="com.kuyun.cards.controller.general.GeneralCardAction",
              method="deleteLabelInfo"
              ,location=@Location(Kind.RETURN)
    )
    public static void printOnReturn(@Self Object targetInstance,Long id,Integer card_type, @ProbeMethodName String probeMethod,@Duration long duration) {
        BTraceUtils.println(strcat("before return in the method ",probeMethod));
        BTraceUtils.println(strcat("times costing in nano-seconds ",BTraceUtils.str(duration)));
        BTraceUtils.printFields(targetInstance);

        BTraceUtils.println("-----");
        BTraceUtils.println(id);
        BTraceUtils.println(card_type);
        BTraceUtils.println("-----");

    }
    
}
