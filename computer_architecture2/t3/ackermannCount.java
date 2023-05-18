
class Ackermann{
    
    static int procedureCalls = 0;          
    static int depth = 0;             
    static int maxDepth = 0;          
    static int overFlow = 0;                
    static int underFlow = 0;               
    static int minRegWinSize = 2;              // minimum size reg file can be before underflow
    static int activeWindows = 2;              // number of windows active on the reg file
    
    static int registerSet = 0;

    public static void main(String[] args)
    {
        registerSet = 6;
        int retVal = ackermann(3, 6);
        printTests(retVal);

        registerSet = 8;
        resetVars();
        retVal = ackermann(3, 6);
        printTests(retVal);

        registerSet = 16;
        resetVars();
        retVal = ackermann(3, 6);
        printTests(retVal);
    }


    public static void flowHandler(int x){
        // overflow check
        if(x == 1){
            if(activeWindows == registerSet){
                overFlow++;
            }else{
                activeWindows++;
            }
            depth++;

            if(depth > maxDepth)
            maxDepth = depth;
        }
        // underflow check
        else{
            if(activeWindows == minRegWinSize){
                underFlow++;
            }else{
                activeWindows--;
            }
            depth--;
        }
    }

    public static int ackermann(int x, int y)
    {
        procedureCalls++;
        flowHandler(1);

        if (x == 0)
        {
            flowHandler(-1);
            return y + 1;
        }
        else if (y == 0)
        {
            int tmp = ackermann(x - 1, 1);
            flowHandler(-1);
            return tmp;
        }
        else
        {
            int tmp = ackermann(x, y - 1);
            tmp = ackermann(x - 1, tmp);
            flowHandler(-1);
            return tmp;
        }
    }

    public static void resetVars(){
        procedureCalls = 0;
        depth = 0;
        maxDepth = 0;
        overFlow = 0;
        underFlow = 0;
        minRegWinSize = 2;
        activeWindows = 2;
        return;
    }


    public static void printTests(int retVal){
        System.out.println("\nResults for register set " + registerSet);
        System.out.println("Ackermann result           : " + retVal);
        System.out.println("Procedure calls            : " + procedureCalls);
        System.out.println("Max Register Window Depth  : " + maxDepth);
        System.out.println("Register file Overflow     : " + overFlow);
        System.out.println("Register file Underflow    : " + underFlow);
        return;
    }
}